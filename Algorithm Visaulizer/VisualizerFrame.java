import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VisualizerFrame extends JFrame {
    private SortingPanel sortingPanel;
    private JTextField arrayInputField;
    private JButton setArrayButton;

    private JButton bubbleSortBtn;
    private JButton mergeSortBtn;
    private JButton selectionSortBtn;

    private JButton insertBtn;
    private JButton deleteBtn;
    private JButton linearSearchBtn;
    private JButton binarySearchBtn;

    public VisualizerFrame() {
        setTitle("Algorithm Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for showing bars
        sortingPanel = new SortingPanel();
        add(sortingPanel, BorderLayout.CENTER);

        // Panel for input
        JPanel inputPanel = new JPanel();
        arrayInputField = new JTextField(40);
        setArrayButton = new JButton("Set Array");
        inputPanel.add(new JLabel("Enter array:"));
        inputPanel.add(arrayInputField);
        inputPanel.add(setArrayButton);
        add(inputPanel, BorderLayout.NORTH);

        // Panel for operation buttons
        JPanel buttonPanel = new JPanel();
        bubbleSortBtn = new JButton("Bubble Sort");
        mergeSortBtn = new JButton("Merge Sort");
        selectionSortBtn = new JButton("Selection Sort");

        insertBtn = new JButton("Insertion");
        deleteBtn = new JButton("Deletion");
        linearSearchBtn = new JButton("Linear Search");
        binarySearchBtn = new JButton("Binary Search");

        buttonPanel.add(bubbleSortBtn);
        buttonPanel.add(mergeSortBtn);
        buttonPanel.add(selectionSortBtn);
        buttonPanel.add(insertBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(linearSearchBtn);
        buttonPanel.add(binarySearchBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Set Array Button Action
        setArrayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = arrayInputField.getText();
                try {
                    String[] parts = input.split(",");
                    Integer[] arr = new Integer[parts.length];
                    for (int i = 0; i < parts.length; i++) {
                        arr[i] = Integer.parseInt(parts[i].trim());
                    }
                    sortingPanel.setArray(arr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Use comma-separated numbers.");
                }
            }
        });

        // Sorting Button Actions
        bubbleSortBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer[] arr = sortingPanel.getArray();
                new Thread(new BubbleSort(arr, sortingPanel)).start();
            }
        });

        mergeSortBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer[] arr = sortingPanel.getArray();
                new Thread(new MergeSort(arr, VisualizerFrame.this)).start();
            }
        });

        selectionSortBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer[] arr = sortingPanel.getArray();
                new Thread(new SelectionSort(arr, VisualizerFrame.this)).start();
            }
        });

        // Insertion Button
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter a value to insert:");
                try {
                    int value = Integer.parseInt(input);
                    Integer[] arr = sortingPanel.getArray();
                    new Thread(new InsertOperation(arr, sortingPanel, value)).start();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number!");
                }
            }
        });

        // Deletion Button
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter a value to delete:");
                try {
                    int value = Integer.parseInt(input);
                    Integer[] arr = sortingPanel.getArray();
                    new Thread(new DeleteOperation(arr, sortingPanel, value)).start();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number!");
                }
            }
        });

        // Linear Search Button
        linearSearchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter a value to search:");
                try {
                    int value = Integer.parseInt(input);
                    Integer[] arr = sortingPanel.getArray();
                    new Thread(new LinearSearch(arr, sortingPanel, value)).start();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number!");
                }
            }
        });

        // Binary Search Button
        binarySearchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter a value to binary search:");
                try {
                    int value = Integer.parseInt(input);
                    Integer[] arr = sortingPanel.getArray();
                    new Thread(new BinarySearch(arr, sortingPanel, value)).start();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number!");
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public SortingPanel getSortingPanel() {
        return sortingPanel;
    }
}
