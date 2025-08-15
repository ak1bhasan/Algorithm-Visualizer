import javax.swing.*;

public class DeleteOperation implements Runnable {
    private Integer[] array;
    private SortingPanel panel;
    private int target;

    public DeleteOperation(Integer[] array, SortingPanel panel, int target) {
        this.array = array;
        this.panel = panel;
        this.target = target;
    }

    @Override
    public void run() {
        try {
            int index = -1;

            // Visual search loop
            for (int i = 0; i < array.length; i++) {
                panel.highlight(i, -1);
                panel.repaint();
                Thread.sleep(300);

                if (array[i].equals(target)) {
                    index = i;
                    break;
                }
            }

            // Value not found
            if (index == -1) {
                panel.highlight(-1, -1);
                panel.repaint();
                JOptionPane.showMessageDialog(null,
                        "Value " + target + " is not in the array.");
                return;
            }

            // Create a new array without the target element
            Integer[] newArray = new Integer[array.length - 1];
            for (int i = 0, j = 0; i < array.length; i++) {
                if (i != index) {
                    newArray[j++] = array[i];
                }
            }

            // Update panel
            panel.setArray(newArray);
            panel.highlight(-1, -1);
            panel.repaint();

            // Show deletion info
            JOptionPane.showMessageDialog(null,
                    "Deleted value: " + target + "\nAt index: " + index);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
