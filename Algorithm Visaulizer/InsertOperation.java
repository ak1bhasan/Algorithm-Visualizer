import javax.swing.*;

public class InsertOperation implements Runnable {
    private Integer[] array;
    private SortingPanel panel;
    private int value;

    public InsertOperation(Integer[] array, SortingPanel panel, int value) {
        this.array = array;
        this.panel = panel;
        this.value = value;
    }

    @Override
    public void run() {
        try {
            Integer[] newArray = new Integer[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = value;

            panel.setArray(newArray);
            panel.highlight(array.length, -1);
            panel.repaint();
            Thread.sleep(500);

            JOptionPane.showMessageDialog(null,
                    "Inserted value: " + value + "\nAt index: " + array.length);

            panel.highlight(-1, -1);
            panel.repaint();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
