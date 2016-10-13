package fileClient.network;

import fileClient.Client_GUI;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Md.Abu Raihan Srabon on 17-Apr-16.
 */
public class ClientNetwork {

    String packetString;
    boolean sendingFile = false;
    boolean receivingFile = false;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    private Socket myConnection;
    private int serverPort;
    private String serverAddress;

    public ClientNetwork(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public Socket getMyConnection() {
        return myConnection;
    }

    public void connectToServer() {
        boolean error = false;
        try {
            myConnection = new Socket(serverAddress, serverPort);
            Client_GUI.getInstance().log("Successfully Connected to the Server.");
            Client_GUI.getInstance().log(String.valueOf(myConnection.getLocalSocketAddress()));
            Client_GUI.getInstance().log("Waiting for Available file list.");

        } catch (IOException e) {
            Client_GUI.getInstance().log("Disconnected from Server!");
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    dataOutputStream = new DataOutputStream(myConnection.getOutputStream());
                    processOutputPacketStringFinal("FileList", "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                boolean error = false;
                while (myConnection.isConnected() && !error) {
                    try {
                        fileListUpdater();
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        error = true;
                        Client_GUI.getInstance().log("Server Disconnected!!!");
                    }

                }
            }
        }).start();
    }

    public void fileDownloader(int i) throws IOException {
        Socket sp = new Socket(serverAddress, serverPort);
        DataOutputStream dataOutput = new DataOutputStream(sp.getOutputStream());
        DataInputStream dataInput = new DataInputStream(sp.getInputStream());
        dataOutput.writeUTF(processOutputPacketStringFinal("Ready", String.valueOf(i)));
        String packet = dataInput.readUTF();
        if (processInputPacketString(packet).equals("SentFile")) {
            saveFile(processInputPacketStringIndex(packet, 1), Long.parseLong(processInputPacketStringIndex(packet, 2)), sp);
        } else {
            Client_GUI.getInstance().log("Requested File is not Available From Server.");
        }
    }

    public void fileUploader(String path) throws IOException {
        Socket sp = new Socket(serverAddress, serverPort);
        DataInputStream inputStream = new DataInputStream(sp.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(sp.getOutputStream());

        File file = new File(path);
        Vector v = new Vector();
        v.add(file.getName().toString());
        v.add(String.valueOf(file.length()));
        outputStream.writeUTF(processOutputPacketString(v, "CheckFile"));
        String packet = inputStream.readUTF();
        if (processInputPacketString(packet).equals("ReadyToDownload")) {
            sendFile(file.getAbsolutePath().toString(), sp);
        } else if (processInputPacketString(packet).equals("FileExist")) {
            if (Client_GUI.getInstance().warnning("Do You want to overwrite")) {
                sendFile(file.getAbsolutePath().toString(), sp);
            } else {
                Client_GUI.getInstance().log("File Exist in Server.Can not upload.");
            }
        }
    }

    public void sendFile(String filePath, Socket s) {
        Client_GUI.getInstance().log(s.getRemoteSocketAddress().toString() + "Sending File " + filePath);
        try {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream din = new DataInputStream(s.getInputStream());
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            fis.skip(Long.parseLong(din.readUTF()));
            Client_GUI.getInstance().log(String.valueOf(file.exists()));
            Client_GUI.getInstance().log(s.getRemoteSocketAddress().toString() + "Sending File " + file.getAbsolutePath());
            byte[] buffer = new byte[2048];
            while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }

            fis.close();
            dos.close();
            Client_GUI.getInstance().log(file.getName().toString() + " Upload to Server Success!");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void saveFile(String s, Long aLong, Socket sp) throws IOException {
        DataInputStream streaminput = new DataInputStream(sp.getInputStream());
        DataOutputStream streamOutput = new DataOutputStream(sp.getOutputStream());
        File file = new File(s);
        FileOutputStream fos = new FileOutputStream(file, true);
        FileOutputStream fos_part = new FileOutputStream(s + ".part", true);
        Client_GUI.getInstance().log("Downloading : " + s);
        byte[] buffer = new byte[4096];

        long filesize = aLong; // Send file size in separate msg
        int read = 0;
        long totalRead = 0;
        long remaining = filesize;
        fos_part.write(Integer.parseInt(String.valueOf(filesize)));
        if (file.exists()) {
            read = Integer.parseInt(String.valueOf(file.length()));
            streamOutput.writeUTF(String.valueOf(read));
        } else {
            streamOutput.writeUTF(String.valueOf(read));
        }
        long percent = 0;
        long check = 0;
        while ((read = streaminput.read(buffer)) > 0) {
            totalRead += read;
            percent = (long) ((totalRead / (double) filesize) * 100);
            if (percent > (check + 4)) {
                Client_GUI.getInstance().log("read " + percent + "%");
                check = percent;
            }
            fos.write(buffer, 0, read);
            fos_part.write(Integer.parseInt(String.valueOf(totalRead)));
        }
        Client_GUI.getInstance().log("File Download Complete!!!");
        Vector v = Client_GUI.getInstance().getDownloadList();
        v.add(file.getName().toString());
        Client_GUI.getInstance().setDownloadList(v);
        fos.close();
        streaminput.close();
        return;
    }

    public void fileListUpdater() {
        try {
            DataInputStream dataInputStream = new DataInputStream(myConnection.getInputStream());
            processOutputPacketString("FileList", "");
            Client_GUI.getInstance().fileListUpdater(processInputPacketVector(dataInputStream.readUTF()));
            // Don't get lost man. it's complex but More Simpler when you understand it.Even i got lost too when i wrote ^^this^^ line.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processInputPacketString(String packetString) {
        String[] packetSegments = packetString.split(";");
        return packetSegments[0];
    }

    private String processInputPacketStringIndex(String packetString, int j) {
        String[] packetSegments = packetString.split(";");
        if (j < packetSegments.length) {
            return packetSegments[j];
        } else {
            return packetSegments[packetSegments.length];
        }
    }

    protected Vector processInputPacketVector(String packetString) {
        Vector v = new Vector();
        String[] packetSegments = packetString.split(";");
        for (int i = 1; i < packetSegments.length; i++) {
            v.addElement(packetSegments[i].toString());
        }
        return v;
    }

    public void processOutputPacketString(String packetString_1, String packetString_2) throws IOException {
        String packetOutput = packetString_1 + ";" + packetString_2;
        dataOutputStream.writeUTF(packetOutput);
    }

    public String processOutputPacketStringFinal(String packetString_1, String packetString_2) {
        String packetOutput = packetString_1 + ";" + packetString_2;
        return packetOutput;
    }

    public String processOutputPacketString(Vector packetString, String cmd) {
        String packetOutput = cmd;
        Iterator i = packetString.iterator();
        while (i.hasNext()) {
            packetOutput += ";" + i.next().toString();
        }
        Client_GUI.getInstance().log(packetOutput);
        return packetOutput;
    }

    public boolean isSendingFile() {
        return sendingFile;
    }

    public void setSendingFile(boolean sendingFile) {
        this.sendingFile = sendingFile;
    }

    public boolean isReceivingFile() {
        return receivingFile;
    }

    public void setReceivingFile(boolean receivingFile) {
        this.receivingFile = receivingFile;
    }

}
