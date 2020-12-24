package tetris2.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tetris2.game.Game;
import tetris2.game.StandartFloorCleaner;

public class Menu
extends JFrame {
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 350;
    private static final int BUTTON_WIDTH = 207;
    private static final int BUTTON_HEIGHT = 52;
    private Edit edits;
    private GamePanel gamePanel;
    private Control control;
    private JButton btnEdit;
    private JButton btnQuit;
    private JButton btnStart;
    private JButton btnControl;

    public Menu() throws CloneNotSupportedException {
        super("Тетрис 2.0 - Меню");
        this.initComponents();
        this.control = new Control(this);
        this.edits = new Edit(this);
        this.getContentPane().setBackground(new Color(43, 43, 43));
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);
    }

    private void initComponents() {
        this.btnQuit = new JButton();
        this.btnEdit = new JButton();
        this.btnStart = new JButton();
        this.btnControl = new JButton();
        this.setDefaultCloseOperation(3);
        this.btnQuit.setBackground(new Color(39, 70, 132));
        this.btnQuit.setFont(new Font("Tahoma", 0, 18));
        this.btnQuit.setText("\u0412\u044b\u0439\u0442\u0438");
        this.btnQuit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                Menu.this.btnQuitActionPerformed(evt);
            }
        });
        this.btnEdit.setBackground(new Color(39, 70, 132));
        this.btnEdit.setFont(new Font("Tahoma", 0, 18));
        this.btnEdit.setText("\u0418\u0437\u043c\u0435\u043d\u0438\u0442\u044c \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438");
        this.btnEdit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                Menu.this.btnEditActionPerformed(evt);
            }
        });
        this.btnStart.setBackground(new Color(39, 70, 132));
        this.btnStart.setFont(new Font("Tahoma", 0, 18));
        this.btnStart.setText("\u041d\u0430\u0447\u0430\u0442\u044c \u0438\u0433\u0440\u0443");
        this.btnStart.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                Menu.this.btnStartActionPerformed(evt);
            }
        });
        this.btnControl.setBackground(new Color(39, 70, 132));
        this.btnControl.setFont(new Font("Tahoma", 0, 18));
        this.btnControl.setText("Управление");
        this.btnControl.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                Menu.this.btnControlActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
    
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, true)
                .addGap(350, 350, 350)
                                .addComponent(this.btnQuit, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.btnEdit, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.btnStart, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.btnControl, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, true))
                        .addGap(50, 50, 50)
                        .addComponent(this.btnStart, GroupLayout.PREFERRED_SIZE, BUTTON_HEIGHT, GroupLayout.PREFERRED_SIZE)
                        .addComponent(this.btnEdit, GroupLayout.PREFERRED_SIZE, BUTTON_HEIGHT, GroupLayout.PREFERRED_SIZE)
                        .addComponent(this.btnControl, GroupLayout.PREFERRED_SIZE, BUTTON_HEIGHT, GroupLayout.PREFERRED_SIZE)
                        .addComponent(this.btnQuit, GroupLayout.PREFERRED_SIZE, BUTTON_HEIGHT, GroupLayout.PREFERRED_SIZE));
        this.pack();
    }

    private void btnQuitActionPerformed(ActionEvent evt) {
        this.exit();
    }

    public void exit() {
        this.edits.dispose();
        this.control.dispose();
        this.dispose();
        System.exit(0);
    }

    private void btnEditActionPerformed(ActionEvent evt) {
        this.edits.setVisible(true);
        this.setVisible(false);
    }
    
    private void btnControlActionPerformed(ActionEvent evt) {
        this.control.setVisible(true);
        this.setVisible(false);
    }

    private void btnStartActionPerformed(ActionEvent evt) {
        try {
            this.gamePanel = new GamePanel(this,
                    new Game(this.edits.getGlassWidth(), this.edits.getGlassHeight(),
                    this.edits.getGlassFloorCleaner()));
        }
        catch (CloneNotSupportedException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.gamePanel.setVisible(true);
        this.setVisible(false);
        this.gamePanel.requestFocus();
        this.gamePanel.requestFocusInWindow();
        try {
            this.gamePanel.getGame().start();
        }
        catch (CloneNotSupportedException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (!"Nimbus".equals(info.getName())) continue;
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                try {
                    new Menu().setVisible(true);
                }
                catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}

