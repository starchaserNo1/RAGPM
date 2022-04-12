/*
 * @Descripttion: 
 * @version: 
 * @Author: Gzhlaker
 * @Date: 2021-10-25 17:51:50
 * @LastEditors: Andy
 * @LastEditTime: 2021-10-31 22:39:59
 */
package com.tree;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Pattern;


public class Util {
    /**
     * @description: 获取token 
     * @return: java.lang.String[]
     * @author: gzhlaker
     */
    public static String[] getTokens(String target) {
        String regString = "\\s*(?<=(;|\\(|\\)|,|:|\\d{1,100}\\.\\d{1,100}|[a-zA-Z]{1,100}))\\s*";
        String reGString = "\\s*(?<=(;|\\(|\\)|,|:|\\d{1,100}\\.\\d{1,100}\\d{1,100}|[a-zA-Z]{1,100}))\\s*";
        Pattern pattern = Pattern.compile(reGString);
        //System.out.println(pattern.split(target));
        return pattern.split(target);
    }
    /**
     * @description: 打印 token 
     * @return: void
     * @author: gzhlaker
     */
    public static void printTokens(String[] target) {
        for (String s : target) {
            System.out.println(s);
        }
    }
    
    public static void printTitle(String title) {
        System.out.println(title + "\n");
    }

    public static<T> void printMarkArray(T[] target, int start, int end) {
        Util.printArray(target);
        for (int i = 0; i < target.length; i++) {
            if (i == start || i == end) {
                System.out.print("   ^");
            } else {
                System.out.print("    ");
            }
        }
        System.out.println();
    }

    public static <T> void printMarkArray(T[] source1, T[] source2, T[][] result, int position) {
        Util.printMarkArrayRange(source1, 0, position - 1);
        Util.printMarkArrayRange(source2, position, source2.length);
        Util.printArray(result[0]);
        Util.printMarkArrayRange(source2, 0, position - 1);
        Util.printMarkArrayRange(source1, position, source2.length);
        Util.printArray(result[1]);
    }
    public static <T> void printMarkArray(T[] source1, T[] result, int start, int end) {
        Util.printMarkArrayRange(source1, start, end);
        Util.printMarkArrayRange(result, start, end);
    }
    public static<T> void printMarkArrayRange(T[] target, int start, int end) {
        printArray(target);
        for (int i = 0; i < target.length; i++) {
            if (i >= start && i <= end) {
                System.out.print("   ^");
            } else {
                System.out.print("    ");
            }
        }
        System.out.println();
    }

    /**
     * @description: 输出数组 
     * @return: void
     * @author: gzhlaker
     */
    public static<T> void printArray(T[] target){
        for (T s : target) {
            System.out.printf("%4s", s.toString());
            System.out.print("");
        }
        System.out.println();
    }
    /**
     * @description: 合并数组
     * @return: java.lang.String[]
     * @author: gzhlaker
     */
    public static <T> T[] margeArray(T[] a, T[] b) {
        T[] result = (T[]) Array.newInstance(a[0].getClass(),  a.length + b.length);
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;

    }

    /**
     * @description: 随机函数进行二选一
     * @return: java.lang.String[]
     * @author: gzhlaker
     */

    public static int  randomTwo() {
        Random random = new Random();
        double booLean = random.nextDouble();
        return booLean > 0.5 ? 1 : 0;
    }
    /**
     * @description: 随机函数进行二选一，概率不一样
     * @return: java.lang.String[]
     * @author: gzhlaker
     */
    public static int falseRandomTwo(double c ){
         Random random = new Random();
         double booLean = random.nextDouble();
         return booLean < c ? 0 : 1;
    }
    public static int falseRandomTwo_09(){
        Random random = new Random();
        double booLean = random.nextDouble();
        return booLean < 0.9 ? 0 : 1;
    }

    /**
     * @description: 随机函数进行n选1
     * @return: java.lang.String[]
     * @author: gzhlaker
     */
    public static int random_n() {
        return 0;
    }
    /**
     * @description: 交换两个数的值
     * @return: java.lang.String[]
     * @author: gzhlaker
     */
    public static void swap(int a, int b) {
        int temp;
        temp = a;
        a = b;
        b =temp;
    }
    /**
     * @description: 将某一字符串数组元素都取负号
     * @return: java.lang.String[]
     * @author: gzhlaker
     */
    public  static String[] Negate(String[] part3)
    {
        for(int i = 0; i < part3.length; i++) {
            if(Integer.parseInt(part3[i])>0)
            {
                part3[i] = "-"+part3[i];
            }
            else
            {
                part3[i] = Integer.toString(-Integer.parseInt(part3[i]));
            }
        }
        return part3;
    }
    /**
     * @description: 进行权值相应次数的各种操作
     * @return: java.lang.String[]
     * @author: gzhlaker
     */
    public static Genome events(Genome part, double quanzhi, double total, double a, double b,double c)
    {
        int number1 = (int) (quanzhi*total*a/(a+b+c));
        int number2 = (int) (quanzhi*total*b/(a+b+c));
        int number3 = (int) (quanzhi*total*c/(a+b+c));
        for (int i = 0; i <number1; i++) {
            part = part.reversal();
        }
        for (int i = 0; i < number2; i++) {
            part = part.Aversion();
        }
        for (int i = 0; i < number3; i++) {
            part = part.transposition();
        }
        return part;
    }
    public static double[] getRateArray(double[] array){
        double sum = 0;
        double[] newArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        for (int i = 0; i < array.length; i++) {
            BigDecimal _sum = new BigDecimal(Double.toString(sum));
            BigDecimal _i = new BigDecimal(Double.toString(array[i]));
            newArray[i] = _i.divide(_sum, 9,BigDecimal.ROUND_DOWN).doubleValue();
            //System.out.println(newArray[i]+"&");
        }
        return newArray;
    }
    //计算结构信息文件的总的权值
    public static double sum() throws IOException {
        double he = 0;
        File file = new File(Dataset.structFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String a = bufferedReader.readLine();
        String ss[]=a.split("[^-?\\d+.?\\d*]");
        for(String k:ss){
            if(k.matches("-?\\d+.?\\d*")) {
                double z = Double.parseDouble(k);
                he+=z;
            }
        }
                return he;
    }
}
