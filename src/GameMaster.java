import javax.swing.*;
import java.awt.*;

public class GameMaster extends javax.swing.JFrame {
    public void start() {
        JFrame frame = new JFrame();
        GamePanel panel = getGamePanel(frame.getSize());
        frame.add(panel);
        frame.addKeyListener(new KeyChecker_1(panel));
        frame.setSize(700, 700);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)(screenSize.getHeight()/2));
        frame.setTitle("Platformer Game");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public GamePanel getGamePanel(Dimension size){
        GamePanel panel = new GamePanel();
        panel.setLocation(0,0);
        panel.setSize(size);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setVisible(true);

        return panel;
    }
}
