import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class JumpPotion {

    int x;
    int y;
    int width;
    int height;
    int startX;

    Rectangle hitBox;

    public JumpPotion(int x, int y, int width, int height) {
        this.x = x;
        startX = x;
        this.y = y;
        this.width = width;
        this.height = height;

        hitBox = new Rectangle(x + 1, y + 151, width - 30, height - 15);

    }

    public void draw(Graphics2D gtd) {
        gtd.setColor(Color.GREEN);
        gtd.fillRect(x + 1, y + 151, width - 30, height - 15);
        gtd.setColor(Color.RED);
        gtd.fillRect(x + 11, y + 139, width - 50, height - 38);
        gtd.setColor(Color.BLACK);
        gtd.fillRect(x+3 , y + 136, width-34, height - 40);
    }
    public int set(int cameraX) {
        x = startX + cameraX;
        hitBox.x = x;

        return x;
    }
}