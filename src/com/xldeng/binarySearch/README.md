二分查找
## 704 [二分查找](https://leetcode-cn.com/problems/binary-search/)
```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -1;
    }
}
```

## 34 [在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
```java

```