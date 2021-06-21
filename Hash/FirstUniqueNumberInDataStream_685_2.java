package leetcode.Hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 雙指針做法
public class FirstUniqueNumberInDataStream_685_2 {}

class DataStream2{

  private DoubleLinkedListNode head; // dummy node
  private DoubleLinkedListNode tail;
  private Map<Integer, DoubleLinkedListNode> numToNode; // 數字 : 數字自己的node
  private Set<Integer> duplicates;

  DataStream2(){
    head = new DoubleLinkedListNode(0);
    tail = head;
    numToNode = new HashMap<>();
    duplicates = new HashSet<>();
  }

  void add(int number){
    if (duplicates.contains(number)){
      return;
    }
    if (!numToNode.containsKey(number)){
      DoubleLinkedListNode node = new DoubleLinkedListNode(number);
      numToNode.put(number, node);
      tail.next = node;
      node.prev = tail;
      tail = node;
    }else{
      remove(number);
      duplicates.add(number);
    }
  }

  private void remove(int number){
    DoubleLinkedListNode node = numToNode.get(number);
    DoubleLinkedListNode prev = node.prev;

    prev.next = node.next;
    if(node.next != null){
      node.next.prev = prev;
    }
    numToNode.remove(number);

    // 如果刪掉的是最後一個點，更改tail
    if (prev.next == null){
      tail = prev;
    }
  }

  int firstUnique(){
    if (head.next != null){
      return head.next.val;
    }
    return -1;
  }
}

class DoubleLinkedListNode{
  int val;
  DoubleLinkedListNode prev, next;
  DoubleLinkedListNode(int val){
    this.val = val;
    this.prev = null;
    this.next = null;
  }
}