package com.zx.xsk.spayutil;

import android.util.Log;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

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


    public void test(Class<?> cl) {
        for (Method method : cl.getDeclaredMethods()
                ) {
            CaseAnnotation testAnnotation = method.getAnnotation(CaseAnnotation.class);
            if (testAnnotation != null) {
                System.out.print(testAnnotation.mId() + "|" + testAnnotation.mDescription());
            }
        }
    }

    @Test
    public void begainTest() {
        test(ExampleMain.class);
    }

    public void countNums() {
        int s = 1;
        for (int i = 1; i < 21; i++) {
            if (i < 4) {
                s = 1;
            }

        }
    }

    /**
     * 测试括号配对
     */
    @Test
    public void test2() {
        Stack<String> stack=new Stack<>();
        String[] args = {
                "([]))",
                "([]())",
                "([(]))",
                "[[]([]())]",
                "([)(]())",
                "([])()",
        };
        for (int i=0;i<args.length;i++){
            boolean isM=true;
            String val=args[i];
            for(int j=0;j<val.length();j++){
                if(val.charAt(j)=='['||val.charAt(j)=='('){
                    stack.push(val.charAt(j)+"");
                }else {
                    if(!stack.isEmpty()&&("[").equals(stack.peek())&&("]").equals(val.charAt(j)+"")){
                        isM=true;
                        stack.pop();
                        continue;
                    }else if(!stack.isEmpty()&&("(").equals(stack.peek())&&(")").equals(val.charAt(j)+"")){
                        isM=true;
                        stack.pop();
                        continue;
                    }else {
                        isM=false;
                        break;
                    }
                }
            }
            if(isM==true&&stack.isEmpty()){
                isM=true;
            }else {
                isM=false;
            }
            System.out.println(isM);
            stack.clear();

        }

    }

    /**
     * 韩信点兵
     */
    @Test
    public void testHx(){
        int a3=2;
        int a5=4;
        int a7=5;
        int x=0;
        do {
            x++;
        }
        while (x%3!=a3||x%5!=a5||x%7!=a7);
        System.out.println(x);

    }

    /**
     * 蛇形输出数组
     */
    @Test
    public void  testSnake(){
        int[] args1={13,14,15,16,1};
        int[] args2={12,23,24,17,2};
        int[] args3={ 11,22,25,18,3};
        int[] args4={ 10,21, 20, 19,4};
        int[] args5={9, 8, 7, 6,5};
        int[][] argsall={args1,args2,args3,args4,args5};
        int column=5;
        int startX=0;
        int startY=column-1;
        do{
            int x=startX;
            int y=startY;
            for(int i=startX;i<column-startX;i++){
                System.out.print(argsall[x][y]+",");
                x++;
            }
            for(int i=startX;i<column-startX-1;i++){
                System.out.print(argsall[x-1][y-1]+",");
                y--;
            }
            for(int i=startX;i<column-startX-1;i++){
                System.out.print(argsall[x-2][y]+",");
                x--;
            }
            for(int i=startX;i<column-startX-2;i++){
                System.out.print(argsall[x-1][y+1]+",");
                y++;
            }
            startX++;
            startY--;
        }
        while (startX*2<column);
    }

    @Test
    public void testAssert(){
        int a=0;
    }

}