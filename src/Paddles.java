import java.awt.*;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;

public class Paddles extends Rectangle{

    int id;
    int yVelocity;
    int speed = 10;

    //Constructor
    Paddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W){
                    setYDirection(-speed); // movimiento hacia arriba en el eje Y
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    setYDirection(speed); // movimiento hacia abajo en el eje Y
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    setYDirection(-speed); // movimiento hacia arriba en el eje Y
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    setYDirection(speed); // movimiento hacia abajo en el eje Y
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W){
                    setYDirection(0); // movimiento hacia arriba en el eje Y
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    setYDirection(0); // movimiento hacia abajo en el eje Y
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    setYDirection(0); // movimiento hacia arriba en el eje Y
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    setYDirection(0); // movimiento hacia abajo en el eje Y
                    move();
                }
                break;
        }
    }

    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }

    public void move(){
        y = y + yVelocity;
    }

    public void draw(Graphics g){
        if (this.id == 1){
            g.setColor(Color.YELLOW);
        }else{
            g.setColor(Color.RED);
        }
        g.fillRect(x, y, width, height);
    }
}
