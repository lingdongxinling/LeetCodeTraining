package com.tsq.LeetCode;

import java.util.Hashtable;
import java.util.Stack;

public class Solution {
    private String[] args;

    public Solution(String[] args) {
        this.args = args;
    }

    //Q1
    public int[] TwoSum(int[] nums, int target) {
        //暴力解法
        // int numsLength=nums.Length;
        // for(int i=0;i<numsLength;i++){
        //     int sub=target-nums[i];
        //     for(int j=i+1;j<numsLength;j++){
        //         if(sub==nums[j]){
        //             return new int[2]{i,j};
        //         }
        //     }
        // }

        // return null;

        //哈希表法
        Hashtable table = new Hashtable();
        int numsLength = nums.length;
        for (int i = 0; i < numsLength; i++) {
            if (table.containsKey(target - nums[i])) {
                return new int[]{i, (int) table.get(target - nums[i])};
            }
            table.put(nums[i], i);
        }

        return null;
    }

    //Q3
    public int Reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }

        return rev;
    }

    //Q9
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        //添加末尾为0的判断
        if (x % 10 == 0 && x != 0) {
            return false;
        }

        //全部反转
        // int yushu = 0;
        // int fanzhuan = 0;
        // int xCopy = x;
        // while (xCopy > 0) {
        //     yushu = xCopy % 10;
        //     xCopy = xCopy / 10;
        //     if (fanzhuan > Integer.MAX_VALUE || (fanzhuan == Integer.MAX_VALUE && yushu > 7))
        //         return false;
        //     fanzhuan = 10 * fanzhuan + yushu;
        // }

        // return fanzhuan == x;

        //反转一半
        int fanzhuan = 0;
        while (x > fanzhuan) {
            fanzhuan = 10 * fanzhuan + x % 10;
            x /= 10;
        }
        return fanzhuan == x || x == fanzhuan / 10;
    }

    //Q13
    public int romanToInt(String s) {
        int result = 0;
        int numberLen = s.length();
        for (int i = 0; i < numberLen; i++) {
            char single = s.charAt(i);
            char next;
            switch (single) {
                case 'I':
                    if (i != numberLen - 1) {
                        next = s.charAt(i + 1);
                        if (next == 'V') {
                            result += 4;
                            i++;
                        } else if (next == 'X') {
                            result += 9;
                            i++;
                        } else {
                            result += 1;
                        }
                    } else {
                        result += 1;
                    }
                    break;
                case 'X':
                    if (i != numberLen - 1) {
                        next = s.charAt(i + 1);
                        if (next == 'L') {
                            result += 40;
                            i++;
                        } else if (next == 'C') {
                            result += 90;
                            i++;
                        } else {
                            result += 10;
                        }
                    } else {
                        result += 10;
                    }
                    break;
                case 'C':
                    if (i != numberLen - 1) {
                        next = s.charAt(i + 1);
                        if (next == 'D') {
                            result += 400;
                            i++;
                        } else if (next == 'M') {
                            result += 900;
                            i++;
                        } else {
                            result += 100;
                        }
                    } else {
                        result += 100;
                    }
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1000;
                    break;
                default:
                    break;
            }
        }

        return result;
    }

    //Q14
    public String longestCommonPrefix(String[] strs) {
        // My
//        int strsLength = strs.length;
//        if (strsLength == 0) {
//            return "";
//        } else if (strsLength == 1) {
//            return strs[0];
//        }
//
//        String result = "";
//        for (int i = 0; ; i++) {
//            for (int j = 1; j < strsLength; j++) {
//                if (strs[j].length() < i + 1 || strs[j - 1].length() < i + 1 || strs[j].charAt(i) != strs[j - 1].charAt(i)) {
//                    return result;
//                }
//            }
//            result +=strs[0].charAt(i);
//
//        }

        //横向搜索
//        if (strs == null || strs.length == 0) {
//            return "";
//        } else if (strs.length == 1) {
//            return strs[0];
//        }
//        String prefix = strs[0];
//        int count = strs.length;
//        for (int i=1;i<count;i++){
//            prefix=longestCommonPrefix(prefix,strs[i]);
//            if(prefix.length()==0){
//                break;
//            }
//        }
//        return prefix;

        //纵向搜索
//        if (strs == null || strs.length == 0) {
//            return "";
//        } else if (strs.length == 1) {
//            return strs[0];
//        }
//        int length = strs[0].length();
//        int count = strs.length;
//        for (int i = 0; i < length; i++) {
//            char c = strs[0].charAt(i);
//            for (int j = 1; j < count; j++) {
//                if (i == strs[j].length() || strs[j].charAt(i) != c) {
//                    return strs[0].substring(0, i);
//                }
//            }
//        }
//        return strs[0];

        //分治法
//        if (strs == null || strs.length == 0) {
//            return "";
//        } else if (strs.length == 1) {
//            return strs[0];
//        } else {
//            return longestCommonPrefix(strs, 0, strs.length - 1);
//        }

        //二分法
        if (strs == null || strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    public String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String lcpLeft = longestCommonPrefix(strs, start, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    public String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }

    public boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    //Q20
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int strLength = s.length();
        if (strLength == 0) {
            return true;
        }
        if (strLength % 2 == 1) {
            return false;
        }
        for (int i = 0; i < strLength; i++) {
            char tempChar = s.charAt(i);
            if (tempChar == '(' || tempChar == '[' || tempChar == '{') {
                stack.push(tempChar);
            } else {
                if (!stack.empty() && YiDui(stack.peek().charValue(), tempChar)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    public boolean YiDui(char a, char b) {
        switch (a) {
            case '(':
                return b == ')';
            case '[':
                return b == ']';
            case '{':
                return b == '}';
            default:
                return false;
        }
    }

    //Q21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //My
//        ListNode headNode = new ListNode();
//        ListNode tempNode = new ListNode();
//        headNode.next = tempNode;
//        if (l1 != null || l2 != null) {
//            if (l1 == null && l2 != null) {
//                tempNode.val = l2.val;
//                l2 = l2.next;
//            } else if (l1 != null && l2 == null) {
//                tempNode.val = l1.val;
//                l1 = l1.next;
//            } else {
//                if (l1.val > l2.val) {
//                    tempNode.val = l2.val;
//                    l2 = l2.next;
//                } else if (l1.val <= l2.val) {
//                    tempNode.val = l1.val;
//                    l1 = l1.next;
//                }
//            }
//        } else {
//            return null;
//        }
//
//        while (l1 != null || l2 != null) {
//            if (l1 == null && l2 != null){
//                tempNode.next = l2;
//                l2 = l2.next;
//            }else if(l1 != null && l2 == null){
//                tempNode.next = l1;
//                l1 = l1.next;
//            }else{
//                if (l1.val > l2.val) {
//                    tempNode.next = l2;
//                    l2 = l2.next;
//                } else if (l1.val <= l2.val) {
//                    tempNode.next = l1;
//                    l1 = l1.next;
//                }
//            }
//            tempNode = tempNode.next;
//        }
//
//        return headNode.next;

//        if (l1 == null) {
//            return l2;
//        } else if (l2 == null) {
//            return l1;
//        } else if (l1.val < l2.val) {
//            l1.next = mergeTwoLists(l1.next, l2);
//            return l1;
//        } else {
//            l2.next = mergeTwoLists(l1, l2.next);
//            return l2;
//        }

        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return preHead.next;
    }

    //Q26
    public int removeDuplicates(int[] nums) {
        //My
//        if(nums.length==0 || nums.length==1){
//            return nums.length;
//        }
//        int result = 0;
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (nums[i] == nums[i + 1]) {
//                continue;
//            }
//
//            result++;
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] != nums[j]) {
//                    i = j - 1;
//                    break;
//                }
//            }
//        }
//
//        return ++result;

        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    //Q27
    public int removeElement(int[] nums, int val) {
        int j = nums.length;
        if (j == 0) {
            return 0;
        }
        for (int i = 0; i < j; i++) {
            if (nums[i] == val) {
                nums[i] = nums[j - 1];
                i--;
                j--;
            }
        }
        return j;
    }

    //Q28
    public int strStr(String haystack, String needle) {
        int needleLength = needle.length();
        if (needleLength == 0) {
            return -1;
        }
        int hayStackLength = haystack.length();
        if (needleLength > hayStackLength) {
            return -1;
        }
        for (int i = 0; i <= hayStackLength - needleLength; i++) {
            int j = 0;
            for (; j < needleLength; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j >= needleLength) {
                return i;
            }
        }
        return -1;
    }

    public int[] GetNext(String p) {
        int[] next = new int[p.length()];
        next[0] = -1;
        int pLength = p.length();
        int k = -1;
        int j = 0;
        while (j < pLength - 1) {
            if (k == -1 || p.charAt(k) == p.charAt(j)) {
                ++k;
                ++j;
                if (p.charAt(j) != p.charAt(k)) {
                    next[j] = k;
                } else {
                    next[j] = next[k];
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public int KMPSearch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        int i = 0;
        int j = 0;
        int[] next = GetNext(p);
        while (i < sLen && j < pLen) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pLen) {
            return i - j;
        } else {
            return -1;
        }
    }

    //Q35
    public int searchInsert(int[] nums, int target) {
        int numsLength = nums.length;
        int left = 0, right = numsLength - 1, answer = numsLength;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target <= nums[mid]) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    //Q38
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String last = "1";
        int i = 2;
        int j;
        int count = 0;
        char lastChar = '\0';
        StringBuffer tempLast;
        while (i++ <= n) {
            lastChar = last.charAt(0);
            tempLast = new StringBuffer();
            count = 1;
            for (j = 1; j < last.length(); j++) {
                if (lastChar != last.charAt(j)) {
                    tempLast.append(count).append(lastChar);
                    lastChar = last.charAt(j);
                    count = 1;
                } else {
                    count++;
                }
            }
            last = tempLast.append(count).append(lastChar).toString();
        }

        return last.toString();
    }

    //Q53
    public int maxSubArray(int[] nums) {
        //动态规划
//        int pre = 0;
//        int max = nums[0];
//        for (int num : nums) {
//            pre = Math.max(pre + num, num);
//            max = Math.max(max, pre);
//        }
//
//        return max;

        //分治法
        return GetInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status GetInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = GetInfo(a, l, m);
        Status rSub = GetInfo(a, m + 1, r);
        return PushUp(lSub, rSub);
    }

    public Status PushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    //Q58
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }

        return end - start;
    }

    //Q66
    public int[] plusOne(int[] digits) {
        int dLen = digits.length;
        if(digits[dLen-1]!=9){
            digits[dLen-1]=digits[dLen-1]+1;
            return digits;
        }else{
            digits[dLen-1]=10;
        }

        boolean jinwei=false;
        for(int i=dLen-1;i>=1;i--){
            if(digits[i]==10){
                digits[i]=0;
                digits[i-1]=digits[i-1]+1;
            }
        }

        if(digits[0]==10){
            int[] newDigits = new int[dLen + 1];
            newDigits[0] = 1;
            digits[0] = 0;
            for (int i = 0; i < dLen; i++) {
                newDigits[i + 1] = digits[i];
            }
            return newDigits;
        }

        return digits;
//        for (int i = dLen - 1; i >= 0; i--) {
//            if(digits[i]!=9){
//                digits[i]=digits[i]+1;
//                return digits;
//            }
//
//            digits[i]=0;
//            if(i>0){
//                digits[i-1]
//            }
//        }

//        if (digits[0] == 0) {
//            int[] newDigits = new int[dLen + 1];
//            newDigits[0] = 1;
//            digits[0] = 0;
//            for (int i = 0; i < dLen; i++) {
//                newDigits[i + 1] = digits[i];
//            }
//            return newDigits;
//        } else {
//            return digits;
//        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution(args);

//        ListNode l1Head = new ListNode(1);
//        ListNode l1Second = new ListNode(2);
//        ListNode l1Third = new ListNode(4);
//        l1Head.next = l1Second;
//        l1Second.next = l1Third;
//
//        ListNode l2Head = new ListNode(1);
//        ListNode l2Second = new ListNode(3);
//        ListNode l2Third = new ListNode(4);
//        l2Head.next = l2Second;
//        l2Second.next = l2Third;

//        System.out.println(solution.strStr("aaaaa", "bba"));
//        ListNode headNode = solution.mergeTwoLists(l1Head, l2Head);
        int[] digits = solution.plusOne(new int[]{9,9});
//        System.out.println(solution.plusOne(new int[]{1, 2, 3}));
    }
}
