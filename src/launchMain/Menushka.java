package launchMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static javax.swing.SwingConstants.CENTER;
public class Menushka extends JPanel implements ActionListener {
    ActionEvent f;
    ActionEvent e;

    Menushka() {
        setLayout(new GridLayout(3,1));

        Color color = new Color(0,255,0);

        JLabel label = new JLabel("Select your tank", CENTER);
        label.setForeground(Color.blue);
                        Font f = new Font("Arial", Font.PLAIN, 64);
        add(label).setFont(f);

        JRadioButton BT7 = new JRadioButton("BT7");
        BT7.setLayout(new GridLayout(2,1));
        BT7.setMnemonic(KeyEvent.VK_B);
        BT7.setActionCommand("BT7");
        BT7.setForeground(Color.BLACK);
        BT7.setBackground(new Color(255,148,73));
        JLabel BT7_1 = new JLabel("Speed = 10", CENTER);
        JLabel BT7_2 = new JLabel("Armor = 2", CENTER);
        BT7.add(BT7_1);
        BT7.add(BT7_2);
        //BT7.setSelected(true);

        JRadioButton T34 = new JRadioButton("T34");
        T34.setLayout(new GridLayout(2,1));
        T34.setMnemonic(KeyEvent.VK_C);
        T34.setActionCommand("T34");
        T34.setForeground(Color.BLACK);
        T34.setBackground(Color.GREEN);
        JLabel labelT34_1 = new JLabel("Speed = 20", CENTER);
        JLabel labelT34_2 = new JLabel("Armor = 1", CENTER);
        T34.add(labelT34_1);
        T34.add(labelT34_2);

        JRadioButton Tiger = new JRadioButton("Tiger");
        Tiger.setLayout(new GridLayout(2,1));
        Tiger.setMnemonic(KeyEvent.VK_D);
        Tiger.setActionCommand("Tiger");
        Tiger.setForeground(Color.BLACK);
        Tiger.setBackground(Color.RED);
        JLabel Tiger_1 = new JLabel("Speed = 5", CENTER);
        JLabel Tiger_2 = new JLabel("Armor = 3", CENTER);
        Tiger.add(Tiger_1);
        Tiger.add(Tiger_2);

        ButtonGroup group = new ButtonGroup();
        group.add(BT7);
        group.add(T34);
        group.add(Tiger);



        BT7.addActionListener(this);
        T34.addActionListener(this);
        Tiger.addActionListener(this);

        JPanel radioPanel = new JPanel(new GridLayout(1, 0));
        radioPanel.add(BT7);
        radioPanel.add(T34);
        radioPanel.add(Tiger);

        radioPanel.setForeground(Color.ORANGE);

        add(radioPanel);

        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        JButton start = new JButton("START");

        panel.add(start);
        start.addActionListener(this);
       // start.setBackground();
        add(panel);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

}

    @Override
    public void actionPerformed(ActionEvent i) {

if(i.getActionCommand().equals("T34") || i.getActionCommand().equals("BT7") || i.getActionCommand().equals("Tiger")){
    f = i;
}
        if(f!=null){
            if(i.getActionCommand().equals("START")){
                e=f;
            }
        }
    }
    public ActionEvent getE() {
        return e;
    }
}
