import javax.swing.*;

public class LinearSearch implements Runnable {
    private Integer[] array;
    private SortingPanel panel;
    private int target;

    public LinearSearch(Integer[] array, SortingPanel panel, int target) {
        this.array = array;
        this.panel = panel;
        this.target = target;
    }

    @Override
    public void run() {
        try {
            boolean found = false;
            int index = -1;

            for (int i = 0; i < array.length; i++) {
                panel.highlight(i, -1);
                panel.repaint();
                Thread.sleep(300);

                if (array[i] == target) {
                    found = true;
                    index = i;
                    break;
                }
            }

            if (found) {
                JOptionPane.showMessageDialog(null,
                    "Value " + target + " found at index: " + index);
            } else {
                JOptionPane.showMessageDialog(null,
                    "Value " + target + " is not in the array.");
            }

            panel.highlight(-1, -1);
            panel.repaint();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
