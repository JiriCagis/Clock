package clock;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Date;

import javax.swing.JPanel;

public class Clock extends JPanel implements Runnable {

    //constants
    private final Color BACKGROUND = Color.LIGHT_GRAY;
    private final Color CONTOUR = Color.BLACK;
    private final Color HOUR_HAND = Color.BLACK;
    private final Color MINUTES_HAND = Color.darkGray;
    private final Color SECOND_HAND = Color.red;
    
    //variables for drawing
    private Point clockCenter;
    private int halfAxisX;
    private int halfAxisY;

    public Clock() {
        setBackground(BACKGROUND);
        setSize(300, 300); //25 mean top panel with control button
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(BACKGROUND);
        g2.fillRect(0, 0, getWidth(), getHeight());
        clockCenter = new Point(getWidth() / 2, getHeight() / 2);
        halfAxisX = getWidth() / 2;
        halfAxisY = getHeight() / 2;

        Date date = new Date();
        drawClock(g2, CONTOUR);
        drawHand(g2, HOUR_HAND, 12, (date.getHours()*5)+calculatePartOfHour(date));
        drawHand(g2, MINUTES_HAND, 4, date.getMinutes());
        drawHand(g2, SECOND_HAND, 1, date.getSeconds());
    }
    
    /*
        5 ... 60
        x ... minute
        --------------------
        x:5=MINUTE:100
        x=(5*minute)/100
    */
    private int calculatePartOfHour(Date date){
       return (int) ((double)(5*date.getMinutes())/60);
    }

    private void drawClock(Graphics2D g2, Color color) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(0, 0, getWidth(), getHeight());
        for (int i = 6; i <= 360; i = i + 6) {
            g2.setStroke((i % 90 == 0) ? new BasicStroke(3) : new BasicStroke(1));
            int length = (i % 90 == 0) ? 20 : ((i % 30 == 0) ? 30 : 10);
            g2.drawLine(
                    (int) (clockCenter.x + ((halfAxisX - length) * Math.cos(i / 57.2))),
                    (int) (clockCenter.y + ((halfAxisY - length) * Math.sin(i / 57.2))),
                    (int) (clockCenter.x + (halfAxisX * Math.cos(i / 57.2))),
                    (int) (clockCenter.y + (halfAxisY * Math.sin(i / 57.2))));
        }
    }

    private void drawHand(Graphics2D g2, Color color, int stroke, int value) {
        g2.setStroke(new BasicStroke(stroke));
        g2.setColor(color);
        g2.drawLine(
                clockCenter.x,
                clockCenter.y,
                (int) (clockCenter.x + ((halfAxisX - 35) * Math.cos(((value * 6) - 90) / 57.2))),
                (int) (clockCenter.y + ((halfAxisY - 35) * Math.sin(((value * 6) - 90) / 57.2))));
    }

}
