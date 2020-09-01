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
```java
public class Solution {
    public String convert(String s, int numRows) {
        int len = s.length();
        // 行数为1 或者 s字符串长度小于行数，s不会变,直接返回s就行
        if (numRows == 1 || len <= numRows) {
            return s;
        }
        // z字型排列每行都会产生一个字符串，共numRows个
        StringBuilder[] rows = new StringBuilder[numRows];
        // 初始化
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }
        // 当前行号索引
        int rowIndex = 0;
        // 判断是否向下
        boolean down = false;
        for (char c : s.toCharArray()) {
            rows[rowIndex].append(c);
            // rowIndex在numRows - 1 和 0 之间变动，到达某一个端点时，将down取反
            if (rowIndex == numRows - 1|| rowIndex == 0) {
                down = !down;
            }
            // down为true rowIndex递增，为false就递减
            rowIndex += down ? 1 : -1;
        }
        // 将rows合并
        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) {
            ans.append(row.toString());
        }
        return ans.toString();
    }
}
```

# 7 [整数反转](https://leetcode-cn.com/problems/reverse-integer/)
```java
public class Solution {
    public int reverse(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x /= 10;
        }
        return (int) n == n ? (int) n : 0;
    }
}
```

# 8 [字符串转换整数 (atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/)
```java
public class Solution8 {
    public int myAtoi(String str) {
        // 去空格
        str = str.trim();
        int len = str.length();
        if (len == 0) {
            return 0;
        }
        char firstChar = str.charAt(0);
        // 首字符不是正负号或数字
        if (!Character.isDigit(firstChar)
                && '+' != firstChar
                && '-' != firstChar) {
            return 0;
        }
        boolean flag = firstChar == '-';
        long ans = 0L;
        int i = Character.isDigit(firstChar) ? 0 : 1;
        char temp;
        while (i < len && Character.isDigit(temp = str.charAt(i))) {
            ans = ans * 10 + (temp - '0');
            if (flag && ans > 1L + Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE + 1L;
                break;
            }
            if (!flag && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;
            }
            i++;
        }
        return flag ? (int) -ans : (int) ans;
    }
}
```

# 9 [回文数](https://leetcode-cn.com/problems/palindrome-number/)
直接反转整数
```java
public class Solution9 {
    public boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }
        // 先反转x
        int y = x;
        long n = 0L;
        while (x != 0){
            n = n * 10 + x % 10;
            x /= 10;
        }
        if (n > Integer.MAX_VALUE){
            return false;
        }
        return (int) n == y;
    }
}
```
实际上只需要反转一半
```java
class Solution{
     public boolean isPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
```

# 10 [正则表达式匹配](https://leetcode-cn.com/problems/regular-expression-matching/)
采用动态规划
```java
public class Solution10 {
    public boolean isMatch(String s, String p) {
        int sLength = s.length();
        int pLength = p.length();
        // dp 表示前i个s字符和前j个p字符是否匹配
        boolean[][] dp = new boolean[sLength + 1][pLength + 1];
        dp[0][0] = true;
        for (int i = 0; i <= sLength; i++) {
            for (int j = 1; j <= pLength; j++) {
                if (p.charAt(j - 1) == '*') {
                    // *前的字符和*可以去除
                    dp[i][j] = dp[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        // i 和 j - 1 匹配上，加上不去除*前的字符和*的情况 i - 1 和 i 相同时，dp[i-1][j]=dp[i][j]
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[sLength][pLength];
    }

    /**
     * @return boolean
     * @Description: // 判断第i个s字符和第j个p字符是否匹配，第一个为0开始
     * @Author: xldeng
     * @Date: 2020/9/1 21:42
     * @Param [s, p, i, j]
     **/
    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
```