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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tetris2.views.Menu;

public class Control
extends JFrame {
    private Menu menu;
    private JButton btnOK;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;

    public Control(Menu menu) {
        super("Тетрис 2.0 - Управление");
        this.initComponents();
        this.menu = menu;
        this.setLocation(500, 300);
        this.getContentPane().setBackground(new Color(43, 43, 43));
        this.setSize(350, 350);
        this.setResizable(false);
    }

    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jLabel5 = new JLabel();
        this.jLabel6 = new JLabel();
        this.btnOK = new JButton();
        this.jLabel1.setFont(new Font("Tahoma", 0, 14));
        this.jLabel1.setForeground(Color.WHITE);
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("Управление:");
        this.jLabel2.setFont(new Font("Tahoma", 0, 14));
        this.jLabel2.setForeground(Color.WHITE);
        this.jLabel2.setHorizontalAlignment(0);
        this.jLabel2.setText("Влево - \u2190");
        this.jLabel3.setFont(new Font("Tahoma", 0, 14));
        this.jLabel3.setForeground(Color.WHITE);
        this.jLabel3.setHorizontalAlignment(0);
        this.jLabel3.setText("Вправо - \u2192");
        this.jLabel4.setFont(new Font("Tahoma", 0, 14));
        this.jLabel4.setForeground(Color.WHITE);
        this.jLabel4.setHorizontalAlignment(0);
        this.jLabel4.setText("Поворот по часовой стрелке - X");
        this.jLabel5.setFont(new Font("Tahoma", 0, 14));
        this.jLabel5.setForeground(Color.WHITE);
        this.jLabel5.setHorizontalAlignment(0);
        this.jLabel5.setText("Поворот против часовой стрелки - Z");
        this.jLabel6.setFont(new Font("Tahoma", 0, 14));
        this.jLabel6.setForeground(Color.WHITE);
        this.jLabel6.setHorizontalAlignment(0);
        this.jLabel6.setText("Вниз - \u2193");
        this.btnOK.setBackground(new Color(39, 70, 132));
        this.btnOK.setFont(new Font("Tahoma", 0, 18));
        this.btnOK.setText("OK");
        this.btnOK.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                Control.this.btnOKActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGap(350, 350, 350)
                                .addComponent(this.jLabel1)
                                .addComponent(this.jLabel2)
                                .addComponent(this.jLabel3)
                                .addComponent(this.jLabel4)
                                .addComponent(this.jLabel5)
                                .addComponent(this.jLabel6)
                                .addComponent(this.btnOK, -2, 81, -2)
                                .addGap(111, 111, 111)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup()
                        .addComponent(this.jLabel1))
                .addGroup(layout.createParallelGroup()
                        .addComponent(this.jLabel2))
                .addGroup(layout.createParallelGroup()
                        .addComponent(this.jLabel3))
                .addGroup(layout.createParallelGroup()
                        .addComponent(this.jLabel6))
                .addGroup(layout.createParallelGroup()
                        .addComponent(this.jLabel4))
                .addGroup(layout.createParallelGroup()
                        .addComponent(this.jLabel5))
                .addGroup(layout.createParallelGroup()
                        .addComponent(this.btnOK, -2, 50, -2)));
        this.pack();
    }

    private void btnOKActionPerformed(ActionEvent evt) {
        this.menu.setVisible(true);
        this.setVisible(false);
    }
}


