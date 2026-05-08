package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class CharacterSelectPanel extends JPanel {
    private GameWindow game;
    private JTextArea textArea;
    private Character tempChosen;
    private Sprite warriorS, archerS, mageS, background;
    private JLabel warriorLabel, archerLabel, mageLabel;
    private Font smallFont;
    private Font mediumFont;

    public CharacterSelectPanel(GameWindow game) {
        this.game = game;
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        background = new Sprite("/EchoesOfTheOath/Resources/intro_bg.png", 426, 240, 121);
        smallFont = FontManager.getFont("Jersey10-Regular.ttf", 22f);
        mediumFont = FontManager.getFont("Jersey10-Regular.ttf", 26f);

        Warrior w = new Warrior();
        Archer a = new Archer();
        Mage m = new Mage();

        warriorS = w.getIdleSprite(); 
        archerS = a.getIdleSprite();
        mageS = m.getIdleSprite();

        setupUI(w, a, m);

        Timer animationTimer = new Timer(150, e -> {
            if (background != null) 
                background.update();

            if (warriorS != null) 
                warriorS.update();

            if (archerS != null) 
                archerS.update();

            if (mageS != null) 
                mageS.update();
            
            updateDisplayLabels(); 
            repaint(); 
        });
        animationTimer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null && background.isLoaded()) {
            g.drawImage(background.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
        }
    }

    private void setupUI(Warrior w, Archer a, Mage m) {
        JPanel selectionGrid = new JPanel(new GridLayout(1, 3, 20, 0));
        selectionGrid.setOpaque(false);
        selectionGrid.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 50));
        
        warriorLabel = new JLabel();
        archerLabel = new JLabel();
        mageLabel = new JLabel();
        
        selectionGrid.add(createCharButton("Warrior", warriorS, w, warriorLabel, "/EchoesOfTheOath/Resources/nation1_bg1.png"));
        selectionGrid.add(createCharButton("Archer", archerS, a, archerLabel, "/EchoesOfTheOath/Resources/nation2_bg1.png"));
        selectionGrid.add(createCharButton("Mage", mageS, m, mageLabel, "/EchoesOfTheOath/Resources/nation3_bg1.png"));

        add(selectionGrid, BorderLayout.CENTER);

        JPanel bottomContainer = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.BLACK); 
                g2.fillRoundRect(50, 10, getWidth()-100, getHeight()-20, 20, 20);
                g2.setColor(new Color(181, 153, 110));
                g2.setStroke(new BasicStroke(3)); 
                g2.drawRoundRect(50, 10, getWidth()-100, getHeight()-20, 20, 20);
            }
        };

        bottomContainer.setPreferredSize(new Dimension(1040, 220));
        bottomContainer.setOpaque(false);

        textArea = new JTextArea("Choose a Hero to begin your journey.");
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFont(mediumFont);
        textArea.setForeground(Color.WHITE);
        textArea.setMargin(new Insets(0, 80, 5, 80));

        JButton confirmBtn = new JButton("Confirm Hero Selection") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) 
                    g2.setColor(new Color(60, 60, 60));

                else if (getModel().isRollover()) 
                    g2.setColor(new Color(80, 80, 80));

                else 
                    g2.setColor(new Color(40, 40, 40));
                
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                
                g2.setColor(new Color(181, 153, 110));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

                g2.dispose();
                super.paintComponent(g);
            }
        };

        confirmBtn.setFont(mediumFont);
        confirmBtn.setForeground(new Color(175, 238, 171)); 
        confirmBtn.setContentAreaFilled(false);
        confirmBtn.setBorderPainted(false); 
        confirmBtn.setFocusable(false); 
        confirmBtn.setPreferredSize(new Dimension(965, 40));
        confirmBtn.addActionListener(e -> handleConfirmation());
        
        JPanel btnWrapper = new JPanel();
        btnWrapper.setOpaque(false);
        btnWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); 
        btnWrapper.add(confirmBtn);

        bottomContainer.add(btnWrapper, BorderLayout.NORTH);
        bottomContainer.add(textArea, BorderLayout.CENTER);
        add(bottomContainer, BorderLayout.SOUTH);
    }

    private JPanel createCharButton(String name, Sprite s, Character c, JLabel imgLabel, String bgPath) {
        JPanel panel = new JPanel(new BorderLayout()) {
        private Sprite localBG = new Sprite(bgPath, 1080, 720, 1);

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create(); 
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Shape roundEdges = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.setClip(roundEdges);

                if (localBG.isLoaded()) {
                    g2.drawImage(localBG.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
                }

                g2.setClip(null); 
                g2.setColor(new Color(181, 153, 110));
                g2.setStroke(new BasicStroke(3));
                g2.draw(roundEdges); 
                
                g2.dispose(); 
            }
    };
    
    panel.setOpaque(false); 

        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        if (s != null && s.isLoaded()) {
            s.update();

            Image scaledImg = s.getCurrentFrame().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(scaledImg));
        }

        JButton selectBtn = new JButton("Select " + name) {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (getModel().isPressed()) 
                g2.setColor(new Color(60, 60, 60));

            else if (getModel().isRollover()) 
                g2.setColor(new Color(80, 80, 80));

            else 
                g2.setColor(new Color(40, 40, 40));
            
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            
            g2.setColor(new Color(181, 153, 110));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);

            g2.dispose();
            super.paintComponent(g); 
        }
    };

        selectBtn.setFont(smallFont);
        selectBtn.setForeground(Color.WHITE);
        selectBtn.setContentAreaFilled(false);
        selectBtn.setBorderPainted(false); 
        selectBtn.setFocusable(false); 
        selectBtn.setPreferredSize(new Dimension(150, 40));
        selectBtn.addActionListener(e -> {
                tempChosen = c;
                textArea.setText(name + " Stats:\nHP: " + c.getHp() + " | Level: " + c.getLevel() + 
                            "\nDescription: " + c.getClassType());
            });
        
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imgLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        panel.add(imgLabel, BorderLayout.CENTER);
        panel.add(selectBtn, BorderLayout.SOUTH);

        return panel;
    }

    private void updateDisplayLabels() {
        updateSingleLabel(warriorS, warriorLabel);
        updateSingleLabel(archerS, archerLabel);
        updateSingleLabel(mageS, mageLabel);
    }

    private void updateSingleLabel(Sprite s, JLabel lbl) {
        if (s != null && s.isLoaded() && lbl != null) {
            Image scaled = s.getCurrentFrame().getScaledInstance(300, 300, Image.SCALE_FAST);
            lbl.setIcon(new ImageIcon(scaled));
        }
    }

    private void handleConfirmation() {
        if (tempChosen == null) {
            JOptionPane.showMessageDialog(this, "Please select a hero first!");
            return;
        }

        String name = showInputDialog(); 

        if (name != null && !name.trim().isEmpty()) {
            tempChosen.setName(name);
            tempChosen.setLevel(1);
            tempChosen.setGold(1000);
            tempChosen.setHp(tempChosen.getMaxHp());
            tempChosen.getInventory().clear();
            game.setChosenCharacter(tempChosen); 
            game.autosave(); 
            game.startNationTransition(1);
        }
    }

    private String showInputDialog() {
        final String[] result = {null};
        JDialog dialog = new JDialog(game.window, "Hero Registration", true);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0, 0, 0, 0)); 

        JPanel content = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2.setColor(new Color(20, 20, 20, 245));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                g2.setColor(new Color(181, 153, 110));
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(2, 2, getWidth()-5, getHeight()-5, 20, 20);
            }
        };
        content.setPreferredSize(new Dimension(400, 200));

        JLabel label = new JLabel("What is your hero's name?");
        label.setForeground(Color.WHITE);
        label.setFont(mediumFont);
        label.setBounds(40, 30, 320, 30);
        content.add(label);

        JTextField input = new JTextField();
        input.setBackground(new Color(40, 40, 40));
        input.setForeground(Color.WHITE);
        input.setCaretColor(Color.WHITE);
        input.setFont(mediumFont);
        input.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(181, 153, 110), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        input.setBounds(40, 70, 320, 40);

        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dialog.dispose(); 
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    result[0] = input.getText();
                    dialog.dispose();
                }
            }
        });

        content.add(input);

        JButton confirm = new JButton("Enter the Realm") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? new Color(60, 60, 60) : new Color(40, 40, 40));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(new Color(181, 153, 110));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                super.paintComponent(g);
            }
        };
        confirm.setBounds(100, 130, 200, 40);
        confirm.setFont(smallFont);
        confirm.setForeground(new Color(175, 238, 171));
        confirm.setContentAreaFilled(false);
        confirm.setBorderPainted(false);
        confirm.addActionListener(e -> {
            result[0] = input.getText();
            dialog.dispose();
        });
        content.add(confirm);

        dialog.add(content);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        return result[0];
    }
}