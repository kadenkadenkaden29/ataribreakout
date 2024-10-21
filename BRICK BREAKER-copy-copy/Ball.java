import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.awt.Rectangle;

public class Ball {
    private int x, y;
    private int width, height;
    private double dx = 3, dy = -3;
    private int brickCount = 104;
    public boolean reset = false;
    Paddle paddle = new Paddle(350, 550, 100, 10);

    public Ball(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0 || x + width > 800) {
            dx = -dx;
        }
        if (y < 0) {
            dy = -dy;
        }
        if (y + height > 600) {
            // Reset
            boolean reset = true;
            x = 400;
            y = 300;
            dx = 3;
            dy = -3;
        }
    }

    public void checkCollision(Paddle paddle, List<Brick> bricks) {
        Rectangle ballBounds = new Rectangle(x, y, width, height);
        Rectangle paddleBounds = paddle.getBounds();
        double left = paddleBounds.getMinX();
        double right = paddleBounds.getMaxX();
        if (ballBounds.intersects(paddleBounds)) {
            double ballCenter = ballBounds.getX() + (ballBounds.getWidth()/2);
            double paddleCenter = paddle.getX() + (paddle.getWidth()/2);
            double ballWidth = ballBounds.getWidth();
            double paddleWidth = paddle.getWidth();

            double dXY = Math.sqrt(dx*dx+dy*dy);
            double pos = (ballCenter - paddleCenter)/(paddleWidth/2);
            final double infX = 0.70;
            double newDX = dXY * pos * infX;
            if(paddle.movingLeft){
                if(newDX > 0){
                    newDX=newDX*-1;
                }
            } else if(paddle.movingRight){
                if(newDX < 0){
                    newDX = newDX*-1;
                }
            }
            dx = newDX*(1.1);

            double newDY = Math.sqrt(dXY*dXY - dx*dx)*(dy > 0? -1 : 1);
            dy = newDY*(1.1);

        }
        Iterator<Brick> it = bricks.iterator();
        while (it.hasNext()) {
            Brick brick = it.next();
            if (ballBounds.intersects(brick.getBounds())) {
                brickCount--;
                dy = -dy;
                it.remove();
                break;
            }

        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}