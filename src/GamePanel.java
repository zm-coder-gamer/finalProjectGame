import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;

public class GamePanel extends javax.swing.JPanel implements ActionListener {

    Player player;
    List<Wall> walls = new ArrayList<>();
    List<jumpPotion> jumpPotions = new ArrayList<>();
    List<coin> Coins = new ArrayList<>();

    List<Enemy> Enemies = new ArrayList<>();
    List<EndSpike> EndSpikes = new ArrayList<>();
    int cameraX;
    int offset;

    Timer gameTimer;

    Rectangle restartRect;
    Rectangle homeRect;

    Font buttonFont = new Font("Arial", Font.BOLD, 30);

    public GamePanel() {

        restartRect = new Rectangle(550, 25, 50, 50);
        homeRect = new Rectangle(625, 25, 50, 50);

        player = new Player(400, 300, this);

        reset();
        makeJumpPotion();
        makeCoin();
        makeEnemy();
        makeEndSpike();


        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {

            @Override
            public void run() {

                player.set();
                for (Wall wall : walls) {
                    wall.set(cameraX);
                }
                for (jumpPotion jP : jumpPotions) {
                    jP.set(cameraX);
                }
                for (coin c : Coins) {
                    c.set(cameraX);
                }

                for (Enemy enemy : Enemies) {
                    enemy.set(cameraX);
                }
                for (EndSpike e : EndSpikes) {
                    e.set(cameraX);
                }

                for (int i = 0; i < walls.size(); i++) {
                    if (walls.get(i).x < -800) {
                        walls.remove(i);
                    }
                }

                repaint();
            }

        }, 0, 17);
    }

    public void reset() {
        player.x = 250;
        player.y = 500;
        cameraX = 150;
        player.xspeed = 0;
        player.yspeed = 0;
        walls.clear();
        jumpPotions.clear();
        Coins.clear();

        Enemies.clear();
        EndSpikes.clear();

        offset = -150;
        makeWalls(offset);

        makeCoin();
        makeJumpPotion();
        makeEnemy();
        makeEndSpike();

    }

    public void makeWalls(int offset) {
        int s = 50;
        //starting floor
        if (player.wallIndex == 0) {
            walls.add(new Wall(offset + 50, 600, s, s));
            walls.add(new Wall(offset + 100, 600, s, s));
            walls.add(new Wall(offset + 150, 600, s, s));
            walls.add(new Wall(offset + 200, 600, s, s));
            walls.add(new Wall(offset + 250, 600, s, s));
            walls.add(new Wall(offset + 300, 600, s, s));
            walls.add(new Wall(offset + 350, 600, s, s));
            walls.add(new Wall(offset + 400, 600, s, s));
            walls.add(new Wall(offset + 450, 600, s, s));
            // 1st parkour
            walls.add(new Wall(offset + 550, 400, s, s));
            walls.add(new Wall(offset + 600, 400, s, s));
            walls.add(new Wall(offset + 650, 400, s, s));
            walls.add(new Wall(offset + 700, 400, s, s));

            // 2nd parkour
            walls.add(new Wall(offset + 1100, 500, s, s));
            walls.add(new Wall(offset + 1300, 500, s, s));
            walls.add(new Wall(offset + 1500, 500, s, s));
            walls.add(new Wall(offset + 1650, 500, s, s));
            walls.add(new Wall(offset + 1700, 400, s, s));
            walls.add(new Wall(offset + 1750, 300, s, s));
            walls.add(new Wall(offset + 1800, 200, s, s));
            // 3rd parkour
            walls.add(new Wall(offset + 2000, 350, s, s));
            walls.add(new Wall(offset + 2050, 350, s, s));
            walls.add(new Wall(offset + 2100, 350, s, s));
            walls.add(new Wall(offset + 2150, 350, s, s));
            walls.add(new Wall(offset + 2200, 350, s, s));
            //4th parkour
            walls.add(new Wall(offset + 2400, 350, s, s));
            walls.add(new Wall(offset + 2450, 350, s, s));
            walls.add(new Wall(offset + 2500, 350, s, s));
            walls.add(new Wall(offset + 2550, 350, s, s));
            walls.add(new Wall(offset + 2600, 350, s, s));
            walls.add(new Wall(offset + 2650, 350, s, s));
        }
        else if (player.wallIndex == 1) {
            walls.add(new Wall(offset + 50, 600, s, s));
            walls.add(new Wall(offset + 100, 600, s, s));
            walls.add(new Wall(offset + 150, 600, s, s));
            walls.add(new Wall(offset + 200, 600, s, s));
            walls.add(new Wall(offset + 250, 600, s, s));
            walls.add(new Wall(offset + 300, 600, s, s));
            walls.add(new Wall(offset + 350, 600, s, s));
            walls.add(new Wall(offset + 400, 600, s, s));
            walls.add(new Wall(offset + 450, 600, s, s));

            // 1st parkour
            walls.add(new Wall(offset + 550, 350, s, s));
            walls.add(new Wall(offset + 600, 450, s, s));
            walls.add(new Wall(offset + 650, 350, s, s));
            walls.add(new Wall(offset + 700, 450, s, s));

            // 2nd parkour
            walls.add(new Wall(offset + 1100, 500, s, s));
            walls.add(new Wall(offset + 1300, 500, s, s));
            walls.add(new Wall(offset + 1500, 500, s, s));
            walls.add(new Wall(offset + 1650, 500, s, s));
            walls.add(new Wall(offset + 1700, 400, s, s));
            walls.add(new Wall(offset + 1750, 300, s, s));
            walls.add(new Wall(offset + 1800, 200, s, s));
            // 3rd parkour
            walls.add(new Wall(offset + 2000, 350, s, s));
            walls.add(new Wall(offset + 2050, 350, s, s));
            walls.add(new Wall(offset + 2100, 350, s, s));
            walls.add(new Wall(offset + 2150, 350, s, s));
            walls.add(new Wall(offset + 2200, 350, s, s));
            //4th parkour
            walls.add(new Wall(offset + 2400, 350, s, s));
            walls.add(new Wall(offset + 2450, 350, s, s));
            walls.add(new Wall(offset + 2500, 350, s, s));
            walls.add(new Wall(offset + 2550, 350, s, s));
            walls.add(new Wall(offset + 2600, 350, s, s));
            walls.add(new Wall(offset + 2650, 350, s, s));
        }



    }
    public void makeCoin() {
        int s = 50;
        if (player.wallIndex == 0) {
            Coins.add(new coin(625, 200, s, s));
            Coins.add(new coin(offset + 1400, 400, s, s));
            Coins.add(new coin(offset + 1200, 400, s, s));
        }
        else if (player.wallIndex == 1) {
            Coins.add(new coin(625, 200, s, s));
            Coins.add(new coin(offset + 1400, 400, s, s));
            Coins.add(new coin(offset + 1200, 400, s, s));
        }
}

    public void makeJumpPotion() {
        int s = 50;
        if (player.wallIndex == 0) {
            jumpPotions.add(new jumpPotion(300, 300, s + 10, s));
            jumpPotions.add(new jumpPotion(750, 300, s + 10, s));

            // underneath parkour
            jumpPotions.add(new jumpPotion(offset + 2000, 400, s + 10, s));
            jumpPotions.add(new jumpPotion(offset + 2150, 400, s + 10, s));
            jumpPotions.add(new jumpPotion(offset + 2300, 400, s + 10, s));
            jumpPotions.add(new jumpPotion(offset + 2300, 250, s + 10, s));
            jumpPotions.add(new jumpPotion(offset + 2300, 150, s + 10, s));
        }
        else if (player.wallIndex == 1) {
            jumpPotions.add(new jumpPotion(300, 300, s + 10, s));
            jumpPotions.add(new jumpPotion(750, 300, s + 10, s));

            // underneath parkour
            jumpPotions.add(new jumpPotion(offset + 2000, 400, s + 10, s));
            jumpPotions.add(new jumpPotion(offset + 2150, 400, s + 10, s));
            jumpPotions.add(new jumpPotion(offset + 2300, 400, s + 10, s));
            jumpPotions.add(new jumpPotion(offset + 2300, 250, s + 10, s));
            jumpPotions.add(new jumpPotion(offset + 2300, 150, s + 10, s));
        }

    }

    public void makeEnemy() {
        int s = 50;
        if (player.wallIndex == 0) {
            Enemies.add(new Enemy(offset + 350, 575, s, s));
            Enemies.add(new Enemy(offset + 400, 575, s, s));


            Enemies.add(new Enemy(offset + 650, 375, s, s));
            int newOffset = offset;

            Enemies.add(new Enemy(offset + 1650, 475, s, s));

            //underneath spikes
            Enemies.add(new Enemy(offset + 2000, 325, s, s));
            Enemies.add(new Enemy(offset + 2050, 325, s, s));
            Enemies.add(new Enemy(offset + 2100, 325, s, s));
            Enemies.add(new Enemy(offset + 2150, 325, s, s));
            Enemies.add(new Enemy(offset + 2200, 325, s, s));
        }
        else if (player.wallIndex == 1) {
            Enemies.add(new Enemy(offset + 350, 575, s, s));
            Enemies.add(new Enemy(offset + 400, 575, s, s));


            Enemies.add(new Enemy(offset + 650, 375, s, s));
            int newOffset = offset;

            Enemies.add(new Enemy(offset + 1650, 475, s, s));

            //underneath spikes
            Enemies.add(new Enemy(offset + 2000, 325, s, s));
            Enemies.add(new Enemy(offset + 2050, 325, s, s));
            Enemies.add(new Enemy(offset + 2100, 325, s, s));
            Enemies.add(new Enemy(offset + 2150, 325, s, s));
            Enemies.add(new Enemy(offset + 2200, 325, s, s));
        }
    }

    public void makeEndSpike() {
        if (player.wallIndex == 0) {
            int s = 50;
            EndSpikes.add(new EndSpike(offset + 2650, 325, s, s));
        }
        else if (player.wallIndex == 1) {
            int s = 50;
            EndSpikes.add(new EndSpike(offset + 2650, 325, s, s));
        }
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D gtd = (Graphics2D) g;

        player.draw(gtd);

        for (Wall wall : walls) {
            wall.draw(gtd);
        }

        gtd.drawString("R", 564, 60);
        gtd.drawString("H", 639, 60);

        for (jumpPotion jP : jumpPotions) {
            jP.draw(gtd);
        }
        for (coin c : Coins) {
            c.draw(gtd);
        }


        for (Enemy enemy : Enemies) {
            enemy.draw(gtd);
        }
        for (EndSpike e : EndSpikes) {
            e.draw(gtd);
        }
    }

    void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'a') {
            player.keyLeft = true;
        }
        if (e.getKeyChar() == 'w') {
            player.keyUp = true;
        }
        if (e.getKeyChar() == 's') {
            player.keyDown = true;
        }
        if (e.getKeyChar() == 'd') {
            player.keyRight = true;
        }
        if (e.getKeyChar() == 'r') {
            reset();
        }
    }

    void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'a') {
            player.keyLeft = false;
        }
        if (e.getKeyChar() == 'w') {
            player.keyUp = false;
        }
        if (e.getKeyChar() == 's') {
            player.keyDown = false;
        }
        if (e.getKeyChar() == 'd') {
            player.keyRight = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        System.out.println("Error");
    }
}
