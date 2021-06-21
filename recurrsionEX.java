package leetcode;

public class recurrsionEX {

    // --0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...
    // n=1, n=2

    // 從後往前推導
    // 1. 遞歸的定義： 函數接受什麼樣的參數，返回什麼樣的值，代表什麼樣的意思
    public int fibonacci(int n) {

        // 3. 遞歸的出口
        if (n <= 2) {
            return n - 1;
        }

        // 2. 遞歸的拆解
        return fibonacci(n - 1) + fibonacci(n - 2);
        // 保證這一層做對，最後一層做對，整個算法就能對，但遞歸會造成stack overflow
    }

}
