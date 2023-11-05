回溯算法
常用于获取所有排列/组合/子集的问题
无论是排列、组合还是子集问题，简单说无非就是让你从序列 nums 中以给定规则取若干元素，主要有以下几种变体：

1. 元素无重不可复选，即 nums 中的元素都是唯一的，每个元素最多只能被使用一次，这也是最基本的形式。

以组合为例，如果输入 nums = [2,3,6,7]，和为 7 的组合应该只有 [7]。

2. 形式二、元素可重不可复选，即 nums 中的元素可以存在重复，每个元素最多只能被使用一次。

以组合为例，如果输入 nums = [2,5,2,1,2]，和为 7 的组合应该有两种 [2,2,2,1] 和 [5,2]。

3. 形式三、元素无重可复选，即 nums 中的元素都是唯一的，每个元素可以被使用若干次。

以组合为例，如果输入 nums = [2,3,6,7]，和为 7 的组合应该有两种 [2,2,3] 和 [7]。

框架
```
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return
    
    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
```


元素无重不可复选
```java
class Solution {
    /* 组合/子集问题回溯算法框架 */
    void backtrack(int[] nums, int start) {
        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.addLast(nums[i]);
            // 注意参数
            backtrack(nums, i + 1);
            // 撤销选择
            track.removeLast();
        }
    }

    /* 排列问题回溯算法框架 */
    void backtrack(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 剪枝逻辑
            if (used[i]) {
                continue;
            }
            // 做选择
            used[i] = true;
            track.addLast(nums[i]);

            backtrack(nums);
            // 撤销选择
            track.removeLast();
            used[i] = false;
        }
    }
}
```

元素可重不可复选
即 nums 中的元素可以存在重复，每个元素最多只能被使用一次，其关键在于排序和剪枝，backtrack 核心代码如下：
```java
class Solution {
    Arrays.sort(nums);
    /* 组合/子集问题回溯算法框架 */
    void backtrack(int[] nums, int start) {
        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 剪枝逻辑，跳过值相同的相邻树枝
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            // 做选择
            track.addLast(nums[i]);
            // 注意参数
            backtrack(nums, i + 1);
            // 撤销选择
            track.removeLast();
        }
    }


    Arrays.sort(nums);
    /* 排列问题回溯算法框架 */
    void backtrack(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 剪枝逻辑
            if (used[i]) {
                continue;
            }
            // 剪枝逻辑，固定相同的元素在排列中的相对位置
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            // 做选择
            used[i] = true;
            track.addLast(nums[i]);

            backtrack(nums);
            // 撤销选择
            track.removeLast();
            used[i] = false;
        }
    }
}
```


形式三、元素无重可复选
即 nums 中的元素都是唯一的，每个元素可以被使用若干次，只要删掉去重逻辑即可，backtrack 核心代码如下：
没有used逻辑，无需去重
```java
class Solution {
    /* 组合/子集问题回溯算法框架 */
    void backtrack(int[] nums, int start) {
        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.addLast(nums[i]);
            // 注意参数
            backtrack(nums, i);
            // 撤销选择
            track.removeLast();
        }
    }


    /* 排列问题回溯算法框架 */
    void backtrack(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 做选择
            track.addLast(nums[i]);
            backtrack(nums);
            // 撤销选择
            track.removeLast();
        }
    }
}
```

