package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI {
    //TODO:
    // THIS CLASS WILL BE USED TO CREATE USER INTERFACE FOR BUS_STOP SELECTION
    // BUTTONS WILL BE GENERATED FROM AN ARRAY OF STATIONS, YET TO BE IMPLEMENTEDgit
    // THIS IS JUST AN EXAMPLE OF HOW IT SHOULD LOOK LIKE


    public GUI(){
        JPanel panel = new JPanel();
        JButton buttonRetiro = new JButton("Retiro");
        JButton buttonWaterloo = new JButton("Waterloo");
        JLabel labelCity1 = new JLabel("Choose bus stop from city 1");
        JLabel labelCity2 = new JLabel("Choose bus stop from city 2");
        JFrame frame = new JFrame("My First GUI");

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.add(labelCity1);
        panel.add(labelCity2);
        panel.add(buttonRetiro);
        panel.add(buttonWaterloo);



        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bus stops");
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new GUI();
    }
}
