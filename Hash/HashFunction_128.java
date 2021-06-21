package leetcode.Hash;

public class HashFunction_128 {

  // 返回固定且無規律（越亂越好）的一個整數
  public int hashCode(char[] key, int HASH_SIZE) {
    // 10 * 33^2 + 20 * 33^1 + 3 * 33^0 == (10 * 33 + 20) * 33 + 3
    long ans = 0;
    for (int i = 0; i < key.length; i++) {
      ans = (ans * 33 + (int) key[i]) % HASH_SIZE;
    }
    return (int) ans;
  }
}
