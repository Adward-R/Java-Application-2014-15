import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by Adward on 14/12/20.
 */
public class DrawFrame extends JFrame {
    Container contentPane;
    int firstX,firstY,secondX,secondY;
    boolean hasOneClick;
    TopPanel topPanel;
    PaintPanel paintPanel;
    DrawFrame ptr = this;
    Graphics2D g,g2;
    BufferedImage bi;

    public DrawFrame(){
        //super();
        this.setTitle("Painting Pan");
        setSize(850,550);
        //this.setBounds(200,200,1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.pack();
        this.setLocationRelativeTo(null);

        contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.green);

        paintPanel = new PaintPanel();
        contentPane.add(paintPanel,BorderLayout.CENTER);
        topPanel = new TopPanel(paintPanel);
        contentPane.add(topPanel, BorderLayout.NORTH);
        JLabel bottomLabel = new JLabel("Click on paint pan twice to generate a figure...");
        contentPane.add(bottomLabel,BorderLayout.SOUTH);
        hasOneClick = false;
        bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        topPanel.setBufferedImage(bi);
        g = (Graphics2D) paintPanel.getGraphics();
        g2 = bi.createGraphics();

        this.setVisible(true);

        paintPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (topPanel.bi!=null)
                    bi = topPanel.bi;
                g = (Graphics2D) paintPanel.getGraphics();
                g2 = bi.createGraphics();
                g.setColor(topPanel.color);
                g2.setColor(topPanel.color);

                if (hasOneClick){
                    secondX = mouseEvent.getX();
                    secondY = mouseEvent.getY();
                    //paintPanel.setParams(topPanel.mode,topPanel.color,firstX,firstY,secondX,secondY);
                    //paintPanel.paintComponent(paintPanel.getGraphics());

                    if (topPanel.mode==1){
                        g.drawLine(firstX,firstY,secondX,secondY);
                        g2.drawLine(firstX,firstY,secondX,secondY);
                    }
                    else if (topPanel.mode==2){
                        int r = (int) Math.sqrt(Math.pow(firstX-secondX,2)+Math.pow(firstY-secondY,2));
                        g.drawOval(firstX-r,firstY-r,2*r,2*r);
                        g2.drawOval(firstX-r,firstY-r,2*r,2*r);
                    }
                    else if(topPanel.mode==3){
                        int X,Y,W,H;
                        W = firstX - secondX;
                        H = firstY - secondY;
                        if (W<0 && H<0){
                            X = firstX;
                            Y = firstY;
                            W = -W;
                            H = -H;
                        }
                        else if (W>0 && H>0){
                            X = secondX;
                            Y = secondY;
                        }
                        else if (W>0 && H<0){
                            X = secondX;
                            Y = firstY;
                            H = -H;
                        }
                        else {
                            X = firstX;
                            Y = secondY;
                            W = -W;
                        }
                        g.drawRect(X,Y,W,H);
                        g2.drawRect(X,Y,W,H);
                    }
                    hasOneClick = false;
                }
                else {
                    firstX = mouseEvent.getX();
                    firstY = mouseEvent.getY();
                    if (topPanel.mode==1||topPanel.mode==2||topPanel.mode==3) {
                        hasOneClick = true;
                    }
                    else if (topPanel.mode==4){ //insert text
                        //Font font = new Font("Serif", Font.BOLD+Font.ITALIC,12);
                        String s = topPanel.getText();
                        if ("".equals(s)){
                            JOptionPane.showMessageDialog(paintPanel,"Please input some text before paint it!");
                        }
                        g.drawString(s, firstX, firstY);
                        g2.drawString(s, firstX, firstY);
                    }
                }
                //topPanel.setPanel(ptr);
                topPanel.setBufferedImage(bi);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }
    public static void main(String[] args){
        DrawFrame drawFrame = new DrawFrame();
    }
}
