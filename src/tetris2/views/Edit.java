package tetris2.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tetris2.game.Game;
import tetris2.game.StandartFloorCleaner;
import tetris2.game.TFloorCleaner;
import tetris2.game.WholeFigureX10FloorCleaner;
import tetris2.views.Menu;

public class Edit
extends JFrame {
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 330;
    private static final int MIN_GLASS_HEIGHT = 10;
    private static final int MAX_GLASS_HEIGHT = 30;
    private static final int MIN_GLASS_WIDTH = 10;
    private static final int MAX_GLASS_WIDTH = 30;
    private static final int SPINNER_STEP_SIZE = 1;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 50;
    private static final int DEFAULT_GLASS_WIDTH = 10;
    private static final int DEFAULT_GLASS_HEIGHT = 20;
    private static final TFloorCleaner DEFAULT_FLOOR_CLEANER = new StandartFloorCleaner();
    private static final Map<String, TFloorCleaner> FLOOR_CLEANERS;
    static {
        Map<String, TFloorCleaner> aMap = new HashMap<String, TFloorCleaner>();
        aMap.put(StandartFloorCleaner.NAME, new StandartFloorCleaner());
        aMap.put(WholeFigureX10FloorCleaner.NAME, new WholeFigureX10FloorCleaner());
        FLOOR_CLEANERS = Collections.unmodifiableMap(aMap);
    }
    private int glassWidth;
    private int glassHeight;
    private TFloorCleaner floorCleaner;
    private Menu menu;
    private Game game;
    private JButton btnCancel;
    private JButton btnOK;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JSpinner spinnerHeight;
    private JSpinner spinnerWidth;
    private DefaultComboBoxModel fcCbModel;
    private JComboBox cbFloorCleaner;

    public Edit(Menu menu) {
        super("Тетрис 2.0 - Настройки");
        this.glassWidth = DEFAULT_GLASS_WIDTH;
        this.glassHeight = DEFAULT_GLASS_HEIGHT;
        this.floorCleaner = DEFAULT_FLOOR_CLEANER;
        this.menu = menu;
        this.initComponents();
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.getContentPane().setBackground(new Color(43, 43, 43));
        this.setResizable(false);
    }

    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.spinnerHeight = new JSpinner();
        this.jLabel2 = new JLabel();
        this.spinnerWidth = new JSpinner();
        this.btnOK = new JButton();
        this.btnCancel = new JButton();
        this.setDefaultCloseOperation(3);
        this.jLabel1.setFont(new Font("Tahoma", 0, 14));
        this.jLabel1.setForeground(Color.WHITE);
        this.jLabel1.setText("Введите желаемую высоту стакана:");
        this.spinnerHeight.setFont(new Font("Tahoma", 0, 18));
        this.spinnerHeight.setBackground(new Color(185, 213, 253));
        this.spinnerHeight.setModel(new SpinnerNumberModel(this.glassHeight,
                MIN_GLASS_HEIGHT, MAX_GLASS_HEIGHT, SPINNER_STEP_SIZE));
        this.spinnerHeight.setEditor(new JSpinner.NumberEditor(this.spinnerHeight));
        this.jLabel2.setFont(new Font("Tahoma", 0, 14));
        this.jLabel2.setForeground(Color.WHITE);
        this.jLabel2.setText("Введите желаемую ширину стакана:  ");
        this.spinnerWidth.setFont(new Font("Tahoma", 0, 18));
        this.spinnerWidth.setBackground(new Color(185, 213, 253));
        this.spinnerWidth.setModel(new SpinnerNumberModel(this.glassWidth,
                MIN_GLASS_WIDTH, MAX_GLASS_WIDTH, SPINNER_STEP_SIZE));
        this.spinnerWidth.setEditor(new JSpinner.NumberEditor(this.spinnerWidth));
        this.jLabel3 = new JLabel();
        this.jLabel3.setFont(new Font("Tahoma", 0, 14));
        this.jLabel3.setForeground(Color.WHITE);
        this.jLabel3.setText("Выберите стратегию упаковки рядов:");
        this.fcCbModel = new DefaultComboBoxModel<String>();
        Iterator<Map.Entry<String, TFloorCleaner>> it = FLOOR_CLEANERS.entrySet().iterator();
        while(it.hasNext()){
            this.fcCbModel.addElement(it.next().getKey());
        }        
        this.cbFloorCleaner = new JComboBox<String>(this.fcCbModel);
        this.cbFloorCleaner.setSelectedItem(this.floorCleaner.NAME);
        this.cbFloorCleaner.setFont(new Font("Tahoma", 0, 18));
        this.cbFloorCleaner.setBackground(new Color(185, 213, 253));
        
        this.btnOK.setBackground(new Color(39, 70, 132));
        this.btnOK.setFont(new Font("Tahoma", 0, 18));
        this.btnOK.setText("OK");
        this.btnOK.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                Edit.this.btnOKActionPerformed(evt);
            }
        });
        this.btnCancel.setBackground(new Color(39, 70, 132));
        this.btnCancel.setFont(new Font("Tahoma", 0, 18));
        this.btnCancel.setText("\u041e\u0442\u043c\u0435\u043d\u0430");
        this.btnCancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    try {
                        Edit.this.btnCancelActionPerformed(evt);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (NoSuchFieldException ex) {
                    Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, 32767)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGap(GroupLayout.PREFERRED_SIZE, FRAME_WIDTH, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                .addComponent(this.jLabel1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(this.spinnerHeight, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                .addComponent(this.jLabel2, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(this.spinnerWidth, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addComponent(this.jLabel3, GroupLayout.PREFERRED_SIZE, FRAME_WIDTH - 30 ,GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.cbFloorCleaner, GroupLayout.PREFERRED_SIZE, FRAME_WIDTH - 30 ,GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.btnOK,
                                                GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                                        .addGap(GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(this.btnCancel,
                                                GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap(30, 30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(this.jLabel1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                        .addComponent(this.spinnerHeight, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(this.jLabel2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                        .addComponent(this.spinnerWidth, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                        .addGap(GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                        .addComponent(this.jLabel3)
                        .addGap(GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                        .addComponent(this.cbFloorCleaner, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addGap(GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.btnOK,
                                        GroupLayout.PREFERRED_SIZE, BUTTON_HEIGHT, GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.btnCancel,
                                        GroupLayout.PREFERRED_SIZE, BUTTON_HEIGHT, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, 32767)));
        this.pack();
    }

    private void btnOKActionPerformed(ActionEvent evt) {
        this.glassWidth = Integer.parseInt(this.spinnerWidth.getValue().toString());
        this.glassHeight = Integer.parseInt(this.spinnerHeight.getValue().toString());
        this.floorCleaner = FLOOR_CLEANERS.get(this.cbFloorCleaner.getSelectedItem());
        this.menu.setVisible(true);
        this.setVisible(false);
    }

    private void btnCancelActionPerformed(ActionEvent evt) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        this.spinnerHeight.setValue(this.glassHeight);
        this.spinnerWidth.setValue(this.glassWidth);
        this.cbFloorCleaner.setSelectedItem(this.floorCleaner.getClass().getField("NAME").get(String.class));
        this.menu.setVisible(true);
        this.setVisible(false);
    }
    
    public int getGlassWidth(){
        return this.glassWidth;
    }

    public int getGlassHeight(){
        return this.glassHeight;
    }
    
    public TFloorCleaner getGlassFloorCleaner(){
        return this.floorCleaner;
    }

}

