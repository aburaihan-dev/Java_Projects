package fileServer;

import fileServer.network.ServerNetwork;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by Md.Abu Raihan Srabon on 17-Apr-16.
 */
public class Server_GUI extends JFrame implements ActionListener {

    private static Server_GUI instance;
    private static SimpleDateFormat myDate = new SimpleDateFormat("dd/MMM/yyyy - hh:mm:ss ");
    public ServerNetwork myServer;
    Vector serverFileList = new Vector();
    JPanel mainPanel;
    JLabel header;
    JList fileList;
    JTextField portField;
    JButton addFile;
    JButton connect;
    JButton deleteFile;
    JFileChooser fileChooser;
    JTextArea console;

    public Server_GUI() {
        setTitle("AnyFileShare Server");
        setSize(700, 400);
        setResizable(false);
        fileChooser = new JFileChooser();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    instance = new Server_GUI();
                    instance.createView();
                    instance.show();
//                    instance.myServer.startServer();
//                    header = new JLabel("Address : " + Inet4Address.getLocalHost().getHostAddress() + "    Port:" + myServer.getServerPort());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static Server_GUI getInstance() {
        return instance;
    }

    public Vector getServerFileList() {
        return serverFileList;
    }

    public void setServerFileList(Vector serverFileList) {
        this.serverFileList = serverFileList;
        fileList.setListData(serverFileList);
    }

    private void createView() {
        mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);
        JPanel headerPanel = new JPanel(new FlowLayout());
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        try {
            header = new JLabel("Address : " + Inet4Address.getLocalHost().getHostAddress());
            headerPanel.add(header);
            portField = new JTextField();
            portField.setText("3000");
            connect = new JButton("Start Server");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        headerPanel.add(portField);
        headerPanel.add(connect);

        fileList = new JList(serverFileList);
        JScrollPane fileJScrollPane = new JScrollPane(fileList);
        fileJScrollPane.setBorder(BorderFactory.createTitledBorder("File List"));
        fileJScrollPane.setPreferredSize(new Dimension(200, 0));
        mainPanel.add(fileJScrollPane, BorderLayout.WEST);

        console = new JTextArea();
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        console.setEditable(false);
        console.setFont(new Font("", Font.PLAIN, 12));
        JScrollPane consoleSP = new JScrollPane(console);
        consoleSP.setBorder(BorderFactory.createTitledBorder("Console"));
        consoleSP.setPreferredSize(new Dimension(400, 0));
        ((DefaultCaret) console.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        mainPanel.add(consoleSP, BorderLayout.EAST);

        //Using Gridlayout for the 1st time , :P. row =10 , col = 1 , hgap = 5 , Vgap = 10.
        JPanel btnPanel = new JPanel(new GridLayout(10, 1, 5, 10));
        mainPanel.add(btnPanel, BorderLayout.CENTER);

        addFile = new JButton("Add File");
        addFile.setPreferredSize(new Dimension(30, 20));
        addFile.addActionListener(this);

        connect.addActionListener(this);

        deleteFile = new JButton("Delete File");
        deleteFile.setPreferredSize(new Dimension(30, 20));
        deleteFile.addActionListener(this);

        btnPanel.add(addFile);
        btnPanel.add(deleteFile);
    }

    public void log(String message) {
        console.append(myDate.format(new Date()) + " :-> " + message + " \n");
    }

    public boolean warnning(String s){
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, s, "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            System.out.println("No button clicked");
        } else if (response == JOptionPane.YES_OPTION) {
            return true;
        } else if (response == JOptionPane.CLOSED_OPTION) {
            return false;
        }

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addFile) {
            fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            String s = fileChooser.getSelectedFile().toString();
            serverFileList.addElement(s);
            Server_GUI.getInstance().log("'" + s + "' File Added To the Server.");
            Server_GUI.getInstance().setServerFileList(serverFileList);

        }
        if (e.getSource() == connect) {
            if (!getServerFileList().isEmpty()){
                Server_GUI.getInstance().myServer = new ServerNetwork(Integer.parseInt(portField.getText().toString()));
                Server_GUI.getInstance().myServer.startServer();
                connect.setEnabled(false);
            }else {
                if(warnning("Add Some File To the Server File  List")){
                    fileChooser = new JFileChooser();
                    fileChooser.showOpenDialog(null);
                    String s = fileChooser.getSelectedFile().toString();
                    serverFileList.addElement(s);
                    Server_GUI.getInstance().log("'" + s + "' File Added To the Server.");
                    Server_GUI.getInstance().setServerFileList(serverFileList);
                    Server_GUI.getInstance().myServer = new ServerNetwork(Integer.parseInt(portField.getText().toString()));
                    Server_GUI.getInstance().myServer.startServer();
                    connect.setEnabled(false);
                }
            }
        }
        if (e.getSource() == deleteFile) {
            String s = fileList.getSelectedValue().toString();
            serverFileList.remove(s);
            Server_GUI.getInstance().log("'" + s + "' File has been Removed From Server.");
            Server_GUI.getInstance().setServerFileList(serverFileList);
        }
    }
}
