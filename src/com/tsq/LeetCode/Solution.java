package com.tsq.LeetCode;

import java.util.Hashtable;

public class Solution {
    private String[] args;

    public Solution(String[] args) {
        this.args = args;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(args);

        int result = solution.romanToInt("III");
        System.out.println(result);
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
        int numberLen=s.length();
        for (int i = 0; i < numberLen; i++) {
            char single = s.charAt(i);
            char next;
            switch (single) {
                case 'I':
                    if(i!=numberLen-1){
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
                    }else {
                        result += 1;
                    }
                    break;
                case 'X':
                    if(i!=numberLen-1){
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
                    }else {
                        result += 10;
                    }
                    break;
                case 'C':
                    if(i!=numberLen-1){
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
                    }else {
                        result += 100;
                    }
                    break;
                case 'V':
                    result+=5;
                    break;
                case 'L':
                    result+=50;
                    break;
                case 'D':
                    result+=500;
                    break;
                case 'M':
                    result+=1000;
                    break;
                default:
                    break;
            }
        }

        return result;
    }
}
