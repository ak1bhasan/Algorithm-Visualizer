// SelectionSort.java
public class SelectionSort implements Runnable {
    private Integer[] array;
    private VisualizerFrame frame;

    public SelectionSort(Integer[] array, VisualizerFrame frame) {
        this.array = array;
        this.frame = frame;
    }

    public void run() {
        SortingPanel panel = frame.getSortingPanel();
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                panel.highlight(minIndex, j);
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
                panel.repaint();
                try { Thread.sleep(200); } catch (InterruptedException e) {}
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        panel.highlight(-1, -1);
        panel.repaint();
    }
}
