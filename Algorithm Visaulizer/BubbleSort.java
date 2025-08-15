public class BubbleSort implements Runnable {
    private Integer[] array;
    private SortingPanel panel;

    public BubbleSort(Integer[] array, SortingPanel panel) {
        this.array = array;
        this.panel = panel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    panel.highlight(j, j + 1);
                    panel.repaint();
                    Thread.sleep(200);

                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;

                        panel.repaint();
                        Thread.sleep(200);
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel.highlight(-1, -1);
        panel.repaint();
    }
}
