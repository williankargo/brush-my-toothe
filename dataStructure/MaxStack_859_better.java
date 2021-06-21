package leetcode.dataStructure;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

// 結合 heap+stack+set
public class MaxStack_859_better {

  class Item implements Comparable<Item> {  // 記得是放Item!

    int value;
    int id;

    public Item(int value, int id) {
      this.value = value;
      this.id = id;
    }

    public int compareTo(Item another) { // 這裡放Item

      if (this.value != another.value) {
        return another.value - this.value; // this放後面：大到小
      }
      return another.id - this.id;
    }
  }

  private Queue<Item> heap;
  private Stack<Item> stack;
  private Set<Item> popedSet;
  private int globalNumber;

  public MaxStack_859_better() {
    this.globalNumber = 0;
    this.heap = new PriorityQueue<>(); // compareTo在這裡派上用場
    this.stack = new Stack<>();
    this.popedSet = new HashSet<>();
  }

  private void clearPoppedStack() {
    while (!stack.isEmpty() && popedSet.contains(stack.peek())) { // 沒有要一次清光，只有要清在stack持續頂的
      popedSet.remove(stack.peek());
      stack.pop();
    }
  }

  private void clearPoppedHeap() {
    while (!heap.isEmpty() && popedSet.contains(heap.peek())) {
      popedSet.remove(heap.peek());
      heap.poll();
    }
  }

  // O(logN) 因為heap.add() 是O(logN)
  public void push(int x) {
    Item item = new Item(x, globalNumber++); // globalNumber每次都會增加
    heap.add(item);
    stack.push(item);
  }

  // O(1) 因為clearPoppedStack() 是 O(1)
  public int pop() {
    clearPoppedStack(); // 因為popMax時並沒有真正從stack中pop掉max
    // heap.remove(stack.peek());  heap.remove() 會O(N)
    popedSet.add(stack.peek());
    return stack.pop().value;
  }

  // O(1) 因為clearPoppedStack() 是 O(1)
  public int top() {
    clearPoppedStack(); // 因為popMax時並沒有真正從stack中pop掉max
    return stack.peek().value;
  }


  // O(logN) 因為heap poll()操作
  public int peekMax() {
    clearPoppedHeap();  // 因為pop()時沒有動到heap
    return heap.peek().value;
  }

  // O(logN) 因為heap poll()操作
  public int popMax() {
    clearPoppedHeap(); // 因為pop()時沒有動到heap
    Item max = heap.poll();
    popedSet.add(max); // 這裡不會動到stack
    return max.value;
  }
}
