package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI {

    public GUI() {
        JFrame frame = new JFrame("My First GUI");
        JPanel panel = new JPanel();
        JButton buttonRetiro = new JButton("Retiro");
        JButton buttonCallao = new JButton("Callao");
        JButton buttonOxfordCircus = new JButton("Oxford Circus");
        JButton buttonWaterloo = new JButton("Waterloo");
        JLabel labelCity1 = new JLabel("Choose bus stop from city 1");
        JLabel labelCity2 = new JLabel("Choose bus stop from city 2");

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(4/*(stations.length/2)+1*/, 2, 10, 10));
        panel.add(labelCity1);
        panel.add(labelCity2);

        panel.add(buttonRetiro);
        panel.add(buttonWaterloo);

        panel.add(buttonCallao);
        panel.add(buttonOxfordCircus);

        /*for (int i = 0; i < stations.length; i++) {
            panel.add(new JButton(stations[i]));

        }*/

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BUS STOPS");
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new GUI();
    }
}
