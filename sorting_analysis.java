import java.util.*;

public class SortingAnalysis {

    // Generate Best Case
    static int[] generateBestCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i + 1;
        return arr;
    }

    // Generate Worst Case
    static int[] generateWorstCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = n - i;
        return arr;
    }

    // Generate Average Case
    static int[] generateAverageCase(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            list.add(i);

        Collections.shuffle(list);

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = list.get(i);

        return arr;
    }

    // Bubble Sort
    static int bubbleSort(int[] arr, boolean ascending) {
        int steps = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                steps++;

                if ((ascending && arr[j] > arr[j + 1]) ||
                    (!ascending && arr[j] < arr[j + 1])) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    steps += 3;
                    swapped = true;
                }
            }

            if (!swapped)
                break;
        }
        return steps;
    }

    // Selection Sort
    static int selectionSort(int[] arr, boolean ascending) {
        int steps = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int idx = i;

            for (int j = i + 1; j < n; j++) {
                steps++;

                if ((ascending && arr[j] < arr[idx]) ||
                    (!ascending && arr[j] > arr[idx])) {
                    idx = j;
                }
            }

            if (idx != i) {
                int temp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = temp;
                steps += 3;
            }
        }
        return steps;
    }

    // Insertion Sort
    static int insertionSort(int[] arr, boolean ascending) {
        int steps = 0;
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            steps++;
            int j = i - 1;

            while (j >= 0) {
                steps++;

                if ((ascending && arr[j] > key) ||
                    (!ascending && arr[j] < key)) {

                    arr[j + 1] = arr[j];
                    steps++;
                    j--;
                } else {
                    break;
                }
            }

            arr[j + 1] = key;
            steps++;
        }
        return steps;
    }

    // Print Array
    static void printArray(int[] arr) {
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] sizes = {10, 20, 30, 40};

        for (String order : new String[]{"Ascending", "Descending"}) {

            boolean ascending = order.equals("Ascending");

            for (String caseType : new String[]{"Best", "Average", "Worst"}) {

                for (int n : sizes) {

                    int[] original;

                    if (caseType.equals("Best"))
                        original = generateBestCase(n);
                    else if (caseType.equals("Average"))
                        original = generateAverageCase(n);
                    else
                        original = generateWorstCase(n);

                    System.out.println("\nInput Size: " + n +
                            " | " + caseType + " Case | " + order);

                    // Bubble Sort
                    int[] arr1 = original.clone();
                    int steps1 = bubbleSort(arr1, ascending);
                    System.out.print("Bubble Sorted: ");
                    printArray(arr1);
                    System.out.println("Steps: " + steps1);

                    // Selection Sort
                    int[] arr2 = original.clone();
                    int steps2 = selectionSort(arr2, ascending);
                    System.out.print("Selection Sorted: ");
                    printArray(arr2);
                    System.out.println("Steps: " + steps2);

                    // Insertion Sort
                    int[] arr3 = original.clone();
                    int steps3 = insertionSort(arr3, ascending);
                    System.out.print("Insertion Sorted: ");
                    printArray(arr3);
                    System.out.println("Steps: " + steps3);
                }
            }
        }
    }
}
