package leetcode.TwoPointer.SameDirection;

public class NumberOfSubstringsWithAllZeros_1870 { // 計算每段連續0的個數

  public static void main(String[] args) {
    NumberOfSubstringsWithAllZeros_1870 demo = new NumberOfSubstringsWithAllZeros_1870();
    System.out
        .println(demo.stringCount("001000")); // 2 (0) + 1 (00) + 3 (0) + 2 (00) + 1 (000) 自己推出來

  }

  public int stringCount(String str) {

    if (str == null) {
      return 0;
    }

    int j = 1, answer = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) != '0') {
        continue;
      }

      j = Math.max(j, i + 1);
      while (j < str.length() && str.charAt(j) == '0') {
        j++;
      }
      // 算有多少個0
      // 001000
      // ^ ^
      // i j
      answer += j - i;
    }
    return answer;
  }
}
