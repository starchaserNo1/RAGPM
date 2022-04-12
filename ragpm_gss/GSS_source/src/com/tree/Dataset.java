/*
 * @Description:
 * @version: 
 * @Author: liu
 * @Date: 2021-10-26 09:54:16
 * @LastEditors: Andy
 * @LastEditTime: 2021-10-26 10:25:19
 */
package com.tree;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Dataset {
    /**
     * 一个常量，保存要读取的结构文件
     */
//    public final static String structFile = "D:/CharlesMiller/Research/Test/parent/createData/data/structure";
//    /**
//     * 一个常量，保存要读取的基因信息
//     */
//    public final static String genomeFile = "D:/CharlesMiller/Research/Test/parent/createData/data/genome";
//    public final static String parameterFile  = "D:/CharlesMiller/Research/Test/parent/createData/data/parameter";
//    public final static String path = "D:/CharlesMiller/Research/Test/parent/createData/data/";

    public static String structFile = "";
    /**
     * 一个常量，保存要读取的基因信息
     */
    public static String genomeFile = "";
    public static String parameterFile  = "";
    public static String path = "";
    /**
     * @description: 返回结构信息
     * @return: java.lang.String
     * @author: liu
     */
    public static String getStructData() throws IOException {
        File file = new File(Dataset.structFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String data = bufferedReader.readLine();
        bufferedReader.close();
        return data;
    }
    /**
     * @description: 返回基因信息 
     * @return: java.lang.String[]
     * @author: liu
     */
    public static String[] getGenomeData() throws IOException {
        File file = new File(Dataset.genomeFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        LinkedList<String> data = new LinkedList<>();
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            if(line.length() > 1)//如果基因组的长度是大于1的，说明是基因组的结构信息部分
            data.add(line);
        }
        bufferedReader.close();
        return data.toArray(new String[0]);
    }
    /**
     * @description: 返回首条染色体的长度
     * @return: java.lang.String[]
     * @author: liutianqi
     */
    public static  int getFirstGnomeLength() throws IOException {
       File file  = new File(Dataset.genomeFile);
       FileReader fileReader = new FileReader(file);
       BufferedReader bufferedReader = new BufferedReader(fileReader);
       int length = 0;//用于存储长度
        for(String line = bufferedReader.readLine() ; line != null ; line = bufferedReader.readLine())
        {
            if(line.length()>1)
            {
                length = line.length();
                break;
            }
        }
        return length;
    }
    /**
     * @description: 返回根节点名称
     * @return: java.lang.String[]
     * @author: liu
     */
    public static String[] getGenomeName() throws IOException {
        File file = new File(Dataset.genomeFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        LinkedList<String> data = new LinkedList<>();
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            if(line.length() == 1)//如果本行的长度只有1，说明这是基因组名字的标识
            data.add(line);
        }
        bufferedReader.close();
        return data.toArray(new String[0]);
    }
    //对操作的参数文件中的事件数量进行读取
    public static String getParameter_01() throws IOException{
        File file  = new File(Dataset.parameterFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String paraMeter = bufferedReader.readLine();
        return paraMeter ;
    }
    //对操作参数文件中的比例文件进行读取
    public static double[] getParameter_02() throws  IOException{
        File file  = new File(Dataset.parameterFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        double data [] = new double[4];
        String paraMeter = null;
        for(String line  = bufferedReader.readLine() ; line != null ; line  = bufferedReader.readLine()){
            //if(line.length() > 1) {
                paraMeter = bufferedReader.readLine();
               // System.out.println(line.length());
            //}
        }

        String[] c = paraMeter.split(" ");//以空格为分隔符
        for(int i=0;i<c.length;i++)
        {
            data[i] = Double.parseDouble(c[i]);
            System.out.println(data[i]);
        }
        return data;


    }
    public static void saveFile(String fileName ,String[][] chromosomes, LinkedList<OptionType> options) throws IOException {
        File optionsFile = new File(Dataset.path + fileName + "_options.txt");
        File chromosomesFile = new File(Dataset.path + fileName + "_chromosomes.txt");

        FileWriter fileWriter = new FileWriter(chromosomesFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String result;
        for (int i = 0; i < chromosomes.length; i++) {
            result = new String("");
            for (int j = 0; j < chromosomes[i].length; j++) {
                result += (chromosomes[i][j] + " ");
            }
            bufferedWriter.write(result);
            bufferedWriter.write("\n");
        }
        bufferedWriter.close();
        fileWriter.close();

        FileWriter fileWriter2 = new FileWriter(optionsFile);
        BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
        String result2 = new String();
        for (int i = 1; i <= options.size(); i++) {
            result2 += (options.get(i-1).toString() + " ");
            if(i % 10 == 0){
                result2 += "\n";
            }
        }
        bufferedWriter2.write(result2);
        bufferedWriter2.close();
        fileWriter2.close();
    }
}
