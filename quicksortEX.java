package leetcode;

public class quicksortEX {
    public void sortIntegers(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        quickSort(A, 0, A.length - 1);
    }

    private void quickSort(int[] A, int start, int end) {
        if (start >= end) {
            return;
        }

        int left = start;
        int right = end;
        // 1. pivot , pivot != A[start] or A[end] 容易造成極端值情況無法均分
        // get value not index
        int pivot = A[(start + end) / 2];

        // 2. left <= right not left < right 用以保證left和right不會重疊
        while (left <= right) {
            while (left <= right && A[left] < pivot) {
                left++;
            }
            while (left <= right && A[right] > pivot) {
                right--;
            }

            // swap
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                left++;
                right--;
            }
        }

        // right和left已經交錯過，此時right在左邊 left在右邊
        quickSort(A, start, right);
        quickSort(A, left, end);
    }
}
