package leetcode;

public class mergesortEX {

    public void sortIntegers(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        int[] temp = new int[A.length]; // 創造一個空間
        mergerSort(A, 0, A.length - 1, temp);
    }

    private void mergerSort(int[] A, int start, int end, int[] temp) {
        if (start >= end) {
            return;
        }

        mergerSort(A, start, (start + end) / 2, temp);
        mergerSort(A, (start + end) / 2 + 1, end, temp);
        merge(A, start, end, temp);
    }

    private void merge(int[] A, int start, int end, int[] temp) {
        int middle = (start + end) / 2;
        int leftIndex = start;
        int rightIndex = middle + 1;
        int index = leftIndex;

        while (leftIndex <= middle && rightIndex <= end) {
            if (A[leftIndex] < A[rightIndex]) {
                temp[index++] = A[leftIndex++];
            } else {
                temp[index++] = A[rightIndex++]; // 同時往右移
            }
        }

        while (leftIndex <= middle) { // R已經跑完
            temp[index++] = A[leftIndex++];
        }

        while (rightIndex <= end) { // L已經跑完
            temp[index++] = A[rightIndex++];
        }

        for (int i = start; i <= end; i++) { // 傳回給本來的array
            A[i] = temp[i];
        }

    }

}
