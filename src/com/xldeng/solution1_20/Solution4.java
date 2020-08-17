package com.xldeng.solution1_20;

/**
 * @ClassName: Solution4
 * @Description: 4. 寻找两个正序数组的中位数
 * @Author: xldeng
 * @Date: 2020/8/17 12:40
 * @Version: 1.0
 **/
public class Solution4 {
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
}