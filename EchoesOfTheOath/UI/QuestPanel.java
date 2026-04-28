package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class QuestPanel extends JPanel {
    private GameWindow game;
    private final int rows = 10;
    private final int cols = 10;
    private final int totalMines = 15;
    
    private int[][] logicGrid; 
    private TileButton[][] uiGrid;
    
    private boolean gameOver = false;
    private int safeTilesRevealed = 0;
    private final int totalSafeTiles = (rows * cols) - totalMines;

    private JLabel statusLabel;
    private JPanel gridPanel;
    
    private int startR = -1;
    private int startC = -1;

    public QuestPanel(GameWindow game) {
        this.game = game;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        setupTopPanel();
        
        gridPanel = new JPanel(new GridLayout(rows, cols, 5, 5));
        gridPanel.setOpaque(false);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 250, 60, 250));
        this.add(gridPanel, BorderLayout.CENTER);
    }

    private void setupTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        statusLabel = new JLabel("Disarm the Arcane Runes to proceed. Right-click to flag.", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        statusLabel.setForeground(new Color(181, 153, 110)); 

        topPanel.add(statusLabel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
    }

    public void startNewGame() {
        gridPanel.removeAll();
        logicGrid = new int[rows][cols];
        uiGrid = new TileButton[rows][cols];
        gameOver = false;
        safeTilesRevealed = 0;
        
        statusLabel.setText("Disarm the Arcane Runes! (Mines: " + totalMines + ")");
        statusLabel.setForeground(new Color(181, 153, 110));

        generateSolvableBoard();
        buildUI();

        if (startR != -1 && startC != -1) {
            revealTile(startR, startC);
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void generateSolvableBoard() {
        boolean validBoard = false;
        
        while (!validBoard) {
            for(int r=0; r<rows; r++) {
                for(int c=0; c<cols; c++) {
                    logicGrid[r][c] = 0;
                }
            }
            
            placeMines();
            calculateNumbers();
            
            for(int r=0; r<rows; r++) {
                for(int c=0; c<cols; c++) {
                    if (logicGrid[r][c] == 0) {
                        startR = r;
                        startC = c;
                        validBoard = true;
                        break;
                    }
                }
                if (validBoard) break;
            }
        }
    }

    private void placeMines() {
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int r = (int) (Math.random() * rows);
            int c = (int) (Math.random() * cols);

            if (logicGrid[r][c] != -1) {
                logicGrid[r][c] = -1;
                minesPlaced++;
            }
        }
    }

    private void calculateNumbers() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (logicGrid[r][c] == -1) continue;

                int count = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int nr = r + i;
                        int nc = c + j;
                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                            if (logicGrid[nr][nc] == -1) {
                                count++;
                            }
                        }
                    }
                }
                logicGrid[r][c] = count;
            }
        }
    }

    private void buildUI() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                TileButton btn = new TileButton(r, c);
                
                final int finalR = r;
                final int finalC = c;

                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (gameOver) return;

                        if (SwingUtilities.isRightMouseButton(e)) {
                            toggleFlag(finalR, finalC);
                        } else if (SwingUtilities.isLeftMouseButton(e)) {
                            revealTile(finalR, finalC);
                        }
                    }
                });

                uiGrid[r][c] = btn;
                gridPanel.add(btn);
            }
        }
    }

    private void toggleFlag(int r, int c) {
        TileButton btn = uiGrid[r][c];
        if (!btn.isEnabled() && !btn.getText().equals("F")) return; 

        if (btn.getText().equals("F")) {
            btn.setText("");
        } else {
            btn.setText("F");
            btn.setForeground(new Color(249, 152, 155)); 
        }
    }

    private void revealTile(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) return;
        
        TileButton btn = uiGrid[r][c];
        if (!btn.isEnabled() || btn.getText().equals("F")) return;

        btn.setEnabled(false); 

        if (logicGrid[r][c] == -1) {
            triggerExplosion(r, c);
        } else {
            safeTilesRevealed++;
            if (logicGrid[r][c] > 0) {
                btn.setText(String.valueOf(logicGrid[r][c]));
                setNumberColor(btn, logicGrid[r][c]);
            } else {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        revealTile(r + i, c + j);
                    }
                }
            }
            checkWinCondition();
        }
    }

    private void setNumberColor(JButton btn, int num) {
        switch (num) {
            case 1 -> btn.setForeground(new Color(175, 238, 171)); 
            case 2 -> btn.setForeground(new Color(135, 206, 250)); 
            case 3 -> btn.setForeground(new Color(249, 152, 155)); 
            default -> btn.setForeground(new Color(255, 215, 0));  
        }
    }

    private void triggerExplosion(int r, int c) {
        gameOver = true;
        uiGrid[r][c].setText("*");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (logicGrid[i][j] == -1) {
                    uiGrid[i][j].setText("*");
                }
            }
        }

        statusLabel.setText("A Rune Exploded!");
        statusLabel.setForeground(Color.RED);

        Timer delay = new Timer(2000, e -> {
            Character player = game.getChosenCharacter();
            int damage = 30; 
            player.setHp(player.getHp() - damage);

            if (player.getHp() <= 0) {
                game.showScreen("gameover");
            } else {
                JOptionPane.showMessageDialog(this, "The trap exploded! You took " + damage + " damage.");
                game.showScreen("story");
            }
        });
        delay.setRepeats(false);
        delay.start();
    }

    private void checkWinCondition() {
        if (safeTilesRevealed == totalSafeTiles) {
            gameOver = true;
            statusLabel.setText("Trap Disarmed!");
            statusLabel.setForeground(new Color(175, 238, 171));

            Timer delay = new Timer(2000, e -> {
                Character player = game.getChosenCharacter();
                player.setGold(player.getGold() + 200);
                JOptionPane.showMessageDialog(this, "Vault unlocked! You found 200 Gold!");
                game.showScreen("story");
            });
            delay.setRepeats(false);
            delay.start();
        }
    }

    private class TileButton extends JButton {
        private int r, c;

        public TileButton(int r, int c) {
            super("");
            this.r = r;
            this.c = c;
            setFocusable(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFont(new Font("Georgia", Font.BOLD, 18));
            setForeground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getText().equals("*")) {
                g2.setColor(new Color(150, 0, 0)); 
            } else if (!isEnabled()) {
                g2.setColor(new Color(20, 20, 20)); 
            } else if (getModel().isRollover()) {
                g2.setColor(new Color(60, 60, 60));
            } else {
                g2.setColor(new Color(40, 40, 40)); 
            }

            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);

            g2.setColor(new Color(181, 153, 110, 180));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);

            super.paintComponent(g);
        }
    }
}