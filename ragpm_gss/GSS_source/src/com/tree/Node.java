/*
 * @Descripttion: 
 * @version: 
 * @Author: Gzhlaker
 * @Date: 2021-10-25 17:09:34
 * @LastEditors: Andy
 * @LastEditTime: 2021-10-26 10:30:57
 */
package com.tree;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Node {
    /**
     * 一个节点的名字
     */
    private String name;
    /**
     * 一个节点到双亲节点的权值
     */
    private double weight;
    /**
     * 一个节点被分配到的操作个数
     */
    private int optionsNumebr;

    /**
     * 一个节点是否为叶子节点
     */
    private boolean isLeaf;
    /**
     * 储存一个节点的父亲节点
     */
    private Node father;
    /**
     * 一个链表每个表成员指向一个从此节点到根节点的祖先节点
     */
    private LinkedList<Node> parents;
    /**
     * 一个链表每个表成员指向一个孩子节点
     */
    private LinkedList<Node> children;
    /**
     * 一个链表用来储存所有的操作
     */
    private LinkedList<OptionType> options;
    /**
     * 一个节点的基因组数据，数组的每个元素代表一条染色体数据
     */
    private String[] genomes;
    /**
     * 一个节点的基因组中的所有染色体，去除了数据中的 "$"
     */
    private String[] chromosomes;

    //结构信息中权值相加
    public double sum;

    /**
     * @description: 默认构造函数
     * @return:
     * @author: gzhlaker
     */
    private Genome genome;
    public Node() {
        this.parents = new LinkedList<>();
        this.children = new LinkedList<>();
        this.name = "";
        this.options = new LinkedList<>();
    }
    /**
     * @description: 传入一个节点的基因组数据构造节点
     * @return: 
     * @author: gzhlaker
     */
    public Node(String[] genomes){
        this.parents = new LinkedList<>();
        this.children = new LinkedList<>();
        this.genomes = genomes;
        this.name = "root";
        this.analyzeGenomes();
        this.genome = new Genome(this.getChromosomes());
        this.options = new LinkedList<>();
    }
    /**
     * @description: 获取节点的名字
     * @return: String
     * @author: gzhlaker
     */
    public String getName() {
        return name;
    }

    /**
     * @description: 设置节点的名字
     * @return: void
     * @author: gzhlaker
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @description: 获取节点的权值
     * @return: double
     * @author: gzhlaker
     */
    public double getWeight() {
        //Util.sum();
        //System.out.println(weight+"$");
        try {
            //System.out.println(Util.sum());
            return weight/Util.sum();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @description: 设置节点的权值
     * @return: void
     * @author: gzhlaker
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @description: 获取节点的所有祖先
     * @return: LinkedList<Node>
     * @author: gzhlaker
     */
    public LinkedList<Node> getParents() {
        return parents;
    }

    /**
     * @description: 设置节点的所有祖先
     * @return: void
     * @author: gzhlaker
     */
    public void setParents(LinkedList<Node> parents) {
        this.parents = parents;
    }

    /**
     * @description: 获取节点的所有孩子
     * @return: LinkedList<Node>
     * @author: gzhlaker
     */
    public LinkedList<Node> getChildren() {
        return children;
    }

    /**
     * @description: 设置节点的所有孩子
     * @return: void
     * @author: gzhlaker
     */
    public void setChildren(LinkedList<Node> children) {
        this.children = children;
    }

    /**
     * @description: 获取所有的未处理的基因组的数据
     * @return: String[]
     * @author: gzhlaker
     */
    public String[] getGenomes() {
        return genomes;
    }

    /**
     * @description: 设置所有的未处理的基因组数据
     * @return: void
     * @author: gzhlaker
     */
    public void setGenomes(String[] genomes) {
        this.genomes = genomes;
    }

    /**
     * @description: 获取所有的染色体数据
     * @return: String[]
     * @author: gzhlaker
     */
    public String[] getChromosomes() {
        return chromosomes;
    }

    public Genome getGenome() {
        return genome;
    }

    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    public boolean isLeaf() {
        return this.isLeaf;
    }

    public void setLeaf(boolean leaf) {
        this.isLeaf = leaf;
    }

    /**
     * @description: 设置所有的染色体数据
     * @return: void
     * @author: gzhlaker
     */

    public void setChromosomes(String[] chromosomes) {
        this.chromosomes = chromosomes;
    }
    /**
     * @description: 分析基因组数据获得染色体数据
     * @return: void
     * @author: gzhlaker
     */
    public void analyzeGenomes() {
        this.chromosomes = new String[this.genomes.length];
        for (int i = 0; i < genomes.length; i++) {
            this.chromosomes[i] = this.genomes[i].substring(0, this.genomes[i].length() - 2);
        }
    }
    public int getOptionsNumebr() {
        //System.out.println(optionsNumebr+"&");
        return optionsNumebr;

    }

    public void setOptionsNumebr(int optionsNumebr) {
        this.optionsNumebr = optionsNumebr;
    }

    public void setOptions(LinkedList<OptionType> options) {
        this.options = options;
    }

    public LinkedList<OptionType> getOptions() {
        return options;
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public void createOptions(double[] array){
        double[] optionNumberArray = new double[array.length];
        double[] currentOptionNumberArray = new double[array.length];
        Random random = new Random();
        int sumOptionNumber = 0;
        for (int i = 0; i < array.length; i++) {
            optionNumberArray[i] = this.getOptionsNumebr() * array[i];
            //System.out.println(this.getOptionsNumebr()+"&");
            sumOptionNumber += this.getOptionsNumebr() * array[i];
            //System.out.println(sumOptionNumber+"$");
            random.nextInt(array.length);
        }
        for (int i = 0; i < array.length; i++) {
            currentOptionNumberArray[i] = 0;
        }
        for (int i = 0; i < sumOptionNumber; i++) {
            //System.out.println(sumOptionNumber+"&");
            int j = random.nextInt(array.length);
            switch (j){
                case 0:{
                    if(currentOptionNumberArray[j] < optionNumberArray[j]){
                        this.options.add(OptionType.Reversal);
                        currentOptionNumberArray[j]++;
                    }
                    else{
                        i--;
                    }
                    break;
                }
                case 1:{
                    if(currentOptionNumberArray[j] < optionNumberArray[j]){
                        this.options.add(OptionType.Transposition);
                        currentOptionNumberArray[j]++;
                    }
                    else{
                        i--;
                    }
                    break;
                }
                case 2:{
                    if(currentOptionNumberArray[j] < optionNumberArray[j]){
                        this.options.add(OptionType.Aversion);
                        currentOptionNumberArray[j]++;
                    }
                    else{
                        i--;
                    }
                    break;
                }
                case 3:{
                    if(currentOptionNumberArray[j] < optionNumberArray[j]){
                        this.options.add(OptionType.Split);
                        this.options.add(OptionType.Combine);
                        currentOptionNumberArray[j]++;
                        //currentOptionNumberArray[j] = currentOptionNumberArray[j] + 2;
                    }
                    else{
                        i--;
                    }
                    break;
                }
//                case 4:{
//                    if(currentOptionNumberArray[j] < optionNumberArray[j]){
//                        //this.options.add(OptionType.Split);
//                        this.options.add(OptionType.Combine);
//                        currentOptionNumberArray[j]++;
//                        ///currentOptionNumberArray[j] = currentOptionNumberArray[j] + 2;
//                    }
//                    else{
//                        i--;
//                    }
//                    break;
//                }
            }
        }
    }
    public void createGenome(){
        Genome genome = this.father.genome;
        for (int i = 0; i < this.options.size(); i++) {
            switch (this.options.get(i)){
                case Reversal:{
                    genome = genome.reversal();
                    break;
                }
                case Transposition:{
                    genome = genome.transposition();
                    break;
                }
                case Aversion:{
                    genome = genome.Aversion();
                    break;
                }
                case Split:{
                    genome = genome.split();
                    break;
                }
                case Combine:{
                    genome = genome.combine();
                    break;
                }
            }
        }
        this.genome = genome;
    }
    public void printOptions(){
        System.out.print("(" + "Node:" + this.name + ", ");
        System.out.print(this.options);
        System.out.println(")");
    }
}
