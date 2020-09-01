# 1.[ 两数之和](https://leetcode-cn.com/problems/two-sum/)

hash表

```java
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        //使用HashMap，key为nums[i],value为i
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //比较已存入HashMap的key中是否存在要求的值target - nums[i]
            //若存在，返回两数在nums中的索引  同时避免判断是否有重复的索引
            if (hashMap.containsKey(target - nums[i])){
                return new  int[]{hashMap.get(target - nums[i]),i};
            }
            hashMap.put(nums[i],i);
        }
        return null;
    }
}
```

# 2.[两数相加](https://leetcode-cn.com/problems/add-two-numbers/)

```java
public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //第一位设为0，最后面返回result.next，去除第一位
        ListNode result = new ListNode(0);
        //pointer作为指针
        ListNode pointer = result;
        //offset == 1 有进位 offset == 0 无进位
        int offset = 0;
        while (l1 != null || l2 != null) {
            //提取l1,l2对应数字，null则用0表示
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            //sum需要加上进位
            int sum = x + y + offset;
            if (sum > 9) {
                offset = 1;
                sum -= 10;
            } else {
                offset = 0;
            }
            pointer.next = new ListNode(sum);
            //pointer,l1,l2均赋值next
            pointer = pointer.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //最高位有进位则需再加上一位1
        if (offset != 0) {
            pointer.next = new ListNode(1);
        }
        return result.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}
```



# 3 [无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int right = 0, ans = 0;
        int n = s.length();
        HashSet<Character> set = new HashSet<>();
        for(int left = 0; left < n; left++){
            if (left != 0){
                set.remove(s.charAt(left - 1));
            }
            while (right< n && !set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                right++;
            }
            ans = Math.max(ans, right - left);
            right--;
        }
        return ans;
    }
}
```

# 4* [寻找两个正序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/)
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int totalLength = length1 + length2;
        if ((totalLength & 1) == 0) {
            // 两个数组总长度为偶数，中位数为第 k=totalLength/2和 k=totalLength/2 + 1 大的数的均值
            int mid = totalLength >> 1;
            return (getKthElement(nums1, nums2, mid) + getKthElement(nums1, nums2, mid + 1)) / 2.0;
        } else {
            // 两个数组总长度为奇数，中位数就是 第 k=totalLength / 2 + 1大的数
            return getKthElement(nums1, nums2, (totalLength >> 1) + 1);
        }


    }

    /**
     * @return int
     * @Description: // 二分法寻找两个数组中第k大的数
     * @Author: xldeng
     * @Date: 2020/8/17 15:12
     * @Param [nums1, nums2, k]
     **/
    public int getKthElement(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        // index1和index2 表示当前索引左侧的值全舍去
        int index1 = 0, index2 = 0;

        while (true) {
            // 边界
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            int half = k / 2;
            int newIndex1 = (Math.min(index1 + half, length1)) - 1;
            int newIndex2 = (Math.min(index2 + half, length2)) - 1;
            // 每次循环比较两个数组新的索引的数的大小，小的一方全舍去，相当于把索引右移，同时k对应的值
            if (nums1[newIndex1] > nums2[newIndex2]) {
                k -= (newIndex2 - index2) + 1;
                index2 = newIndex2 + 1;
            } else {
                k -= newIndex1 - index1 + 1;
                index1 = newIndex1 + 1;
            }
        }
    }
}
```
# 5 [最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)
## 动态规划
```java
public class Solution {
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        // dp记录回文开始和结束索引
        boolean[][] dp = new boolean[length][length];
        char[] sChars = s.toCharArray();
        // 回文开始索引
        int begin = 0;
        // 回文最大长度
        int maxLength = 1;
        // 初始化每个字符都是回文
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < length; j++) {
            for (int i = 0; i < j; i++) {
                // 子串首尾不同
                if (sChars[i] != sChars[j]) {
                    dp[i][j] = false;
                }
                // 子串首尾相同
                else {
                    // 子串长度小于4 可直接判断为true
                    if (j - i < 3) {
                        dp[i][j] = true;
                    }
                    // 子串首尾相同，并且子串长度大于4，该情况与去掉子串首尾的情形一致
                    else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > maxLength) {
                    begin = i;
                    maxLength = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + maxLength);
    }

}
```
## 中心扩散
```java
public class Solution5_2 {
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        String ans = s.substring(0, 1);
        for (int i = 0; i < length - 1; i++) {
            String oddString = getMaxString(s, i, i);
            String evenString = getMaxString(s, i, i + 1);
            String temp = oddString.length() > evenString.length() ? oddString : evenString;
            if (temp.length() > ans.length()) {
                ans = temp;
            }
        }
        return ans;
    }

    private String getMaxString(String s, int left, int right) {
        int length = s.length();
        while (left >= 0 && right < length) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return s.substring(left + 1, right);
    }
}
```

# 6 [Z 字形变换](https://leetcode-cn.com/problems/zigzag-conversion/)
