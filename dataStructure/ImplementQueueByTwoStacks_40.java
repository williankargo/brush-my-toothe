package leetcode.dataStructure;

import java.util.Stack;

public class ImplementQueueByTwoStacks_40 {

  Stack<Integer> stack;
  Stack<Integer> buffer;

  public ImplementQueueByTwoStacks_40() {
    stack = new Stack<>();
    buffer = new Stack<>();
  }

  private void stackToBuffer() {
    while (!stack.isEmpty()) {
      buffer.push(stack.pop());
    }
  }

  // O(1)
  public void push(int element) {
    stack.push(element);
  }


  // O(1) 非O(N) 因為stackToBuffer()不會每次都用到，平均下來是 O(1)
  public int pop() {

    while (buffer.isEmpty()) {
      stackToBuffer();
    }
    return buffer.pop();
  }


  // O(1) 非O(N) 因為stackToBuffer()不會每次都用到，平均下來是 O(1)
  public int top() {
    while (buffer.isEmpty()) {
      stackToBuffer();
    }
    return buffer.peek();
  }
}