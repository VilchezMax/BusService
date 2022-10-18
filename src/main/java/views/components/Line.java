package views.components;

import busservice.models.BusStop;
import views.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Line extends JComponent implements ActionListener {
    private Integer initialX;
    private Integer initialY;
    private Integer finalX;
    private Integer finalY;

    private Integer currentX = initialX; //CURRENT FOR ANIMATION
    private Integer currentY = initialY; //CURRENT FOR ANIMATION

    public Line() {
        super();
    }

    public Line(Integer initialX, Integer initialY, Integer finalX, Integer finalY) {
        super();
        this.initialX = initialX;
        this.initialY = initialY;
        this.finalX = finalX;
        this.finalY = finalY;
    }

    public Integer getInitialX() {
        return initialX;
    }

    public void setInitialX(Integer initialX) {
        this.initialX = initialX;
    }

    public Integer getInitialY() {
        return initialY;
    }

    public void setInitialY(Integer initialY) {
        this.initialY = initialY;
    }

    public Integer getFinalX() {
        return finalX;
    }

    public void setFinalX(Integer finalX) {
        this.finalX = finalX;
    }

    public Integer getFinalY() {
        return finalY;
    }

    public void setFinalY(Integer finalY) {
        this.finalY = finalY;
    }

    public Integer getCurrentX() {
        return currentX;
    }

    public void setCurrentX(Integer x) {
        this.currentX = x;
    }

    public Integer getCurrentY() {
        return currentY;
    }

    public void setCurrentY(Integer y) {
        this.currentY = y;
    }

    /* Config for Line: color, vector, width */
    public void paintComponent(Graphics g, Color color1, Color color2) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color1);
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(initialX, initialY, finalX, finalY);
    }

    /* Validations before painting Line */
    public void drawLine(BusStop busStop1, BusStop busStop2) {
        Color color1;
        Color color2;
        if (!busStop1.getCity().getId().equals(busStop2.getCity().getId())) {
            color1 = Color.BLACK;
            color2 = Color.BLACK;
        } else {
            color1 = GUI.chooseColor(busStop1.getCity().getId());
            color2 = GUI.chooseColor(busStop2.getCity().getId());
        }
        paintComponent(this.getGraphics(), color1, color2);
    }

    /* Is executed until currentX&Y goes from initialX&Y to finalX&Y, generating an animation */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentX < finalX) {
            currentX++;
        }
        if (currentY < finalY) {
            currentY++;
        }
        repaint();
    }

}
