import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements ActionListener {
    private final int width = 800;
    private final int height = 600;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;
    private Timer timer;
    public int lifeCount = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        paddle = new Paddle(width / 2 - 50, height - 50, 100, 10);
        ball = new Ball(width / 2 - 10, height / 2 - 10, 15, 15);
        bricks = new ArrayList<>();
        initBricks();
        timer = new Timer(10, this);
        timer.start();
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    paddle.keyPressed(e);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    paddle.keyReleased(e);
                }
            });
        requestFocusInWindow();
    }

    private void initBricks() {
        int rows = 8;
        int cols = 10;
        int brickWidth = 75;
        int brickHeight = 18;
        int spacing = 3;
        int offsetX = 10;
        int offsetY = 10;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = offsetX + j * (brickWidth + spacing);
                int y = offsetY + i * (brickHeight + spacing);
                bricks.add(new Brick(x, y, brickWidth, brickHeight));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paddle.draw(g);
        ball.draw(g);
        for (Brick brick : bricks) {
            brick.draw(g);
        }
        g.setColor(Color.WHITE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        paddle.move();
        ball.move();
        ball.checkCollision(paddle, bricks);
        repaint();
    }
}