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

11 [盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/)
暴力求解
```java
public class Solution {
    public int maxArea(int[] height) {
        int length = height.length;
        int maxArea = 0;
        int currentArea = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                currentArea = Math.min(height[i],height[j]) * (j - i);
                if (currentArea > maxArea){
                    maxArea = currentArea;
                }
            }
        }
        return maxArea;
    }
}
```
双指针
```java
public class Solution {
    public int maxArea(int[] height) {
        int len = height.length;
        int m = 0, n = len - 1;
        int maxArea = 0;
        int currentArea = 0;
        while (n > m){
            currentArea = (n - m) * Math.min(height[m],height[n]);
            if (currentArea > maxArea ){
                maxArea = currentArea;
            }
            if(height[m] > height[n]){
                n--;
            } else {
                m++;
            }
        }
        return maxArea;
    }
}
```
# 12 [整数转罗马数字](https://leetcode-cn.com/problems/integer-to-roman/)
贪心
```java
public class Solution {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length && num != 0; i++) {
            while (num > values[i]) {
                num -= values[i];
                roman.append(romans[i]);
            }
        }
        return roman.toString();
    }
}
```
# 13 [罗马数字转整数](https://leetcode-cn.com/problems/roman-to-integer/)
```java
public class Solution {
    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
    public int romanToInt(String s) {
        int preNum = getValue(s.charAt(0));
        int len = s.length();
        int value = 0;
        int currNum;
        for (int i = 1; i < len; i++) {
            currNum = getValue(s.charAt(i));
            if (preNum >= currNum){
                value += preNum;
            } else {
                value -= preNum;
            }
            preNum = currNum;
        }
        return value + preNum;
    }
}
```

# 14 [最长公共前缀](https://leetcode-cn.com/problems/longest-common-prefix/)
```java
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len == 0 ){
            return "";
        }
        // 以strs[0]为基准，遍历其他字符串，比较对应位置的字符是否相同，不同则退出循环，找到公共前缀
        String ans = strs[0];
        for (int i = 1; i < len; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j)){
                    break;
                }
            }
            ans = ans.substring(0,j);
            if ("".equals(ans)){
                return "";
            }
        }
        return ans;
    }
}
```

# 15 [三数之和](https://leetcode-cn.com/problems/3sum/)
经典题，排序+双指针
```java
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        // 排序
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            // 每次a都不相同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int k = len - 1;
            // b+c目标值
            int target = -nums[i];
            for (int j = i + 1; j < len; j++) {
                // 每次b都不同
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // b+c>target 则左移c
                while (j < k && nums[j] + nums[k] > target) {
                    k--;
                }
                // 不允许j k 重合
                if (j == k) {
                    break;
                }
                if (nums[j] + nums[k] == target) {
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(nums[i]);
                    integers.add(nums[j]);
                    integers.add(nums[k]);
                    lists.add(integers);
                }
            }
        }
        return lists;
    }
}
```

# 16 [最接近的三数之和](https://leetcode-cn.com/problems/3sum-closest/)
15题的升级，加一个找到最接近的数的逻辑
```java
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int closestNum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = len - 1;
            while (j < k) {
                int currentNum = nums[i] + nums[j] + nums[k];
                if (currentNum == target) {
                    return currentNum;
                }
                if (Math.abs(currentNum - target) < Math.abs(closestNum - target)) {
                    closestNum = currentNum;
                }
                if (currentNum > target) {
                    --k;
                    // 排除相同的c元素，加速运算
                    while (k > j && nums[k] == nums[k + 1]){
                        --k;
                    }
                } else {
                    ++j;
                    while (k > j && nums[j] == nums[j - 1]){
                        ++j;
                    }
                }
            }
        }
        return closestNum;
    }
}
```

# 17 [电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)
回溯递归
```java
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        HashMap<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "uvw");
        map.put('9', "xyz");
        backtrack(combinations, map, digits, 0, new StringBuilder());
        return combinations;
    }

    private void backtrack(List<String> combinations, HashMap<Character, String> map, String digits, int index,
                           StringBuilder combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = map.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, map, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
}
```

# 18 [四数之和](https://leetcode-cn.com/problems/4sum/)
```java
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                int k = j + 1;
                int l = len - 1;
                while (k < l){
                    int sum = nums[i] + nums[j]+ nums[k] + nums[l];
                    if (sum == target){
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
                        lists.add(list);
                    }
                    if (sum > target){
                        --l;
                        while (l > k && nums[l] == nums[l+1]){
                            --l;
                        }
                    } else {
                        ++k;
                        while (l > k && nums[k] == nums[k-1]){
                            ++k;
                        }
                    }
                }
            }
            
        }
        return lists;
    }
}
```
优化
```java
public class Solution {
    // 优化后39ms -> 6ms
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            // 优化：如果nums[i]与最大的三个数相加依然小于target，那么这个数可以跳过
            int maxi1 = nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3];
            if (maxi1 < target){
                continue;
            }
            // 优化：如果nums[i]与最小的三个数相加依然大于target，循环可以终止
            int maxi2 = nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (maxi2 > target){
                continue;
            }

            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                // 优化：如果nums[i],nums[j]与最大的两个数相加依然小于target，那么这个数可以跳过
                int maxj1 = nums[i] + nums[len - 1] + nums[len - 2] + nums[j];
                if (maxj1 < target){
                    continue;
                }
                // 优化：如果nums[i]，nums[]与最小的三个数相加依然大于target，循环可以终止
                int maxj2 = nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                if (maxi2 > target){
                    continue;
                }

                int k = j + 1;
                int l = len - 1;
                while (k < l){
                    int sum = nums[i] + nums[j]+ nums[k] + nums[l];
                    if (sum == target){
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
                        lists.add(list);
                    }
                    if (sum > target){
                        --l;
                        while (l > k && nums[l] == nums[l+1]){
                            --l;
                        }
                    } else {
                        ++k;
                        while (l > k && nums[k] == nums[k-1]){
                            ++k;
                        }
                    }
                }
            }
            
        }
        return lists;
    }
}
```

# 19 [删除链表的倒数第N个节点](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/)
两次遍历
```java
public class Solution {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = new ListNode(0);
        first.next = head;
        ListNode node = head;
        int len = 0;
         while (node != null){
             len++;
             node = node.next;
         }
         node = first;
        for (int i = 0; i < len - n ; i++) {
            node = node.next;
        }
        node.next = node.next.next;
        return first.next;

    }

}
```
一次遍历
```java
public class Solution {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = new ListNode(0);
        first.next = head;
        int len = 0;
        ListNode node1 = first;
        ListNode node2 = first;
        for (int i = 0; i <= n; i++) {
            node1 = node1.next;
        }
        while (node1 != null) {
            node1 = node1.next;
            node2 = node2.next;
        }
        node2.next = node2.next.next;
        return first.next;
    }
}
```

# 20 [有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)
```java
public class Solution20 {
    public boolean isValid(String s) {
        int len = s.length();
        if (len == 0) {
            return true;
        }
        if ((len % 2) == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (stack.isEmpty() || !stack.peek().equals(map.get(c))) {
                    return false;
                }
                stack.pop();

            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
```
