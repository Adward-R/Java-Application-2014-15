import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Adward on 14/12/19.
 */

public class TopPanel extends JPanel {
    final static int TEXTFIELD_WIDTH = 5;
    final static int MODE_LINE = 1;
    final static int MODE_CIRCLE = 2;
    final static int MODE_REC = 3;
    final static int MODE_TEXT = 4;

    LayoutManager topLayout;
    TopPanel ptr = this;
    public int mode;
    public Color color;
    //public int scale; //double?
    public BufferedImage bi;
    PaintPanel paintPanel;

    JRadioButton drawLine,drawCircle,drawRectangle,drawText;
    ButtonGroup bg;
    JLabel textLabel;
    JTextField changeText;
    JButton changeColor;
    JButton fileIn;
    JButton fileOut;

    public TopPanel(final PaintPanel paintPanel){
        this.paintPanel = paintPanel;
        topLayout = new FlowLayout();
        this.setLayout(topLayout);
        drawLine = new JRadioButton("Line");
        drawCircle = new JRadioButton("Circle");
        drawRectangle = new JRadioButton("Rectangle");
        drawText = new JRadioButton("Text");
        fileIn = new JButton("Import");
        fileOut = new JButton("Export");

        //bi = new BufferedImage(paintPanel.getWidth(), paintPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        bg = new ButtonGroup();
        bg.add(drawLine);
        bg.add(drawCircle);
        bg.add(drawRectangle);
        bg.add(drawText);
        this.add(drawLine);
        this.add(drawCircle);
        this.add(drawRectangle);
        this.add(drawText);
        this.add(fileIn);
        this.add(fileOut);
        mode = 0;
        color = Color.black;
        textLabel = new JLabel("Text to be drawn:");
        this.add(textLabel);
        changeText = new JTextField(TEXTFIELD_WIDTH);
        this.add(changeText);
        changeColor = new JButton("Change color");
        this.add(changeColor);

       // final JLabel label = new JLabel("COLOR");
        drawLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mode = MODE_LINE;
            }
        });
        drawCircle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mode = MODE_CIRCLE;
            }
        });
        drawRectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mode = MODE_REC;
            }
        });
        drawText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mode = MODE_TEXT;
            }
        });
        changeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Color.black==color)
                    color = Color.red;
                else if (Color.red==color)
                    color = Color.green;
                else if (Color.green==color)
                    color = Color.blue;
                else if (Color.blue==color)
                    color = Color.black;
            }
        });
        /*changeText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    scale = Integer.parseInt(actionEvent.getActionCommand());
                } catch (NumberFormatException e) {
                    //TODO generate an AlertDialog
                }
            }
        });*/
        fileOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String str = JOptionPane.showInputDialog("Please specify a file path to import","PaintPan.png");
                    ImageIO.write(bi, "PNG", new File(str));
                }
                catch (FileNotFoundException e){
                    JOptionPane.showMessageDialog(paintPanel,"File not found!");
                }
                catch (NullPointerException e){
                    JOptionPane.showMessageDialog(paintPanel,"File not found!");
                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(paintPanel,"Cannot access that path!");
                }
            }
        });
        fileIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser filechooser = new JFileChooser();
                filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF Images", "jpg", "gif");
                filechooser.setFileFilter(filter);
                int returnVal = filechooser.showOpenDialog(ptr);

                if(returnVal == JFileChooser.CANCEL_OPTION) {
                    return;
                }
                // the following will be executed if clicked "OK"
                File fileName = filechooser.getSelectedFile();
                fileName.canRead();
                if(fileName.getName().equals("")) //fileName equals to null is not likely to happen
                {
                    JOptionPane.showMessageDialog(filechooser,"Filename","Please specify a filename!",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    //JLabel label = new JLabel();
                    ImageIcon imageicon = new ImageIcon(filechooser.getSelectedFile().getPath());
                    paintPanel.getGraphics().drawImage(imageicon.getImage(),0,0,paintPanel.getWidth(),paintPanel.getHeight(),paintPanel);
                    bi.createGraphics().drawImage(imageicon.getImage(), 0, 0, getWidth(), paintPanel.getHeight(), paintPanel);
                }
            }
        });
    }

    public String getText(){
        return changeText.getText();
    }

    public void setBufferedImage(BufferedImage bi){
        this.bi = bi;
    }

}
