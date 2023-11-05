双指针 核心思路
* 快慢指针
  * 两个指针同向而行，一快一慢。 
  * 链表: 用来找 是否循环，找中点，倒数第k个等
  * 数组: 用于有序数组的去重，移除，移动

* 左右指针 就是两个指针相向而行或者相背而行
    * 二分查找 有序数组中搜索一个元素 target，找极值
    你是否能够找到一些规律，能够在搜索区间中一次排除掉一半
    1、确定 x, f(x), target 分别是什么，并写出函数 f 的代码。
    2、找到 x 的取值范围作为二分搜索的搜索区间，初始化 left 和 right 变量。（最小值，和最大值确定）
    3、根据题目的要求，确定应该使用搜索左侧还是搜索右侧的二分搜索算法，写出解法代码。
    分析二分查找的一个技巧是：不要出现 else，而是把所有情况用 else if 写清楚，这样可以清楚地展现
    ```agsl
        int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while(left <= right) {
        int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
           // 根据区间的不同，用法不同
           // return mid
           // right = mid - 1; // 寻找左侧边界，锁定右侧
           // left = mid + 1 // 寻找右侧边界，锁定左侧
            } else if (nums[mid] < target) {
                left = mid + 1
            } else if (nums[mid] > target) {
                right = mid - 1
            }
        }
        return -1;
    }
    ```
    * 回文检查

* 滑动窗口
    * 子字符串
    * 连续 最大/最小 计算
  ```agsl
            /* 滑动窗口算法框架 */
    void slidingWindow(string s) {
    // 用合适的数据结构记录窗口中的数据
    HashMap<char, int> window;
        
        int left = 0, right = 0;
        while (right < s.size()) {
            // c 是将移入窗口的字符
            char c = s[right];
            window.add(c)
            // 增大窗口
            right++;
            // 进行窗口内数据的一系列更新
            ...
        
            /*** debug 输出的位置 ***/
            // 注意在最终的解法代码中不要 print
            // 因为 IO 操作很耗时，可能导致超时
            printf("window: [%d, %d)\n", left, right);
            /********************/
                
            // 判断左侧窗口是否要收缩
            while (left < right && window needs shrink) {
                // d 是将移出窗口的字符
                char d = s[left];
                window.remove(d)
                // 缩小窗口
                left++;
                // 进行窗口内数据的一系列更新
                ...
            }
        }
    }
  ```