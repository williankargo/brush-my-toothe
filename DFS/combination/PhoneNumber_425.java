package leetcode.DFS.combination;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumber_425 {

  public static void main(String[] args) {
    PhoneNumber_425 demo = new PhoneNumber_425();
    demo.letterCombinations("23");

  }

  public static final String[] KEYBOARD = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv",
      "wxyz"};
  // 0 1 2 3 ...9

  public List<String> letterCombinations(String digits) {

    List<String> combinations = new ArrayList<>();

    // 特殊情況處理
    if (digits.length() == 0) {
      return combinations;
    }

    dfs(digits, 0, "", combinations);
    return combinations;
  }


  private void dfs(String digits, int index, String combination, List<String> combinations) {
    if (index == digits.length()) {
      combinations.add(combination);
      return; // 不要忘記結束遞歸！
    }
    int digit = digits.charAt(index) - '0';

    for (int i = 0; i < KEYBOARD[digit].length(); i++) { // 第一層
      dfs(digits, index + 1, combination + KEYBOARD[digit].charAt(i), combinations); // 第二層
      // 這裡為什麼不用回朔把加入combination的字母移除？因為串入下層的是combination + KEYBOARD[digit].charAt(i)，combination沒有變
    }
  }
}
// 同一層for loop下：
// dfs(digits, 1, combination + KEYBOARD[digit].charAt(0), combinations ) -> "a"
//    dfs(digits, 2, combination + KEYBOARD[digit].charAt(0), combinations ) -> "ad"
//    dfs(digits, 2, combination + KEYBOARD[digit].charAt(1), combinations ) -> "ae"
//    dfs(digits, 2, combination + KEYBOARD[digit].charAt(2), combinations ) -> "af"

// dfs(digits, 1, combination + KEYBOARD[digit].charAt(1), combinations ) -> "b"
//    dfs(digits, 2, combination + KEYBOARD[digit].charAt(0), combinations ) -> "bd"
//    dfs(digits, 2, combination + KEYBOARD[digit].charAt(1), combinations ) -> "be"
//    dfs(digits, 2, combination + KEYBOARD[digit].charAt(2), combinations ) -> "bf"

// dfs(digits, 1, combination + KEYBOARD[digit].charAt(2), combinations ) -> "c"
//    dfs(digits, 2, combination + KEYBOARD[digit].charAt(0), combinations ) -> "cd"
//    dfs(digits, 2, combination + KEYBOARD[digit].charAt(1), combinations ) -> "ce"
//    dfs(digits, 2, combination + KEYBOARD[digit].charAt(2), combinations ) -> "cf"

