import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EndSpike {

    int x;
    int y;
    int width;
    int height;
    int startX;

    Rectangle hitBox;

    public EndSpike(int x, int y, int width, int height) {
        this.x = x;
        startX = x;
        this.y = y;
        this.width = width;
        this.height = height;

        hitBox = new Rectangle(x+10, y, width-5, height - 20);

    }

    public void draw(Graphics2D gtd) {
        gtd.setColor(Color.GREEN);
        gtd.fillRect(x, y, width, height - 25);
        //gtd.setColor(Color.YELLOW);
        gtd.setColor(Color.BLUE);
        int[] xPoints = {x, x + 25, x + 50};
        int[] yPoints = {y, y - 20, y};
        int numPoints = 3;
        gtd.fillPolygon(xPoints, yPoints, numPoints);
    }
    public int set(int cameraX) {
        x = startX + cameraX;
        hitBox.x = x;

        return x;
    }
}