import javax.swing.*;
import java.awt.*;

public class SortingPanel extends JPanel {
    private Integer[] array = new Integer[]{};
    private int activeIndex1 = -1;
    private int activeIndex2 = -1;

    public void setArray(Integer[] newArray) {
        this.array = newArray;
        repaint();
    }

    public Integer[] getArray() {
        return array;
    }

    public void highlight(int i, int j) {
        activeIndex1 = i;
        activeIndex2 = j;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(850, 450);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (array == null || array.length == 0) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font("Arial", Font.BOLD, 16 ));
        FontMetrics fm = g2d.getFontMetrics();

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / array.length;

        int max = Integer.MIN_VALUE;
        for (int val : array) max = Math.max(max, val);

        for (int i = 0; i < array.length; i++) {
            int barHeight = (int)(((double) array[i] / max) * (height - 30));
            int x = i * barWidth;
            int y = height - barHeight;

            // Bar color
            if (i == activeIndex1 || i == activeIndex2) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.BLUE);
            }
            g2d.fillRect(x + 2, y, barWidth - 4, barHeight);

            // Draw the number above or inside bar
            String valStr = array[i].toString();
            int strWidth = fm.stringWidth(valStr);
            int strHeight = fm.getHeight();

            int textX = x + (barWidth - strWidth) / 2;
            int textY = y - 5;
            // if (barHeight < 20) {
            //     textY = y + 15; // inside bar if too short
            // }

            // Background black box for number
            g2d.setColor(Color.BLACK);
            g2d.fillRect(textX - 2, textY - strHeight + 4, strWidth + 4, strHeight);

            // Number text in white
            g2d.setColor(Color.WHITE);
            g2d.drawString(valStr, textX, textY);
        }
    }
}

