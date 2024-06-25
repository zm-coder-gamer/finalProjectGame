import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {
    int wallIndex = 0;
    GamePanel panel;
    int x;
    int y;
    int width;
    int height;
    int step = 0;
    int coincounter;
    double xspeed;
    double yspeed;

    Rectangle hitBox;

    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;

    public Player(int x, int y, GamePanel panel) {
        this.panel = panel;
        this.x = x;
        this.y = y;

        width = 50;
        height = 100;
        hitBox = new Rectangle(x, y, width, height);

        xspeed = 0;
        yspeed = 0;
        System.out.println(x + " " + y);
    }
    public void incrementWallIndex(){
        wallIndex++;
    }


    public void mainCharacterRight(Graphics2D gtd) {
        gtd.setColor(Color.BLACK); //main body
        gtd.fillRect(x, y, width, height);
        gtd.setColor(Color.WHITE);
        gtd.fillOval(x + 32, y + 12, 10, 12);
        gtd.setColor(Color.BLACK);
        gtd.fillOval(x + 37, y + 15, 4, 5);
        //gtd.setColor(Color.WHITE);
        //gtd.fillRect(x+30, y+11, width-40, height-95);

        gtd.setColor(Color.DARK_GRAY); //hat
        gtd.fillRect(x, y - 20, width, height - 75);
        gtd.setColor(Color.GRAY);
        gtd.fillRect(x + 46, y - 10, width - 46, height - 90);
        gtd.fillRect(x + 39, y - 10, width - 46, height - 90);
        gtd.fillRect(x + 32, y - 10, width - 46, height - 90);
        gtd.fillRect(x + 25, y - 10, width - 46, height - 90);

        gtd.setColor(Color.DARK_GRAY);
        int xRight[] = {x, x + 10, x + 35, x + 50};
        int yRight[] = {y - 20, y - 33, y - 28, y - 20};
        int points = 4;
        gtd.fillPolygon(xRight, yRight, points);

        gtd.setColor(Color.WHITE); //arms
        gtd.fillRect(x + 26, y + 47, width - 46, height - 75);
    }

    public void mainCharacterLeft(Graphics2D gtd) {
        gtd.setColor(Color.BLACK); //main body
        gtd.fillRect(x, y, width, height);
        gtd.setColor(Color.WHITE);
        gtd.fillOval(x + 11, y + 12, 10, 12);
        gtd.setColor(Color.BLACK);
        gtd.fillOval(x + 11, y + 15, 4, 5);
        //gtd.setColor(Color.WHITE);
        //gtd.fillRect(x+9, y+11, width-40, height-95);
        gtd.setColor(Color.DARK_GRAY); //hat
        gtd.fillRect(x, y - 20, width, height - 75);
        gtd.setColor(Color.GRAY);
        gtd.fillRect(x, y - 10, width - 46, height - 90);
        gtd.fillRect(x + 7, y - 10, width - 46, height - 90);
        gtd.fillRect(x + 14, y - 10, width - 46, height - 90);
        gtd.fillRect(x + 21, y - 10, width - 46, height - 90);

        gtd.setColor(Color.DARK_GRAY);
        int xLeft[] = {x + 50, x + 40, x + 15, x};
        int yLeft[] = {y - 20, y - 33, y - 28, y - 20};
        int points = 4;
        gtd.fillPolygon(xLeft, yLeft, points);


        gtd.setColor(Color.WHITE); //arms
        gtd.fillRect(x + 22, y + 47, width - 46, height - 75);
    }

    public void set() {
        if (keyLeft && keyRight || !keyLeft && !keyRight) {
            xspeed *= 0.8; // Apply friction when no horizontal key is pressed
        } else if (keyLeft && !keyRight) {
            xspeed = -5; // Set a constant speed when moving left
            step = 1;
        } else if (keyRight && !keyLeft) {
            xspeed = 5; // Set a constant speed when moving right
            step = 2;
        }

        if (xspeed > 0 && xspeed < 0.75) {
            xspeed = 0;
        }
        if (xspeed < 0 && xspeed > -0.75) {
            xspeed = 0;
        }

        //cap speed
        /*if (xspeed > 7) {
            xspeed = 7;
        }
        if (xspeed < -7) {
            xspeed = -7;
        }*/
        if (keyUp) {
            hitBox.y++;
            for (Wall wall : panel.walls) {
                if (wall.hitBox.intersects(hitBox)) {
                    yspeed = -11;
                }
            }
            hitBox.y--;
        }

        yspeed += 0.5; //gravity

        //horizontal collision for wall
        hitBox.x += xspeed;
        for (Wall wall : panel.walls) {
            if (hitBox.intersects(wall.hitBox)) {
                hitBox.x -= xspeed;
                while (!wall.hitBox.intersects(hitBox)) {
                    hitBox.x += Math.signum(xspeed);
                }
                hitBox.x -= Math.signum(xspeed);
                panel.cameraX += x - hitBox.x;
                xspeed = 0;
                hitBox.x = x;
            }
        }

        //vertical collision for wall
        hitBox.y += yspeed;
        for (Wall wall : panel.walls) {
            if (hitBox.intersects(wall.hitBox)) {
                hitBox.y -= yspeed;
                while (!wall.hitBox.intersects(hitBox)) {
                    hitBox.y += Math.signum(yspeed);
                }
                hitBox.y -= Math.signum(yspeed);
                yspeed = 0;
                y = hitBox.y;
            }
        }

        // handle jump potion collision (both horizontal and vertical)
        ArrayList<JumpPotion> jumpPotionsToRemove = new ArrayList<>();
        for (JumpPotion jP : panel.jumpPotions) {
            if (hitBox.intersects(jP.hitBox)) {
                jumpPotionsToRemove.add(jP);
                System.out.println("Jump potion collected!");
                yspeed = -12;
            }
        }
        boolean resetPanelForEnd = false;

        ArrayList<EndSpike> endSpikeToRemove = new ArrayList<>();
        for (EndSpike e : panel.EndSpikes) {
            if (hitBox.intersects(e.hitBox)) {
                endSpikeToRemove.add(e);
                resetPanelForEnd = true;
                System.out.println("Level Finished");

            }
        }
        if (resetPanelForEnd){
            incrementWallIndex();
            panel.reset();
        }

        ArrayList<Coin> coinsToRemove = new ArrayList<>();
        for (Coin c : panel.Coins) {
            if (hitBox.intersects(c.hitBox)) {
                coinsToRemove.add(c);
                System.out.println("coin collected!");
                coincounter++;

            }
        }
        ArrayList<Enemy> nextEnemyToRemove = new ArrayList<>();
        boolean resetPanel = false;
        for (Enemy enemy: panel.Enemies) {
            if (hitBox.intersects(enemy.hitBox)) {
                nextEnemyToRemove.add(enemy);
                resetPanel = true;
                //panel.reset();
                System.out.println("You Died");
                System.out.println("your points: " + coincounter);
            }
        }
        if (resetPanel){
            panel.reset();
        }
// Remove collected jump potions
        panel.jumpPotions.removeAll(jumpPotionsToRemove);

        panel.cameraX -= xspeed;
        y += yspeed;

        hitBox.x = x;
        hitBox.y = y;

        //death
        if (y > 800) {
            panel.reset();
        }
        // remove all coins
        panel.Coins.removeAll(coinsToRemove);



        hitBox.x = x;
        hitBox.y = y;

        //death
        if (y > 800) {
            panel.reset();
        }
    }

    public void draw(Graphics2D gtd) {
        if (step == 1) {
            mainCharacterLeft(gtd);
        } else if (step == 2) {
            mainCharacterRight(gtd);
        } else {
            gtd.setColor(Color.BLACK); //main body
            gtd.fillRect(x, y, width, height);
            gtd.setColor(Color.WHITE);
            gtd.fillOval(x + 30, y + 12, 8, 10);
            gtd.setColor(Color.WHITE);
            gtd.fillOval(x + 13, y + 12, 8, 10);
            gtd.setColor(Color.BLACK);
            gtd.fillOval(x + 32, y + 14, 4, 5);
            gtd.setColor(Color.BLACK);
            gtd.fillOval(x + 15, y + 14, 4, 5);
            gtd.setColor(Color.WHITE);
            gtd.fillRect(x + 10, y + 11, width - 40, height - 95);
            gtd.setColor(Color.WHITE);
            gtd.fillRect(x + 30, y + 11, width - 40, height - 95);
            gtd.setColor(Color.DARK_GRAY); //hat
            gtd.fillRect(x, y - 20, width, height - 75);
            gtd.setColor(Color.GRAY);
            gtd.fillRect(x + 13, y - 10, width - 46, height - 90);
            gtd.fillRect(x + 20, y - 10, width - 46, height - 90);
            gtd.fillRect(x + 27, y - 10, width - 46, height - 90);
            gtd.fillRect(x + 34, y - 10, width - 46, height - 90);
            gtd.setColor(Color.DARK_GRAY);
            int xTriangle[] = {x, x + 12, x + 25, x + 38, x + 50};
            int yTriangle[] = {y - 20, y - 27, y - 32, y - 27, y - 20}; // Adjust the height of the triangle as needed
            int points = 5;
            gtd.setColor(Color.DARK_GRAY); // Change the color if desired
            gtd.fillPolygon(xTriangle, yTriangle, points);
        }
    }
}