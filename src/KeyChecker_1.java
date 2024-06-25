import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyChecker_1 extends KeyAdapter{
    GamePanel panel;

    public KeyChecker_1 (GamePanel panel) {
        this.panel = panel;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        panel.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        panel.keyReleased(e);
    }
}