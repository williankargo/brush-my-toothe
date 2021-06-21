package leetcode.Hash;

public class Rehasing_129 {

  public ListNode[] rehashing(ListNode[] hashTable) {

    if (hashTable.length <= 0) {
      return hashTable;
    }

    int newCapacity = hashTable.length * 2;
    ListNode[] newHashTable = new ListNode[newCapacity];

    for (int i = 0; i < hashTable.length; i++) {
      while (hashTable[i] != null) {
        int newIndex = ((hashTable[i].val % newCapacity) + newCapacity) % newCapacity; // 防止負數
        // java : 如果你直接计算-4 % 3，你会得到-1，你可以应用函数：a % b = (a % b + b) % b得到一个非负整数。

        if (newHashTable[newIndex] == null) {
          newHashTable[newIndex] = new ListNode(hashTable[i].val); // 記得 deep copy !
        } else {
          ListNode pointer = newHashTable[newIndex];
          while (pointer.next != null) {
            pointer = pointer.next;
          }
          pointer.next = new ListNode(hashTable[i].val);
        }
        hashTable[i] = hashTable[i].next; // 要到同條的下一點
      }
    }

    return newHashTable;
  }
}

