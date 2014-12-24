import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Adward on 14/12/23.
 */
public class MainPanel extends JFrame{
    MainPanel ptr = this;
    Container contentPane;
    JMenuBar menuBar;
    JMenu fileMenu;
    //DataObtainer dataObtainer;
    MyTable myTable;
    JTable table;
    JScrollPane scrollPane;
    JLabel statusBar;
    String currentFile;

    public MainPanel(){
        this.setTitle("CSV Viewer");
        this.setSize(1000,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        //contentPane.setBackground(Color.black);

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem openItem = new JMenuItem("Open...");
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);

        statusBar = new JLabel("Status...");
        contentPane.add(statusBar,BorderLayout.SOUTH);

        this.setVisible(true);

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    myTable.dataObtainer.fileSaver(currentFile);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(contentPane,"No file to be saved yet!");
                }
                catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(contentPane,"No file to be saved yet!");
                }
            }
        });
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ("CANCEL".equals(currentFile = chooseFile())){
                    return;
                }
                try {
                    if (myTable==null) {
                        myTable = new MyTable(currentFile);
                        table = new JTable(myTable);
                        scrollPane = new JScrollPane(table);
                        contentPane.add(scrollPane);
                    }
                    else {
                        myTable.dataObtainer.fileParser(currentFile);
                        //scrollPane.updateUI();
                    }
                    statusBar.setText(myTable.dataObtainer.getRowNum() + " rows and " +
                            myTable.dataObtainer.getColumnNum() + " columns.");
                    ptr.setTitle("CSV Viewer: "+currentFile);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(contentPane, "Error occured while opening the file!");
                }
                contentPane.setVisible(false);
                contentPane.setVisible(true);
            }
        });

    }

    public String chooseFile(){
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv", "csv");
        filechooser.setFileFilter(filter);
        int returnVal = filechooser.showOpenDialog(contentPane);
        if (returnVal == JFileChooser.CANCEL_OPTION) {return "CANCEL";}
        // the following will be executed if clicked "OK"
        File fileName = filechooser.getSelectedFile();
        return fileName.getPath();
    }

    public static void main(String[] args){
        MainPanel mainPanel = new MainPanel();
    }
}
