package leetcode.TwoPointer.SortColor;

public class MoveZeros {
    public void moveZeros(int[] nums) {
        int fillPointer = 0; // 指向準備被非0數填充的位置
        int movePointer = 0; // 向前找到 非0 後停下來拼命往後丟

        while (movePointer < nums.length) {
            if (nums[movePointer] != 0) {
                // 如果兩指針不等，交換
                // 如果兩指針相等，同時往前移
                if (fillPointer != movePointer){
                    // 多此一舉可以省略
                    //int fillPointerValue = nums[fillPointer];
                    nums[fillPointer] = nums[movePointer];
                    //nums[movePointer] = fillPointerValue;
                }
                fillPointer++;
            }
            movePointer++;
        }

        // 把後續所有數字全部變0
        // mp經過過的都會寸少不生
        while (fillPointer < nums.length){
            if (nums[fillPointer] != 0){
                nums[fillPointer] = 0;
            }
            fillPointer++;
        }
    }
}
