import java.awt.Color;

public class MainFrame extends javax.swing.JFrame {
    void setResizeable(boolean b) {
    }
    public MainFrame() {
        GamePanel panel = new GamePanel();
        panel.setLocation(0,0);
        panel.setSize(this.getSize());
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setVisible(true);
        this.add(panel);
        addKeyListener(new KeyChecker_1(panel));
    }
}

