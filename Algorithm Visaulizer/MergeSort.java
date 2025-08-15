public class MergeSort implements Runnable {
    private Integer[] array;
    private VisualizerFrame frame;

    public MergeSort(Integer[] array, VisualizerFrame frame) {
        this.array = array;
        this.frame = frame;
    }

    @Override
    public void run() {
        mergeSort(array, 0, array.length - 1);
        frame.getSortingPanel().highlight(-1, -1);
        frame.getSortingPanel().repaint();
    }

    private void mergeSort(Integer[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(Integer[] arr, int left, int mid, int right) {
        Integer[] temp = new Integer[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        SortingPanel panel = frame.getSortingPanel();

        while (i <= mid && j <= right) {
            panel.highlight(i, j);
            panel.repaint();
            try { Thread.sleep(200); } catch (InterruptedException e) {}

            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (k = 0; k < temp.length; k++) {
            arr[left + k] = temp[k];
            panel.repaint();
            try { Thread.sleep(200); } catch (InterruptedException e) {}
        }
        panel.highlight(-1, -1);
        panel.repaint();
    }
}
