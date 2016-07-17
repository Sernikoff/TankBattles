package launchMain;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





public class GameOver extends JPanel implements ActionListener {
    ActionEvent ee;
    GameOver(){


        setLayout(new GridLayout(3,1));
        JLabel label = new JLabel("Game Over", SwingConstants.CENTER);
        label.setForeground(Color.blue);
        Font f = new Font("Arial", Font.PLAIN, 64);
        add(label).setFont(f);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        panel.setBackground(Color.CYAN);
        JButton restart = new JButton("RESTART");
        restart.addActionListener(this);
        panel.add(restart);

        JButton  replay = new JButton("REPLAY");
        replay.addActionListener(this);
        panel.add(replay);

        JButton exit = new JButton("EXIT");
        exit.addActionListener(this);
        panel.add(exit);
        add(panel);


        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("RESTART")){
           ee = e;
            System.out.println("e = " + e.getActionCommand());
        }
        if(e.getActionCommand().equals("REPLAY")){
            ee = e;
            System.out.println("e = " + e.getActionCommand());
        }
        if(e.getActionCommand().equals("EXIT")){
            ee = e;
            System.out.println("e = " + e.getActionCommand());
        }
    }

    public ActionEvent getE() {
        return ee;
    }
}
