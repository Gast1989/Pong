import java.awt.*;
import java.awt.Event;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1; // contendra el score del player1
    int player2; // score del player2

    //Constructor
    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH; // al ser variables de Clase, no usamos "this" ya que no nos referimos a una instancia de la Clase
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));

        // Linea central
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH  / 2, GAME_HEIGHT);

        // Puntaje
        g.drawString(String.valueOf(this.player1 / 10) + String.valueOf(this.player1 % 10), (GAME_WIDTH / 2) - 95, 50);
        g.drawString(String.valueOf(this.player2 / 10) + String.valueOf(this.player2 % 10), (GAME_WIDTH / 2) + 20, 50);
    }
}
