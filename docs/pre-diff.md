前缀和差分 核心思路
* 前缀数组
    * 快速、频繁地计算一个索引区间内的元素之和 / 乘
    * 原始数组不会被修改的情况
    * 可以配合hashMap核外储存一些信息，记录条件
     ```java
     class PrefixSum {
     // 前缀和数组
     private int[] preSum;
   
         /* 输入一个数组，构造前缀和 */
         public PrefixSum(int[] nums) {
             // preSum[0] = 0，便于计算累加和
             preSum = new int[nums.length + 1];
             // 计算 nums 的累加和
             for (int i = 1; i < preSum.length; i++) {
                 preSum[i] = preSum[i - 1] + nums[i - 1];
             }
         }
       
         /* 查询闭区间 [left, right] 的累加和 */
         public int sumRange(int left, int right) {
             return preSum[right + 1] - preSum[left];
         }
     }
     ```
* 差分数组
    * 频繁对原始数组的某个区间的元素进行增减。
    * n(1)的修改
  ```java
  class Difference {
  // 差分数组
  private int[] diff;
    
      /* 输入一个初始数组，区间操作将在这个数组上进行 */
      public Difference(int[] nums) {
          assert nums.length > 0;
          diff = new int[nums.length];
          // 根据初始数组构造差分数组
          diff[0] = nums[0];
          for (int i = 1; i < nums.length; i++) {
              diff[i] = nums[i] - nums[i - 1];
          }
      }
    
      /* 给闭区间 [i, j] 增加 val（可以是负数）*/
      public void increment(int i, int j, int val) {
          diff[i] += val;
          if (j + 1 < diff.length) {
              diff[j + 1] -= val;
          }
      }
    
      /* 返回结果数组 */
      public int[] result() {
          int[] res = new int[diff.length];
          // 根据差分数组构造结果数组
          res[0] = diff[0];
          for (int i = 1; i < diff.length; i++) {
              res[i] = res[i - 1] + diff[i];
          }
          return res;
      }
  }
  ```