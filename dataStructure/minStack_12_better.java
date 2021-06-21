package leetcode.dataStructure;

import java.util.Stack;

public class minStack_12_better {


  Stack<Integer> stack, minStack;

  public minStack_12_better() {
    stack = new Stack<>();
    minStack = new Stack<>();
  }

  // 夠小再加到minStack裡
  public void push(int number) {
    stack.push(number);
    if (minStack.empty() || number <= minStack.peek()) { // 相等也要加進去
      minStack.push(number);
    }
  }

  public int pop() {
    int number = stack.pop();
    if (number == minStack.peek()) {
      minStack.pop();
    }
    return number;
  }

  public int min() {
    return (minStack.peek());
  }

}
