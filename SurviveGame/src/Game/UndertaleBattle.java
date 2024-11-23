package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UndertaleBattle extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private int playerX, playerY, playerWidth, playerHeight;
    private ArrayList<Bullet> bullets;
    private int screenWidth = 800, screenHeight = 600;
    private boolean inGame = false; // Indica se o jogo está em execução
    private boolean gameOver = false; // Indica se o jogo terminou
    private long startTime; // Para calcular o tempo decorrido
    private int bulletSpeedIncrement = 0; // Incremento na velocidade das balas

    public UndertaleBattle() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        playerX = 400;
        playerY = 500;
        playerWidth = 30; // Tamanho ajustado do retângulo
        playerHeight = 30; // Tamanho ajustado do retângulo

        bullets = new ArrayList<>();
        spawnBullets();

        timer = new Timer(16, this); // ~60 FPS
    }

    private void spawnBullets() {
        bullets.clear();
        for (int i = 0; i < 10; i++) {
            bullets.add(new Bullet((int) (Math.random() * screenWidth), 0, 10, 2 + (int) (Math.random() * 3)));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame && !gameOver) {
            // Atualizar tempo decorrido
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

            // Aumentar a velocidade das balas a cada 10 segundos
            if (elapsedTime % 10 == 0) {
                bulletSpeedIncrement = (int) elapsedTime / 10;
            }

            // Atualizar balas
            for (Bullet b : bullets) {
                b.move(bulletSpeedIncrement);
            }

            // Verificar colisões
            checkCollisions();

            repaint();
        }
    }

    private void checkCollisions() {
        // Verificar colisão do jogador com balas
        for (Bullet b : bullets) {
            if (new Rectangle(playerX, playerY, playerWidth, playerHeight).intersects(
                    new Rectangle(b.getX(), b.getY(), b.getSize(), b.getSize()))) {
                gameOver = true; // Aciona o fim do jogo
                timer.stop();    // Para o loop do jogo
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!inGame) {
            // Tela inicial
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("Pressione ENTER para começar", 200, 300);
        } else if (gameOver) {
            // Tela de Game Over
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("GAME OVER", 250, 250);

            // Mensagem para reiniciar o jogo
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.setColor(Color.WHITE);
            g.drawString("Deseja começar de novo? Aperte 1", 230, 300);
        } else {
            // Jogo em execução

            // Desenhar jogador (retângulo)
            g.setColor(Color.WHITE);
            g.fillRect(playerX, playerY, playerWidth, playerHeight);

            // Desenhar balas
            g.setColor(Color.RED);
            for (Bullet b : bullets) {
                g.fillOval(b.getX(), b.getY(), b.getSize(), b.getSize());
            }

            // Desenhar tempo decorrido
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.setColor(Color.GREEN);
            g.drawString("Tempo: " + elapsedTime + "s", 10, 20);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Movimentar o jogador com as setas
        if (inGame && !gameOver) {
            if (key == KeyEvent.VK_LEFT && playerX > 0) {
                playerX -= 10; // Mover para a esquerda
            }
            if (key == KeyEvent.VK_RIGHT && playerX < screenWidth - playerWidth) {
                playerX += 10; // Mover para a direita
            }
            if (key == KeyEvent.VK_UP && playerY > 0) {
                playerY -= 10; // Mover para cima
            }
            if (key == KeyEvent.VK_DOWN && playerY < screenHeight - playerHeight) {
                playerY += 10; // Mover para baixo
            }
        }

        if (key == KeyEvent.VK_ENTER && !inGame && !gameOver) {
            // Começar o jogo
            inGame = true;
            gameOver = false;
            startTime = System.currentTimeMillis(); // Iniciar cronômetro
            timer.start();
        }

        if (key == KeyEvent.VK_1 && gameOver) {
            // Reiniciar o jogo
            inGame = true;
            gameOver = false;
            playerX = 400;
            playerY = 500;
            spawnBullets();
            startTime = System.currentTimeMillis(); // Iniciar cronômetro
            timer.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Undertale Battle");
        UndertaleBattle game = new UndertaleBattle();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Classe Bullet para representar as balas
    class Bullet {
        private int x, y, size, speed;

        public Bullet(int x, int y, int size, int speed) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = speed;
        }

        public void move(int speedIncrement) {
            y += speed + speedIncrement; // Aumenta a velocidade a cada intervalo de tempo
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getSize() {
            return size;
        }
    }
}
