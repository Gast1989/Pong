import java.awt.*;
import java.awt.Event;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)); // 5/9
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddles paddle1;
    Paddles paddle2;
    Ball ball;
    Score score;

    //Constructor
    GamePanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);

        this.setFocusable(true); // enfoca el Panel en caso de que presionamos teclas. De esta manera las reconoce
        this.addKeyListener(new AL()); // hara q el Panel responda a las teclas presionadas
        this.setPreferredSize(SCREEN_SIZE); // requiere como parámetro un obj de tipo "Dimension"

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall(){
        //random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), (GAME_HEIGHT / 2) - (BALL_DIAMETER / 2), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddles(){
        paddle1 = new Paddles(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddles(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight()); // método de la clase "Image" importada
        graphics = image.getGraphics(); // creamos un graphics mediante la image
        draw(graphics); // dibujamos el obj graphics
        g.drawImage(image, 0, 0, this); // "this" hace referencia al GamePanel
    }

    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move(){
        // se ejecuta luego de cada iteración del GameLoop
        // logramos que el movimiento de las Paddles sea más fluido
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision(){
        // collide with Panel edges / frena las paddles al llegar al limite de la pantalla
        // Paddle1
        if (paddle1.y <= 0){
            paddle1.y = 0;
        }
        if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)){
            paddle1.y = (GAME_HEIGHT - PADDLE_HEIGHT);
        }
        // Paddle2
        if (paddle2.y <= 0){
            paddle2.y = 0;
        }
        if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)){
            paddle2.y = (GAME_HEIGHT - PADDLE_HEIGHT);
        }

        // ball cambia de direccion al hacer contacto con el limite superior e inferior de la pantalla
        if(ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= (GAME_HEIGHT - BALL_DIAMETER / 2)){
            ball.setYDirection(-ball.yVelocity);
        }

        // colisión de la ball con las paddles, usando el método "intersection" de la clase "Rectangle"
        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity); // invertimos del + al - y vicervers
            ball.xVelocity++; // OPCIONAL
            if(ball.yVelocity > 0){
                ball.yVelocity++; // OPCIONAL
            }else{
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // OPCIONAL
            if(ball.yVelocity > 0){
                ball.yVelocity++; // OPCIONAL
            }else{
              ball.yVelocity--;
            }
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        // Dar puntaje y crear nuevos paddles y ball
        // Player 2
        if(ball.x <= 0){
            score.player2++;
            newPaddles();
            newBall();
            System.out.println(score.player2);
        }
        // Player 1
        if(ball.x >= GAME_WIDTH - BALL_DIAMETER){
            score.player1++;
            newPaddles();
            newBall();
            System.out.println(score.player1);
        }

    }


    // este metodo es ejecutado automaticamente al haber iniciado el Thread
    public void run(){
        //gameLoop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    // Declaramos una "inner class" de tipo ActionListener
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }


}
