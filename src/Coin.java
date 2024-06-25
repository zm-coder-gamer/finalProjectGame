import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Coin {

    int x;
    int y;
    int width;
    int height;
    int startX;

    Rectangle hitBox;

    public Coin(int x, int y, int width, int height) {
        this.x = x;
        startX = x;
        this.y = y;
        this.width = width;
        this.height = height;

        hitBox = new Rectangle(x+10, y, width-5, height - 20);

    }

    public void draw(Graphics2D gtd) {
        gtd.setColor(Color.YELLOW);
        //gtd.fillRect(x, y, width-20, height);
        gtd.fillOval(x, y, width-10, height-10);
    }
    public int set(int cameraX) {
        x = startX + cameraX;
        hitBox.x = x;

        return x;
    }
}