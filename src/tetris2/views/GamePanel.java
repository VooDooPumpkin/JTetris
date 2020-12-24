package tetris2.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tetris2.game.Game;
import tetris2.game.events.GameListener;
import tetris2.game.events.GlassEvent;
import tetris2.game.events.GlassListener;

public final class GamePanel
extends JFrame {
    private static final int CONTROL_PN_SIZE = 270;
    private Menu menu;
    private Game game;
    private GlassPanel glassPanel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel lbScore;
    private PainNextFigure painNext;
    private JPanel pnControl;

    public GamePanel(final Menu menu, Game game) throws CloneNotSupportedException {
        super("Тетрис 2.0 - Игра");
        this.menu = menu;
        this.game = game;
        this.game.addGameListener(new GameObserver());
        this.glassPanel = new GlassPanel(this.game.getGlass());
        this.initComponents();
        this.reSize(game.getGlass()._getWidth(), this.game.getGlass()._getHeight());
        this.glassPanel.setFocusable(true);
        this.glassPanel.requestFocusInWindow();
        this.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keycode = e.getKeyCode();
                if (keycode == KeyEvent.VK_ESCAPE) {
                    GamePanel.this.exit();
                    menu.setVisible(true);
                } else {
                    try {
                        GamePanel.this.game.getGlass().myKeyListener(e);
                    }
                    catch (CloneNotSupportedException ex) {
                        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLocation(450, 100);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(43, 43, 43));
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                GamePanel.this.exit();
                menu.exit();
            }
        });
    }

    /**
     *Обновить счёт
     */
    private void _setScore() {
        this.lbScore.setText("" + this.game.getScore());
    }
    
    public Game getGame(){
        return this.game;
    }

    /**
     *Изменить размер игровой панели
     */
    private void reSize(int width, int height) {
        this.glassPanel.setSize(width * GlassPanel.CELL_SIZE, height * GlassPanel.CELL_SIZE);
        this.glassPanel.setPreferredSize(new Dimension(width * GlassPanel.CELL_SIZE, height * GlassPanel.CELL_SIZE));
        this.pnControl.setSize(CONTROL_PN_SIZE, height * GlassPanel.CELL_SIZE + 1);
        this.pnControl.setPreferredSize(new Dimension(CONTROL_PN_SIZE, height * GlassPanel.CELL_SIZE + 1));
        this.setLocation(500, 160);
        this.setSize(this.glassPanel.getWidth() + this.pnControl.getWidth() + 4, this.glassPanel.getHeight() + 39);
        this.setResizable(false);
    }

    /**
     *Выйти
     */
    public void exit() {
        this.game.finishGame();
        this.dispose();
    }

    /**
     *Инициализировать компоненты игровой панели
     */
    private void initComponents() {
        this.pnControl = new JPanel();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.lbScore = new JLabel();
        this.painNext = new PainNextFigure();
        this.setDefaultCloseOperation(3);
        this.pnControl.setBackground(new Color(43, 43, 43));
        this.pnControl.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        this.jLabel1.setFont(new Font("Tahoma", 0, 14));
        this.jLabel1.setForeground(Color.WHITE);
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("Очки:");
        this.lbScore.setFont(new Font("Tahoma", 0, 14));
        this.lbScore.setForeground(Color.green);
        this.lbScore.setHorizontalAlignment(0);
        this.lbScore.setText("00");
        this.jLabel2.setFont(new Font("Tahoma", 0, 14));
        this.jLabel2.setForeground(Color.WHITE);
        this.jLabel2.setHorizontalAlignment(0);
        this.jLabel2.setText("Следующая фигура:");
        this.jLabel3.setFont(new Font("Tahoma", 0, 14));
        this.jLabel3.setForeground(Color.WHITE);
        this.jLabel3.setHorizontalAlignment(0);
        this.jLabel3.setText("Меню - Esc");        
        this.painNext.setBackground(new Color(20, 26, 69));
        this.painNext.setPreferredSize(new Dimension(150, 120));
        GroupLayout painNextLayout = new GroupLayout(this.painNext);
        this.painNext.setLayout(painNextLayout);
        painNextLayout.setHorizontalGroup(painNextLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 150, 32767));
        painNextLayout.setVerticalGroup(painNextLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 120, 32767));
        GroupLayout pnControlLayout = new GroupLayout(this.pnControl);
        this.pnControl.setLayout(pnControlLayout);
        
        pnControlLayout.setAutoCreateGaps(true);
        pnControlLayout.setAutoCreateContainerGaps(true);
        
        pnControlLayout.setHorizontalGroup(pnControlLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGap(270, 270, 270)
                .addGroup(pnControlLayout.createSequentialGroup()
                        .addComponent(this.jLabel1, -2, 50, -2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.lbScore, -2, 47, -2))
                .addComponent(this.jLabel2)
                .addComponent(this.painNext, -2, -1, -2)
                .addComponent(this.jLabel3));
        
        pnControlLayout.setVerticalGroup(pnControlLayout.createSequentialGroup()
                        .addGroup(pnControlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.jLabel1, -1, 27, 32767)
                                .addComponent(this.lbScore, -1, -1, 32767))
                        .addGroup(pnControlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE, true)
                        .addComponent(this.jLabel2, -1, -1, 32767))
                        .addGroup(pnControlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE, true)
                        .addComponent(this.painNext, -2, -1, -2))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.jLabel3, -1, -1, 32767));
        GroupLayout glassLayout = new GroupLayout(this.glassPanel);
        this.glassPanel.setLayout(glassLayout);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(this.glassPanel, -2, -1, -2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.pnControl, -2, -1, -2)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(this.glassPanel, -1, -1, 32767)
                                .addComponent(this.pnControl, -1, -1, 32767))
                        .addGap(0, 0, 32767)));
        this.pack();
    }
    
    class GameObserver
        implements GameListener {

        public GameObserver(){
        }

        @Override
        public void newNextFigure() {
            try {
                GamePanel.this.painNext.setFigure(GamePanel.this.game.getNextFigure());
                GamePanel.this.painNext.repaint();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void scoreChanged() {
            GamePanel.this._setScore();
        }

        @Override
        public void gameOver() {
            JOptionPane.showMessageDialog(null, "GAME-OVER!!!", "GAME - OVER", 0);
        }

        
    }
}

