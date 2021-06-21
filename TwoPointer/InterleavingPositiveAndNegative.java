package leetcode.TwoPointer;

// 時O(N) ， 空O(1) 因為 in-place
public class InterleavingPositiveAndNegative {

    public void rerange(int[] A) {
        int negCnt = partition(A); // 負數得個數
        int posCnt = A.length - negCnt;
        
        // 負數 = 正數 + 1 , left = 1 right = length - 1 間隔交換
        // 正數 = 負數 + 1 , left = 0 right = length - 2 間隔交換
        // 負數 = 正數 , left = 0 right = length - 1 間隔交換

        int left = negCnt > posCnt ? 1 : 0;
        int right = A.length - (posCnt > negCnt ? 2 : 1);
        interleave(A, left, right);
    }

    // 左分區為負，右分區為正
    private int partition(int[] A) {
        int left = 0, right = A.length - 1;
        while (left <= right) {

            // 從左邊抓正數
            while (left <= right && A[left] < 0) {
                left++;
            }

            // 從右邊抓負數
            while (left <= right && A[right] > 0) {
                right--;
            }

            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;

                left++;
                right--;
            }
        }

        return left; // 會返回負數得個數 R L
    }
    
    private void interleave(int[] A, int left, int right) {
        while (left < right) {
            int temp = A[left];
            A[left] = A[right];
            A[right] = temp;
            // +2 是間隔交換
            left += 2;
            right -= 2;
        }
    }
}
