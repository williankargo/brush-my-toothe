package leetcode.LowerThanON;

import java.util.ArrayList;
import java.util.List;

// 時 O(n * Range^1/2) -> n : 元素個數  Range^1/2 : 當前這個block要遍歷的個數
public class CountOfSmallerNumberBeforeItself_249 {

  public List<Integer> countOfSmallerNumberII(int[] A) {

    List<Integer> results = new ArrayList<>();
    if (A == null || A.length == 0) {
      return results;
    }

    BlockArray blockArray = new BlockArray(10000); // 10000個，每100個為一個block(0 - 99) 10000^1/2
    for (int i = 0; i < A.length; i++) {
      results.add(blockArray.countSmaller(A[i]));
      blockArray.insert(A[i]);
    }

    return results;
  }
}

class BlockArray {

  public Block[] blocks;
  public int blockSize;

  public BlockArray(int size) {
    blockSize = (int) Math.sqrt(size);
    int blockCount = size / blockSize + 1; // 共101個block (最後一個10000-10000)
    blocks = new Block[blockCount];
    for (int i = 0; i < blockCount; i++) {
      blocks[i] = new Block(blockSize);
    }
  }

  public int countSmaller(int value) {
    int index = value / blockSize;
    int totalCount = 0;
    for (int i = 0; i < index; i++) { // 統計前面blocks有的數字
      totalCount += blocks[i].total;
    }
    for (int i = 0; index * blockSize + i < value; i++) { // 統計當前block比我小的數量
      totalCount += blocks[index].count[i];
    }
    return totalCount;
  }

  // 插入 O(1)
  public void insert(int value) {
    int index = value / blockSize;
    blocks[index].total++; // 加total
    blocks[index].count[value - index * blockSize]++; // 也要加count
  }
}

class Block {

  int total; // 紀錄本block共有多少個數
  int[] count; // 紀錄本block的某個數出現了幾次

  Block(int blockSize) {
    this.total = 0;
    this.count = new int[blockSize];
  }
}

