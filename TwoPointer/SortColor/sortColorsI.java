package leetcode.TwoPointer.SortColor;

// 時O(N) ， 空O(N)
public class sortColorsI {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // 一共三個顏色，存放每個顏色出現次數
        int[] colorCnts = new int[3];
        // 遍歷所有顏色並紀錄次數 [次數,次數,次數]
        for (int num : nums) {
            colorCnts[num]++;
        }

        int index = 0;
        for (int color = 0; color < colorCnts.length; color++) {
            int cnt = colorCnts[color];
            while (cnt > 0) {
                nums[index] = color;
                index++;
                cnt--;
            }
        }

    }
}
