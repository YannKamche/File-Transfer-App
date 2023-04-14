
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static JFrame JFrame;
    static ArrayList<MyFile>myFiles= new ArrayList<>();

    public static void main(String[]args){
        int fileid = 0;
        JFrame jFrame = new JFrame("Server");
        jFrame.setSize(400, 400);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);

 JPanel jpanel = new JPanel();
        Container jPanel = null;
        JPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JScrollPane jScrollPane= new JScrollPane(jPanel);
        JScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel jlTitle = new JLabel("File Receiver");
        jlTitle.setFont(new Font( "Arial", Font.BOLD,25 ));
        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        jFrame.add(jlTitle);
        jFrame.add(jScrollPane);
        jFrame.setVisible(true);

        ServerSocket serverSocket = new ServerSocket(1234);

        while (true){
            try{
                Socket socket= serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                int filenamelength = dataInputStream.readInt();

                if (filenamelength > 0){
                    byte[] filenameBytes = new byte[filenamelength];
                    dataInputStream.readFully(filenameBytes, 0, filenameBytes.length);
                    String filename = new String(filenameBytes);


                    if (filenamelength>0){
                        int fileContentLength;
                        byte[] fileContentBytes= new byte[fileContentLength];
                        dataInputStream.readFully(fileContentBytes,0,fileContentLength);

                        JPanel jpFileRow =new JPanel();
                        jpFileRow.setLayout(new BoxLayout(jpFileRow,BoxLayout.Y_AXIS));

                        JLabel jFileName = new JLabel(fileName);
                        jFileName.setFont( new Font( "Arial", Font.BOLD, 20) );
                        jFileName.setBorder(new EmptyBorder(10, 0,10,0));

                        if (getFileExtension(filename).equalsIgnoreCase("txt")); {
                            jpFileRow.setName(String.valueOf(fileid));
                            jpFileRow.addMouseListener(getMyMouselistener());

                            jpFileRow.add(jlFilename);
                            jPanel.add(jpFileRow);
                            jFrame.validate();


                    }
                }
            }
        } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}

public static MouseListener getMyMouselistener(){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JPanel jPanel = (JPanel) e.getSource();
                int fileId = Integer.parseInt(String.valueOf(jPanel.getName()));

                for (MyFile myFile: myFiles){
                    if (myFile.getId()==fileId){
                        JFrame jfPreview = createFrame();
                        jfPreview.setVisible(true);

                    }
                }



            }
            @Override

            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
}

public static JFrame createFrame(String filename, byte[] filedata, String fileExtension){
        JFrame jFrame= new JFrame( "File Downloader");
        JFrame.setSize(400,400);

        JPanel jPanel=new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));

        JLabel jlTitle = new JLabel("File Downloader");
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20, 0 , 10,));

        JButton jbNo = new JButton("No");
        jbNo.setPreferredSize(new Dimension(150, 75));
        jbNo.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel jlFileContent = new JLabel();
        jlFileContent.setAlignmentX(Component.CENTER_ALIGNMENT);
}


public static  String getFileExtensions(String fileName){
    // would not work with .tor.gz
        int i= fileName.lastIndexOf(".");

        if(i>0){
            return fileName.substring(i+1);
            else{
                return "No extension Found";
            }
        }
    }
}