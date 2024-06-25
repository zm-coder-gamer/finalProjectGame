import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/**
 * Represents a coin in the game which the player can collect.
 * Coins increase the player's score when collected.
 */
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

        // Define the hitbox slightly smaller than the coin's dimensions for better collision
        hitBox = new Rectangle(x+10, y, width-5, height - 20);

    }

    public void draw(Graphics2D gtd) {
        gtd.setColor(Color.YELLOW);
        gtd.fillOval(x, y, width-10, height-10);
    }
    /**
     * Updates the position of the coin based on the camera movement.
     *
     * @param cameraX The x-coordinate offset of the camera.
     * @return The updated x-coordinate of the coin.
     */
    public int set(int cameraX) {
        x = startX + cameraX;
        hitBox.x = x;
        return x;
    }
}