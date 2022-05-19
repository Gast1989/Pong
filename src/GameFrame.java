import java.awt.*;
import java.awt.Event;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame{

    GamePanel panel;

    //Constructor
    GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // adapta el tamaño del Frame en base al tamaño del Panel
        this.setVisible(true);
        this.setLocationRelativeTo(null); // el Frame aparece en el centro de la pantalla
    }
}
