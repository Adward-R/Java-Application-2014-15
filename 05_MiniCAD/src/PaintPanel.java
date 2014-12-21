import javax.swing.*;
import java.awt.*;

/**
 * Created by Adward on 14/12/20.
 */
public class PaintPanel extends JPanel {
    int mode;
    Color color;
    int firstX,firstY;
    int secondX,secondY;

    public PaintPanel(){
        mode = 0;
        color = Color.black;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(color);
        if (mode==1){
            g.drawLine(firstX,firstY,secondX,secondY);
        }
        else if (mode==2){
            int r = (int) Math.sqrt(Math.pow(firstX-secondX,2)+Math.pow(firstY-secondY,2));
            g.drawOval(firstX-r,firstY-r,firstX+r,firstY+r);
        }
        else if(mode==3){
            g.drawRect(firstX, firstY, secondX, secondY);
        }
    }
    /*public void setParams(int mode,Color color,int firstX,int firstY,int secondX,int secondY){
        this.mode = mode;
        this.color = color;
        this.firstX = firstX;
        this.firstY = firstY;
        this.secondX = secondX;
        this.secondY = secondY;
    }*/

/*
    @Override
    public synchronized void addMouseListener(MouseListener mouseListener) {
        super.addMouseListener(mouseListener);

    }*/
}
