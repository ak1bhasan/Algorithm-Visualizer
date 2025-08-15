import javax.swing.*;

public class BinarySearch implements Runnable {
    private Integer[] array;
    private SortingPanel panel;
    private int target;

    public BinarySearch(Integer[] array, SortingPanel panel, int target) {
        this.array = array;
        this.panel = panel;
        this.target = target;
    }

    // Utility method to check if array is sorted in ascending order
    private boolean isSorted(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        try {
            if (!isSorted(array)) {
                JOptionPane.showMessageDialog(null,
                    "Binary Search only works on a sorted array.\nPlease sort the array first.");
                return;
            }

            int left = 0, right = array.length - 1;
            boolean found = false;
            int index = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                panel.highlight(mid, -1);
                panel.repaint();
                Thread.sleep(500);

                if (array[mid] == target) {
                    found = true;
                    index = mid;
                    break;
                } else if (array[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
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
