package leetcode.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class subsets_mode2l_BFS_17 {

  public List<List<Integer>> subsets(int[] nums) {
    if (nums == null) {
      return new ArrayList<>();
    }

    List<List<Integer>> queue = new ArrayList<>();
    queue.add(new LinkedList<Integer>()); // 這裡可以用LinkedList，因為後面不會針對來get()
    Arrays.sort(nums);

    for (int num : nums) {
      int size = queue.size();
      for (int i = 0; i < size; i++) { // 同層
        List<Integer> subset = new ArrayList<>(queue.get(i));
        subset.add(num);
        queue.add(subset);
      }
    }

    return queue;
  }
}

// {}
// {}, {1}
// {}, {1}, {2}, {1,2}
// {}, {1}, {2}, {1,2}, {3}, {1,3}, {2,3}, {1,2,3} #