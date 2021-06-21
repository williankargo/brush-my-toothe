package leetcode.TwoPointer.SameDirection;

public class LongestSubstringWithAtMostKDistinctCharacters_386 {

  public int lengthOfLongestSubstringKDistinct(String s, int k) {

    int left = 0, right = 0, count = 0, MaxLen = 0;
    int[] charSet = new int[256]; //  total number of Character in ASCII table is 256 (0 to 255)
    while (right < s.length()) {

      if (charSet[s.charAt(right)] == 0) {
        count++; // 多增加一種種類
      }
      charSet[s.charAt(right)]++;
      right++;

      while (count > k) {
        charSet[s.charAt(left)]--;
        if (charSet[s.charAt(left)] == 0) {
          count--; // 種類少一種
        }
        left++;
      }

      MaxLen = Math.max(MaxLen, right - left);
    }
    return MaxLen;
  }
}
