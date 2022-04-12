/*
 * @Description:
 * @version: 
 * @Author: Gzhlaker
 * @Date: 2021-10-25 17:09:14
 * @LastEditors: 刘天奇
 * @LastEditTime: 2022-2-18
 * @规模：1510行
 */
package com.tree;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
            for(int zz = 0 ;zz< 500;zz++)
            {
                    //System.out.println(Util.sum());
                    int options = Integer.parseInt(Dataset.getParameter_01());//获取发生在进化树上的总的事件数量
                    //初始化树
                    Tree t = new Tree(Dataset.getGenomeData(), Dataset.getStructData(), options);
                    t.knowFather();
                    t.levelOrder();
                    t.createOptionsNumber();//每个节点分配到的操作数
                    t.createInternalName();//中间节点的名称
                    double[] a = Dataset.getParameter_02();//获取各个事件的比例
                    double[] b = Util.getRateArray(a);//函数用于计算每种操作的实际比例
                    //b中存储每种操作真实所占的比例
                    for (int i = 0; i < b.length; i++) {
                            System.out.println(b[i] + "*");
                    }
                    t.createOptions(b);
                    t.createGenome();
                    t.printGenome();
                    t.saveData();
            }
    }
}
