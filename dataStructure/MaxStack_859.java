package leetcode.dataStructure;

import java.util.Stack;

public class MaxStack_859 {

  Stack<Integer> stack;
  Stack<Integer> maxStack;

  public MaxStack_859() {
    this.stack = new Stack<>();
    this.maxStack = new Stack<>();
  }


  public void push(int x) {
    int max = maxStack.isEmpty() ? x : maxStack.peek();  // 記得要考慮初始條件
    maxStack.push(max > x ? max : x);
    stack.push(x);
  }

  public int pop() {
    maxStack.pop();
    return (stack.pop());
  }

  public int top() {
    return (stack.peek());
  }

  public int peekMax() {
    return maxStack.peek();
  }

  public int popMax() {

    int max = maxStack.peek();
    Stack<Integer> buffer = new Stack<>();
    while (top() != max) {
      buffer.push(pop());
    }
    pop();

    while (!buffer.isEmpty()) {
      push(buffer.pop());
    }

    return max;
  }
}