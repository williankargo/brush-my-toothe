package leetcode.TwoPointer.NSum;

import java.util.HashMap;
import java.util.Map;

public class TwoSum_HashMap {
    
    // 空O(N)
    // key放數字，value放該數字出現的次數
    private Map<Integer, Integer> numToCntMap;

    public TwoSum_HashMap(){
        numToCntMap = new HashMap<Integer, Integer>();
    }

    
    // add: 時O(1)
    public void add(int number) {

        // 數字出現次數加1，如果這個數字第一次出現，則之前出現的次數為0
        numToCntMap.put(number, numToCntMap.getOrDefault(number, 0) + 1);
    }

    // find: 時O(N)
    public boolean find(int targetValue) {

        // 遍歷map中所有的key
        for (Integer num1 : numToCntMap.keySet()) {

            // 尋找和 num1 搭配的 num2
            int num2 = targetValue - num1;
            // 如果num1和num2一樣，則num2的值需出現兩次
            int num2Cnt = (num1 == num2) ? 2 : 1;
            // 找不到就返回0
            if (numToCntMap.getOrDefault(num2, 0) >= num2Cnt) {
                return true;
            }
        }
        return false;
    }

}