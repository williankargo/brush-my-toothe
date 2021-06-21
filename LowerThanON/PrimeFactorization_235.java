package leetcode.LowerThanON;

import java.util.ArrayList;
import java.util.List;

// 最坏时间复杂度 O(sqrt (n) )。当n为质数时，取到其最坏时间复杂度。
// 最多空间复杂度 O(log(n)), 当n质因数很多时，需要空间大，但总不会多于O(log(n))个 math problem ex. 16=2*2*2*2
public class PrimeFactorization_235 {

  public static void main(String[] args) {

    PrimeFactorization_235 demo = new PrimeFactorization_235();
    System.out.println(demo.primeFactorization(10000000));
  }

  // 分解成若干質因數的乘積
  public List<Integer> primeFactorization(int n) {
    List<Integer> result = new ArrayList<>();
    int up = (int) Math.sqrt(n);

    for (int k = 2; k <= up; ++k) {
      while (n % k == 0) {
        n /= k;
        result.add(k);
      }
    }

    // 最後殘餘的 num 如果不是 1，一定也是一個質因數，不要漏掉
    if (n != 1) {
      result.add(n);
    }

    return result;
  }
}
