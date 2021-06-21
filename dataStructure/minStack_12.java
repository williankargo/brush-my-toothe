package leetcode.dataStructure;

import java.util.Stack;

public class minStack_12 {

  Stack<Integer> stack, minStack;

  public minStack_12() {
    stack = new Stack<>();
    minStack = new Stack<>();
  }

  public void push(int number) {
    stack.push(number);
    if (minStack.empty() || number < minStack.peek()) { // minStack初始沒值
      minStack.push(number);
    } else {
      minStack.push(minStack.peek());
    }
  }

  public int pop() {
    minStack.pop();
    return (stack.pop());
  }

  public int min() {
    return (minStack.peek());
  }
}