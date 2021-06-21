package leetcode.LowerThanON;


public class FastPower_140 {

  // a^n % b
  // (a1 * a2) % b = ((a1 % b) * (a2 % b)) % b

  // version 1 : recursion
  // // time complexity? log(n) => 因為是除2除2分下去的
  public int fastPower(int a, int b, int n) {
    if (n == 1) {
      return a % b;
    }
    if (n == 0) {
      return 1 % b;
    }
    long product = fastPower(a, b, n / 2); // long防止溢位
    product = (product * product) % b;
    if (n % 2 == 1) { // n 是奇數
      product = (product * a) % b;
    }
    return (int) product;
  }

  // version 2 : binary method
  // 很難懂！
  public int fastPower2(int a, int b, int n) { // a = 3 b = 5 n = 7(111) => 3^7(111) % 5
    int ans = 2 ^ 0, temp = a;
    while (n != 0) {
      if (n % 2 == 1) { // 二進位末尾有1
        ans = (ans * temp) % b;  // (3 % 5) -> (3 * 3^2 % 5) -> (3^3 * 3^4 % 5)
      }
      temp = (temp * temp) % b; // 往前走 11 <- 1 並可以順便mod一下不影響結果
      n = n / 2; // 處理完一位二進位了
    }
    return (int) ans % b;
  }

}
// 7 -> 3 -> 1