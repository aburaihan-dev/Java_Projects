package fileServer.network;

import fileServer.Server_GUI;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Md.Abu Raihan Srabon on 17-Apr-16.
 */
public class ServerNetwork {

    boolean sendingFile = false;
    boolean receivingFile = false;
    private ServerSocket myServer;
    private int serverPort;
    private boolean running; // defines if we are accepting clients.

    public ServerNetwork(int port) {
        serverPort = port;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void startServer() {
        try {
            myServer = new ServerSocket(serverPort);
            Server_GUI.getInstance().log("Server Started Successfully!...");
            Server_GUI.getInstance().log("Server Running on : " + Inet4Address.getLocalHost().getHostAddress() + ":" + myServer.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
            Server_GUI.getInstance().log("Server Stoped Unexpectedly. ");
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Server_GUI.getInstance().log("Listening For Clients...");
                running = true;
                while (running) {
                    try {
                        Socket client = myServer.accept();
                        Server_GUI.getInstance().log("Client Connected!" + client.getRemoteSocketAddress());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean error = false;
                                while (!error && client.isConnected()) {
                                    try {
                                        DataInputStream dataIn = new DataInputStream(client.getInputStream());
                                        DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());
                                        String packetString = dataIn.readUTF();
                                        Server_GUI.getInstance().log(client.getRemoteSocketAddress() + " " + packetString);
                                        Server_GUI.getInstance().myServer.decisionMaker(packetString, client);
                                    } catch (IOException e) {
                                        error = true;
                                        Server_GUI.getInstance().log("This Client(" + client.getRemoteSocketAddress() + ")  has been Discnnected!");
//                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).start();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }
        }).start();


    }

    public void decisionMaker(String packetString, Socket clients) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(clients.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(clients.getInputStream());
        File open;
        Vector vector = new Vector();
        switch (processInputPacketString(packetString)) {
            case "FileList":
                dataOutputStream.writeUTF(processOutputPacketString(Server_GUI.getInstance().getServerFileList(), "FileList"));
                break;
            case "Ready":
                Server_GUI.getInstance().log(String.valueOf(Server_GUI.getInstance().getServerFileList().get(Integer.parseInt(processInputPacketStringIndex(packetString, 1)))));
                open = new File(String.valueOf(Server_GUI.getInstance().getServerFileList().get(Integer.parseInt(processInputPacketStringIndex(packetString, 1)))));
                Server_GUI.getInstance().log(open.getAbsolutePath().toString());
                vector.add(open.getName().toString());
                Server_GUI.getInstance().log((open.getAbsolutePath().toString()));
                vector.add(String.valueOf(open.length()));
                dataOutputStream.writeUTF(processOutputPacketString(vector, "SentFile"));
                sendFile(open.getAbsolutePath().toString(), clients);
                break;
            case "CheckFile": open = new File(processInputPacketStringIndex(packetString,1));
                if(open.exists()){
                    //FileExist
                    dataOutputStream.writeUTF(processOutputPacketString("FileExist",""));
                    String packet = dataInputStream.readUTF();
                    if(processInputPacketString(packet).equals("OverWrite")){
                        Server_GUI.getInstance().log(clients.getRemoteSocketAddress() + " is Uploading file , File :" + open.getName().toString() + " Size :" + processInputPacketStringIndex(packetString,2));
                        saveFile(open.getName().toString(),Long.valueOf(processInputPacketStringIndex(packetString,2)),clients,false);
                    }else{
                        clients.close();
                    }

                }else{
                    //FileDoesNotExist
                    Server_GUI.getInstance().log(clients.getRemoteSocketAddress() + " is Uploading file , File :" + open.getName().toString() + " Size :" + processInputPacketStringIndex(packetString,2));
                    dataOutputStream.writeUTF(processOutputPacketString("ReadyToDownload",""));
                    saveFile(open.getName().toString(),Long.valueOf(processInputPacketStringIndex(packetString,2)),clients,true);
                }
                break;
            case "4":
                break;
            case "5":
                break;
            default:
                break;
        }
        return;
    }

    private String processInputPacketString(String packetString) {
        String[] packetSegments = packetString.split(";");
        System.out.println(packetSegments[0]);
        return packetSegments[0];
    }

    public String processOutputPacketString(String[] packetString) {
        String packetOutput = null;
        for (int i = 0; i < packetString.length; i++) {
            packetOutput += ";" + packetString[i];
        }
        return packetOutput;
    }

    private String processInputPacketStringIndex(String packetString, int j) {
        String[] packetSegments = packetString.split(";");
        if (j < packetSegments.length) {
            System.out.println("processInputPacketStringIndex(String packetString, int j)" + packetSegments[j]);
            return packetSegments[j];
        } else {
            return "";
        }
    }

    public void sendFile(String filePath, Socket s) {
        Server_GUI.getInstance().log(s.getRemoteSocketAddress().toString() + "Sending File " + filePath);
        try {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream din = new DataInputStream(s.getInputStream());
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            fis.skip(Long.parseLong(din.readUTF()));
            Server_GUI.getInstance().log(String.valueOf(file.exists()));
            Server_GUI.getInstance().log(s.getRemoteSocketAddress().toString() + "Sending File " + file.getAbsolutePath());
            byte[] buffer = new byte[2048];
            while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }
            fis.close();
            dos.close();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveFile(String s, Long aLong, Socket sp , boolean append) throws IOException {
        DataInputStream streaminput = new DataInputStream(sp.getInputStream());
        DataOutputStream streamOutput = new DataOutputStream(sp.getOutputStream());
        File file = new File(s);
        FileOutputStream fos = new FileOutputStream(file, append);
        FileOutputStream fos_part = new FileOutputStream(s + ".part", append);
        Server_GUI.getInstance().log("Downloading : " + s);
        byte[] buffer = new byte[4096];

        long filesize = aLong; // Send file size in separate msg
        int read = 0;
        long totalRead = 0;
        long remaining = filesize;
        fos_part.write(Integer.parseInt(String.valueOf(filesize)));
        if (file.exists()){
            read = Integer.parseInt(String.valueOf(file.length()));
            streamOutput.writeUTF(String.valueOf(read));
        }else {
            streamOutput.writeUTF(String.valueOf(read));
        }
        long percent=0;
        long check = 0;
        while ((read = streaminput.read(buffer)) > 0) {
            totalRead += read;
            percent = (long)((totalRead / (double)filesize) * 100);
            if(percent>(check + 4)){
                Server_GUI.getInstance().log("read " + percent + "%");
                check= percent;
            }
            fos.write(buffer, 0, read);
            fos_part.write(Integer.parseInt(String.valueOf(totalRead)));
        }
        Server_GUI.getInstance().log("File Download Complete!!!");
        fos.close();
        streaminput.close();
        return;
    }


    public String processOutputPacketString(Vector packetString, String cmd) {
        String packetOutput = cmd;
        if (!packetString.isEmpty()) {
            Iterator i = packetString.iterator();
            String s;
            while (i.hasNext()) {
                s = i.next().toString();
                packetOutput += ";" + s;
            }

        } else Server_GUI.getInstance().log("File List Is Empty.\nAdd Some File 1st.Then Try again.");
        return packetOutput;
    }

    public String processOutputPacketString(String packetString_1, String packetString_2) throws IOException {
        String packetOutput = packetString_1 + ";" + packetString_2;
        return packetOutput;
    }

    public boolean isReceivingFile() {
        return receivingFile;
    }

    public void setReceivingFile(boolean receivingFile) {
        this.receivingFile = receivingFile;
    }

    public boolean isSendingFile() {
        return sendingFile;
    }

    public void setSendingFile(boolean sendingFile) {
        this.sendingFile = sendingFile;
    }
}
