package busservice.views.gui;

import busservice.models.BusStop;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GUI {
    private static GUI instance;

    private GUI() {
    }

    public static GUI getInstance() {
        if (instance == null) {
            instance = new GUI();
        }
        return instance;
    }

    public void map() {
        JFrame frame = new JFrame("Bus Service");
        JLabel map = new JLabel(new ImageIcon("src/main/resources/images/gridHD.png"));
        JButton button = new JButton("CHOOSE STOPS");
        button.addActionListener(e -> frame.dispose());
        frame.setLayout(new BorderLayout(5, 5));
        frame.add(map, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        while (frame.isVisible()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<BusStop> election(List<BusStop> data) {
        JFrame firstFrame = new JFrame("BUS SERVICE");

        int maxRows = data.stream()
                .collect(Collectors.groupingBy(BusStop::getCity))
                .values()
                .stream()
                .mapToInt(List::size)
                .max().getAsInt() + 1;

        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        leftPanel.setLayout(new GridLayout(maxRows, 1, 10, 10));

        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        rightPanel.setLayout(new GridLayout(maxRows, 1, 10, 10));


        JLabel leftLabel = new JLabel("Choose initial bus stop:");
        leftPanel.add(leftLabel);

        JLabel rightLabel = new JLabel("");
        rightPanel.add(rightLabel);

        List<BusStop> choices = new ArrayList<>();

        /* Builds the button with color/actionListener */
        for (BusStop busStop : data) {
            JButton button = new JButton(busStop.getName());
            button.addActionListener(e -> {
                if (choices.isEmpty()) {
                    choices.add(busStop);

                    if (busStop.getCity().getId().equals(1)) {
                        leftPanel.setVisible(false);
                        rightLabel.setText("DESTINATION: ");
                    } else {
                        rightPanel.setVisible(false);
                        leftLabel.setText("DESTINATION: ");
                    }
                } else if (choices.size() == 1) {
                    choices.add(busStop);
                    firstFrame.dispose();
                }
            });

            /* Set Color */
            button.setBackground(GUI.chooseColor(busStop.getCity().getId()));
            /* Set Border if terminal */
            if (busStop.isTerminal()) {
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
            }
            /* Adds to corresponding panel (left-right) depending on city */
            if (busStop.getCity().getId() == 1) {
                leftPanel.add(button);
            } else {
                rightPanel.add(button);
            }
        }

        firstFrame.setLayout(new GridLayout(1, 2, 10, 10));
        firstFrame.add(leftPanel, BorderLayout.WEST);
        firstFrame.add(rightPanel, BorderLayout.EAST);
        firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstFrame.setTitle("Bus stops");
        firstFrame.pack();
        firstFrame.setVisible(true);
        firstFrame.setLocationRelativeTo(null);

        while (choices.size() < 2) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return choices;
    }

    /* Displays an icon(.gif) for 7 seconds and close the frame */

    public void loading(BusStop initialStop, BusStop finalStop) throws FileNotFoundException {
        JFrame transitionFrame = new JFrame("Loading...");
        ImageIcon icon = new ImageIcon("src/main/resources/images/loading.gif");
        if (icon == null) {
            throw new FileNotFoundException();
        }

        JLabel textLabel = new JLabel("FROM: " + initialStop.getName() + ", " + initialStop.getCity().getName());
        JLabel iconLabel = new JLabel(icon);
        JLabel toLabel = new JLabel("TO: " + finalStop.getName() + ", " + finalStop.getCity().getName());

        transitionFrame.setLayout(new GridLayout(3, 1, 0, 0));
        transitionFrame.add(textLabel, BorderLayout.NORTH);
        transitionFrame.add(iconLabel, BorderLayout.CENTER);
        transitionFrame.add(toLabel, BorderLayout.SOUTH);

        transitionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        transitionFrame.setTitle("Loading...");
        transitionFrame.pack();
        transitionFrame.setVisible(true);
        transitionFrame.setLocationRelativeTo(null);

        Timer timer = new Timer(6500, e -> transitionFrame.dispose());
        timer.start();
    }

    public void displayResult(List<BusStop> data) {
        JFrame resultFrame = new JFrame("BUS SERVICE");
    }

    public static Color chooseColor(int n) {
        switch (n) {
            case 1:
                return Color.CYAN; // Argentina light blue
            case 2:
                return Color.getHSBColor(353, 96, 81); //England red
            default:
                return Color.BLACK;
        }
    }
}
