package fileClient;

import fileClient.network.ClientNetwork;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by Md.Abu Raihan Srabon on 17-Apr-16.
 */
public class Client_GUI extends JFrame implements ActionListener {

    private static Client_GUI instance;
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MMM/yy - hh:mm:ss ");
    public Vector downloadList = new Vector();
    public Vector serverFileList = new Vector();


    JPanel cPanel;
    JList fileList;
    JList downLoadList;
    JButton download;
    JButton upload;
    JButton refresh;
    JButton connect;
    JButton open;
    JTextField servAddress;
    JTextField servPort;
    JOptionPane uploPane;
    JOptionPane overWrite;
    JTextArea console;
    private ClientNetwork myConnection;

    public Client_GUI() {
        setTitle("AnyFileShare Client");
        setSize(700, 400);
        setResizable(false);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Client_GUI getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                instance = new Client_GUI();
                instance.createView();
                instance.show();
            }
        });
    }

    public Vector getDownloadList() {
        return downloadList;
    }

    public void setDownloadList(Vector downloadList) {
        this.downloadList = downloadList;
        Client_GUI.getInstance().downLoadList.setListData(downloadList);
    }

    public ClientNetwork getMyConnection() {
        return myConnection;
    }

    public void fileListUpdater(Vector v) {
        serverFileList = v;
        fileList.setListData(serverFileList);
    }

    public void createView() {
        cPanel = new JPanel(new BorderLayout());
        getContentPane().add(cPanel);

        JPanel headerPanel = new JPanel(new FlowLayout());
        cPanel.add(headerPanel, BorderLayout.NORTH);

        servAddress = new JTextField("192.168.0.2");
        servPort = new JTextField("3000");
        connect = new JButton("Connect To Server");
        connect.addActionListener(this);
        headerPanel.add(servAddress);
        headerPanel.add(servPort);
        headerPanel.add(connect);

        JPanel fileListPanel = new JPanel(new GridLayout(2, 1));
        fileListPanel.setPreferredSize(new Dimension(200, 0));
        cPanel.add(fileListPanel, BorderLayout.WEST);

        fileList = new JList(serverFileList);
        JScrollPane fileListSP = new JScrollPane(fileList);
        fileListSP.setBorder(BorderFactory.createTitledBorder("Server File List"));
        fileListPanel.add(fileListSP);

        downLoadList = new JList(downloadList);
        downLoadList.setListData(downloadList);
        downLoadList.setBorder(BorderFactory.createTitledBorder("Download List"));
        fileListPanel.add(downLoadList);

        JPanel btnHolder = new JPanel(new GridLayout(8, 1, 20, 10));
        cPanel.add(btnHolder, BorderLayout.CENTER);

        download = new JButton("Download");
        download.setToolTipText("Select a File From Server File List.");
        download.setPreferredSize(new Dimension(0, 30));
        download.addActionListener(this);
        btnHolder.add(download);

        upload = new JButton("Upload");
        upload.setToolTipText("Upload a File To Server.");
        upload.setPreferredSize(new Dimension(0, 30));
        upload.addActionListener(this);
        btnHolder.add(upload);

        refresh = new JButton("Refresh");
        refresh.setToolTipText("Refreshes File list From Server.");
        refresh.setPreferredSize(new Dimension(0, 30));
        refresh.addActionListener(this);
        btnHolder.add(refresh);

        open = new JButton("Open");
        open.setToolTipText("To open and view The Selected file.");
        open.addActionListener(this);
        btnHolder.add(open);

        console = new JTextArea();
        console.setEditable(false);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        console.setFont(new Font("", Font.PLAIN, 12));
        ((DefaultCaret) console.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane consoleSP = new JScrollPane(console);
        consoleSP.setToolTipText("Shows Comunications Between Client and Server.");
        consoleSP.setBorder(BorderFactory.createTitledBorder("Console"));
        consoleSP.setPreferredSize(new Dimension(400, 0));
        cPanel.add(consoleSP, BorderLayout.EAST);

    }

    public void log(String message) {
        console.append(DATE_FORMAT.format(new Date()) + " -> " + message + " \n");
    }

    public boolean warnning(String s) {
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
        if (e.getSource() == download) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Client_GUI.getInstance().myConnection.fileDownloader(fileList.getSelectedIndex());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }).start();
        }
        if (e.getSource() == upload) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.showOpenDialog(null);
                    String path = fileChooser.getSelectedFile().toString();
                    try {
                        Client_GUI.getInstance().myConnection.fileUploader(path);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }).start();
        }
        if (e.getSource() == refresh) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Client_GUI.getInstance().myConnection.fileListUpdater();
                }
            }).start();
        }
        if (e.getSource() == connect) {
            if (!servAddress.getText().isEmpty() && !servPort.getText().isEmpty()) {
                Client_GUI.getInstance().myConnection = new ClientNetwork(servAddress.getText().toString(), Integer.parseInt(servPort.getText().toString()));
                Client_GUI.getInstance().myConnection.connectToServer();
                connect.setEnabled(false);
            }
        }
        if (e.getSource() == open) {
            File file = new File(downLoadList.getSelectedValue().toString());
            System.out.println(file.exists());
            if (file.exists()) {
                try {
                    System.out.println(file.getName().toString());
                    Desktop.getDesktop().open(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

