public class SortingAlgorithms {

    // Selection Sort - Iterative
    // Time Complexity: O(n^2) (best, average, and worst case)
    // Space Complexity: O(1)
    // Advantages: Simple implementation, performs well for small datasets
    // Disadvantages: Inefficient for large datasets
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        // Iterate through the array
        for (int i = 0; i < n - 1; i++) {
            // Assume the current index has the minimum value
            int minIndex = i;
            // Check for a smaller element in the rest of the array
            for (int j = i + 1; j < n; j++) {
                // If found, update the minimum index
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the minimum element with the current element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Bubble Sort - Iterative
    // Time Complexity: O(n^2) (best case: Omega(n), average and worst case)
    // Space Complexity: O(1)
    // Advantages: Simple implementation, can detect if the list is already sorted
    // Disadvantages: Inefficient for large datasets
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        // Traverse the array
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            // Swap adjacent elements if they are in the wrong order
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped, the array is already sorted
            if (!swapped) {
                break;
            }
        }
    }

    // Merge Sort - Recursive
    // Time Complexity: O(n log n) (best, average, and worst case)
    // Space Complexity: O(n)
    // Advantages: Stable sorting algorithm, efficient for large datasets
    // Disadvantages: Requires additional space for merging, not suitable for small datasets
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            // Recursively sort the two halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // Helper method for merge sort to merge two sorted arrays
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays L[] and R[]
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[mid + 1 + j];
        }

        // Merge the temp arrays back into arr[left..right]
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements of L[], if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy the remaining elements of R[], if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Quick Sort - Recursive
    // Time Complexity: O(n log n) average case, O(n^2) worst case (when the pivot is the smallest or largest element)
    // Space Complexity: O(log n) average case, O(n) worst case (when the pivot is the smallest or largest element)
    // Advantages: Efficient for large datasets, in-place sorting algorithm
    // Disadvantages: Not stable, worst-case performance can be poor
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(arr, low, high);
            // Recursively sort elements before partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Helper method for quick sort to partition the array and return the partitioning index
    private static int partition(int[] arr, int low, int high) {
        // pivot (Element to be placed at right position)
        int pivot = arr[high];
        int i = low - 1; // Index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Counting Sort - Iterative
    // Time Complexity: O(n + k) (best, average, and worst case) where k is the range of input
    // Space Complexity: O(n + k)
    // Advantages: Efficient for small range of input data, stable sorting algorithm
    // Disadvantages: Requires extra space for counting, not suitable for large range of input data
    public static void countingSort(int[] arr) {
        int n = arr.length;
        // Find the maximum value in the array
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // Create a count array to store count of individual elements
        int[] count = new int[max + 1];
        // Store count of each character
        for (int j : arr) {
            count[j]++;
        }
        // Change count[i] so that count[i] now contains actual position of this character in output array
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        // Build the output character array
        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        // Copy the output array to arr, so that arr now contains sorted characters
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // Example usage
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        selectionSort(arr);
        System.out.println("Selection Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] arr2 = {64, 25, 12, 22, 11};
        bubbleSort(arr2);
        System.out.println("Bubble Sorted array:");
        for (int num : arr2) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] arr3 = {64, 25, 12, 22, 11};
        mergeSort(arr3, 0, arr3.length - 1);
        System.out.println("Merge Sorted array:");
        for (int num : arr3) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println();
        int[] arr4 = {64, 25, 12, 22, 11};
        quickSort(arr4, 0, arr4.length - 1);
        System.out.println("Quick Sorted array:");
        for (int num : arr4) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] arr5 = {64, 25, 12, 22, 11};
        countingSort(arr5);
        System.out.println("Counting Sorted array:");
        for (int num : arr5) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
