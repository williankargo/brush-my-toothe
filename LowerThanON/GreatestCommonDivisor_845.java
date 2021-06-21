package leetcode.LowerThanON;

public class GreatestCommonDivisor_845 {

  // 輾轉相除法
  public int gcd(int big, int small) {
    if (small != 0) {
      return gcd(small, big % small);
    } else {
      return big;
    }
  }
}