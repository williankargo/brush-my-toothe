package leetcode.dataStructure;

import java.util.ArrayDeque;
import java.util.Queue;

public class ImplementStackByTwoQueues_494 {

  private Queue<Integer> queue;
  private Queue<Integer> buffer;

  public ImplementStackByTwoQueues_494() {
    this.queue = new ArrayDeque<>();
    this.buffer = new ArrayDeque<>();
  }

  // O(1)
  public void push(int x) {
    queue.offer(x);
  }

  // O(N) 每次都會搞剛，平均也一樣
  public void pop() {
    moveItems();
    queue.poll();
    swapQueues();
  }

  // O(N) 每次都搞剛，平均也一樣
  public int top() {
    moveItems();
    Integer item = queue.poll();
    swapQueues();
    queue.offer(item);
    return item;
  }

  // O(1)
  public boolean isEmpty() {
    return queue.isEmpty();
  }

  private void moveItems() {
    while (queue.size() != 1) {
      buffer.offer(queue.poll());
    }
  }

  private void swapQueues() {
    Queue<Integer> temp = buffer;
    buffer = queue;
    queue = temp;
  }
}