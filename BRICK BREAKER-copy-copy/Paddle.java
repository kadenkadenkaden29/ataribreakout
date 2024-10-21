import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle {
    private int x, y;
    private int width, height;
    private int speed = 8;
    public boolean movingLeft = false;
    public boolean movingRight = false;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int getWidth(){
        return width;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }

    public void move() {
        if (movingLeft) {
            x -= speed;
            if (x < 0) x = 0;
        }
        if (movingRight) {
            x += speed;
            if (x + width > 800) x = 800 - width;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            movingLeft = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            movingRight = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            movingLeft = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            movingRight = false;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}