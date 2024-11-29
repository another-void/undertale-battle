package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;

public class UndertaleBattle extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
    private Timer timer;
    private int playerX, playerY, playerWidth, playerHeight;
    private ArrayList<Bullet> bullets;
    private int screenWidth = 800, screenHeight = 600;
    private boolean inGame = false; // Indica se o jogo está em execução
    private boolean gameOver = false; // Indica se o jogo terminou
    private long startTime; // Para calcular o tempo decorrido
    private int bulletSpeedIncrement = 0; // Incremento na velocidade das balas
    private HashSet<Integer> activeKeys; // Conjunto de teclas atualmente pressionadas
    private int playerSpeed = 5; // Velocidade do jogador

    private boolean hoverYes = false, hoverNo = false; // Estados de hover das opções
    private Rectangle yesRect, noRect; // Áreas das opções

    public UndertaleBattle() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        playerX = 400;
        playerY = 500;
        playerWidth = 30;
        playerHeight = 30;

        bullets = new ArrayList<>();
        spawnBullets();

        activeKeys = new HashSet<>();
        timer = new Timer(7, this); // ~144 FPS

        // Definir áreas das opções
        yesRect = new Rectangle(300, 350, 80, 40);
        noRect = new Rectangle(450, 350, 80, 40);
    }

    private void spawnBullets() {
        for (int i = 0; i < 10; i++) {
            bullets.add(new Bullet((int) (Math.random() * screenWidth), 0, 10, 2 + (int) (Math.random() * 3)));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame && !gameOver) {
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            if (elapsedTime % 3 == 0) {
                bulletSpeedIncrement = (int) elapsedTime / 3;
            }
            for (int i = 0; i < bullets.size(); i++) {
                Bullet b = bullets.get(i);
                b.move(bulletSpeedIncrement);
                if (b.getY() > screenHeight) {
                    bullets.remove(i);
                    bullets.add(new Bullet((int) (Math.random() * screenWidth), 0, 10, 2 + (int) (Math.random() * 3)));
                    i--;
                }
            }
            handlePlayerMovement();
            checkCollisions();
            repaint();
        }
    }

    private void handlePlayerMovement() {
        if (activeKeys.contains(KeyEvent.VK_LEFT) && playerX > 0) {
            playerX -= playerSpeed;
        }
        if (activeKeys.contains(KeyEvent.VK_RIGHT) && playerX < screenWidth - playerWidth) {
            playerX += playerSpeed;
        }
        if (activeKeys.contains(KeyEvent.VK_UP) && playerY > 0) {
            playerY -= playerSpeed;
        }
        if (activeKeys.contains(KeyEvent.VK_DOWN) && playerY < screenHeight - playerHeight) {
            playerY += playerSpeed;
        }
    }

    private void checkCollisions() {
        for (Bullet b : bullets) {
            if (new Rectangle(playerX, playerY, playerWidth, playerHeight).intersects(
                    new Rectangle(b.getX(), b.getY(), b.getSize(), b.getSize()))) {
                gameOver = true;
                timer.stop();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!inGame) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("Pressione ENTER para começar", 160, 300);
        } else if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("GAME OVER", 250, 250);

            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.setColor(Color.WHITE);
            g.drawString("Deseja começar de novo?", 255, 300);

            if (hoverYes) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString("SIM", 300, 380);

            if (hoverNo) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString("NÃO", 450, 380);
        } else {
            g.setColor(Color.RED);
            g.fillRect(playerX, playerY, playerWidth, playerHeight);

            g.setColor(Color.WHITE);
            for (Bullet b : bullets) {
                g.fillOval(b.getX(), b.getY(), b.getSize(), b.getSize());
            }

            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.setColor(Color.GREEN);
            g.drawString("Tempo: " + elapsedTime + "s", 10, 20);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        hoverYes = yesRect.contains(e.getPoint());
        hoverNo = noRect.contains(e.getPoint());
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameOver) {
            if (yesRect.contains(e.getPoint())) {
                inGame = true;
                gameOver = false;
                playerX = 400;
                playerY = 500;
                bullets.clear();
                spawnBullets();
                startTime = System.currentTimeMillis();
                timer.start();
            } else if (noRect.contains(e.getPoint())) {
                System.exit(0);
            }
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        activeKeys.add(key);
        if (key == KeyEvent.VK_ENTER && !inGame) {
            inGame = true;
            gameOver = false;
            startTime = System.currentTimeMillis();
            timer.start();
        }
    }

    @Override public void keyReleased(KeyEvent e) {
        activeKeys.remove(e.getKeyCode());
    }
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Undertale Battle");
        UndertaleBattle game = new UndertaleBattle();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class Bullet {
        private int x, y, size, speed;
        public Bullet(int x, int y, int size, int speed) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = speed;
        }
        public void move(int speedIncrement) {
            y += speed + speedIncrement;
        }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getSize() { return size; }
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
