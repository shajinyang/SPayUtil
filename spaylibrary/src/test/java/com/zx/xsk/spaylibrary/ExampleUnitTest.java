package com.zx.xsk.spaylibrary;

import android.provider.Settings;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    /**
     * 求和算法
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    @Test
    public void testC() {
//        int[] args={1,4};
//        int dr=2;
//        System.out.println(findPoisonedDuration(args,dr));
//        System.out.println(reverse(123));
        System.out.println(isPalindrome(101));
    }

    /**
     * 提莫攻击
     *
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int totalTime = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            if (timeSeries.length > 1) {
                if ((timeSeries[i + 1] - timeSeries[i]) < duration) {
                    totalTime = totalTime + timeSeries[i + 1] - timeSeries[i];
                } else {
                    totalTime = totalTime + duration;
                }
            } else {
                return duration;
            }
        }
        if (timeSeries.length == 0) return totalTime;
        return totalTime + duration;
    }


    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x = x / 10;
        }
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) res;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int res = 0;
        Stack<Integer> stack = new Stack();
        Queue<Integer> queue = new LinkedList<>();

        while (x != 0) {
            res = x % 10;
            x = x / 10;
            queue.add(res);
            stack.push(res);
        }
        while (!queue.isEmpty()) {
            if (stack.peek() == queue.peek()) {
                stack.pop();
                queue.poll();
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void tetsL() {
        String[] strs = new String[]{"ab", "abcd"};
        System.out.println(longestCommonPrefix(strs));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length > 0) {
            String res = strs[0];
            for (int i = 1; i < strs.length; i++) {
                int j = 1;
                String comres = "";
                while (res.length() >= j && strs[i].length() >= j && res.subSequence(0, j).equals(strs[i].substring(0, j))) {
                    comres = res.substring(0, j);
                    j++;
                }
                res = comres;
            }
            return res;
        }
        return "";

    }

    @Test
    public void test6() {
        System.out.println(isValid("{[()([){(])()}[]]}"));
    }

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars
                ) {
            if (stack.empty()) {
                stack.push(c);
                continue;
            }
            if (('{'==stack.peek() && '}'==c)|| ('['==stack.peek() && ']'==c)|| ('('==stack.peek() && ')'==c)
                    ) {
                stack.pop();
            }else {
                stack.push(c);
            }

        }
        return stack.empty();
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }



}