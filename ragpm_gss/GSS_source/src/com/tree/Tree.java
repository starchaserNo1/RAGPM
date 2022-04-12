/*
 * @Descripttion: 
 * @version: 
 * @Author: Gzhlaker
 * @Date: 2021-10-25 17:10:56
 * @LastEditors: Andy
 * @LastEditTime: 2021-10-27 21:26:19
 */
package com.tree;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    /**
     * 树的所有操作数 = 所有节点的操作数之和
     */
    private double optionsNumber;
    /**
     * 树的根节点
     */
    private Node root;
    /**
     * 描述树的结构的字符串
     */
    private String structure;
    /**
     * 所有的 token
     */
    private String[] tokens;

    /**
     * @description: 树的构造函数，需要根节点的基因组数据和树的结构
     * @return:
     * @author: gzhlaker
     */
    public Tree(String[] genomes, String structure) {
        this.root = new Node(genomes);
        this.structure = structure;
        this.tokens = Util.getTokens(this.structure);
        this.analyzeStructure();
    }
    /**
     * @description: 树的构造函数，需要根节点的基因组数据和树的结构和用户输入的操作数
     * @return:
     * @author: gzhlaker
     */
    public Tree(String[] genomes, String structure, int optionsNumber) {
        this.root = new Node(genomes);
        this.structure = structure;
        this.tokens = Util.getTokens(this.structure);
        this.optionsNumber = optionsNumber;
        this.analyzeStructure();
    }
    /**
     * @description: 分析树的结构 
     * @return: void
     * @author: gzhlaker
     */
    public void analyzeStructure() {
        Node root = this.root;
        LinkedList<Node> ancestors = new LinkedList<>();
//        for(int i = 0;i<this.tokens.length;i++)
//        {
//            System.out.println(tokens[i]);
//        }
        for (int i = 0; i < this.tokens.length; i++) {
            switch (tokens[i]) {
            case "(": {
                Node child = new Node();
                root.getChildren().push(child);
                ancestors.push(root);
                root = child;
                break;
            }
            case ",": {
                Node child = new Node();
                ancestors.peek().getChildren().push(child);
                root = child;
                break;
            }
            case ")": {
                root = ancestors.pop();
            }
            case ":": {
                break;
            }
            case ";": {
                break;
            }
            default: {
                String x = this.tokens[i - 1];
                if (x.equals(")") || x.equals("(") || x.equals(",")) {
                    root.setName(tokens[i]);
                    root.setLeaf(true);
                } else if (x.equals(":")) {
                    root.setWeight(Double.parseDouble(tokens[i]));
                }
            }
            }
        }
    }
    
    /**
     * @description: 生成树中其他结构的基因组数据
     * @return: void
     * @author: gzhlaker
     */

    public void create(){}
    /**
     * @description: 获取根节点 
     * @return: com.tree.Node
     * @author: gzhlaker
     */

    public Node getRoot() {
        return root;
    }
    /**
     * @description: 设置根节点 
     * @return: void
     * @author: gzhlaker
     */

    public void setRoot(Node root) {
        this.root = root;
    }
    /**
     * @description: 获取 tokens 
     * @return: java.lang.String[]
     * @author: gzhlaker
     */

    public String[] getTokens() {
        return tokens;
    }
    /**
     * @description: 设置 tokens 
     * @return: void
     * @author: gzhlaker
     */

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }
    /**
     * @description: 获取结构字符串 
     * @return: java.lang.String
     * @author: gzhlaker
     */

    public String getStructure() {
        return structure;
    }
    /**
     * @description: 设置结构字符串
     * @return: void
     * @author: gzhlaker
     */

    public void setStructure(String structure) {
        this.structure = structure;
    }
    /**
     * @description: 层序遍历
     * @return: void
     * @author: gzhlaker
     */
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
           // System.out.println("(Weight:" + node.getWeight() + ", Name:" + node.getName() + ", Leaf:" + node.isLeaf() + ")");

            LinkedList<Node> children = node.getChildren();
            for (Node child : children) {
                queue.offer(child);
            }
        }
    }
    /**
     * @description: 使每个孩子节点知道自己的父亲是谁
     * @return: void
     * @author: gzhlaker
     */
    public void knowFather() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            LinkedList<Node> children = node.getChildren();
            for (Node child : children) {
                child.setFather(node);
                //System.out.println("(" + "Name:" + child.getName() + ", Father:" + node.getName() + ")");
                queue.offer(child);
            }
        }
    }
    /**
     * @description: 获取层序遍历的记录
     * @return: java.util.LinkedList<com.tree.Node>
     * @author: gzhlaker
     * @time: 2022/1/21
     */
    public LinkedList<Node> getLevelOrderList(){
        LinkedList<Node> leverOrder = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            LinkedList<Node> children = node.getChildren();
            if(children.size() > 0 && node.getName() != "root"){
                leverOrder.add(node);
            }
            for (int i = children.size() - 1; i >= 0; i--) {
                queue.offer(children.get(i));
            }
        }
        return leverOrder;
    }
    /**
     * @description: 设置中间节点的名称 
     * @return: void
     * @author: gzhlaker
     */
    public void setInternalName(Node node){
        LinkedList<Node> children = node.getChildren();
        String name = "(";
        for(int i = children.size() - 1; i >= 0; i--){
            if(i > 0){
                name += children.get(i).getName();
                name += ",";
            }
            else{
                name += children.get(i).getName();
            }
        }
        name += ")";
        node.setName(name);
    }

    /**
     * @description: 创建中间节点的名称
     * @return: void
     * @author: gzhlaker
     */
    public void createInternalName(){
        LinkedList<Node> levelOrderList = this.getLevelOrderList();
        for(int i = levelOrderList.size() - 1; i >= 0; i--){
            this.setInternalName(levelOrderList.get(i));
            System.out.println(levelOrderList.get(i).getName());
        }
    }
    /**
     * @description: 每个节点被分配到的操作数
     * @return: void
     * @author: gzhlaker
     */
    public void createOptionsNumber(){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            LinkedList<Node> children = node.getChildren();
            for (Node child : children) {
                //System.out.println(this.getOptionsNumber()+"&");//2000
                System.out.println(child.getWeight()+"&");
                double optionNumber = this.getOptionsNumber() * child.getWeight();

                double a = Math.round(optionNumber);
                System.out.println(a+"*");

                child.setOptionsNumebr((int)(a));
                System.out.println("(" + "Name:" + child.getName() + ", OptionsNumber:" + child.getOptionsNumebr() + ")");
                queue.offer(child);
            }
        }
    }
    /**
     * @description: 创建操作
     * @return: void
     * @author: gzhlaker
     */
    public void createOptions(double[] array){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            node.createOptions(array);
            node.printOptions();
            LinkedList<Node> children = node.getChildren();
            for (Node child : children) {
                queue.offer(child);
            }
        }
    }
    /**
     * @description: 创建基因组
     * @return: void
     * @author: gzhlak
     */
    public void createGenome(){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node.getFather() != null){
                node.createGenome();
            }
            LinkedList<Node> children = node.getChildren();
            for (Node child : children) {
                queue.offer(child);
            }
        }
    }
    public void printGenome(){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.println("******************");
            System.out.println(node.getName());
            for(int i = 0; i<node.getGenome().getChromosomeLength() ; i++)
            {
                Util.printArray(node.getGenome().getChromosome()[i]);//控制台也打印一遍，方便观察之用。
            }
            LinkedList<Node> children = node.getChildren();
            for (Node child : children) {
                queue.offer(child);
            }
        }
    }
    public void saveData() throws IOException {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            Dataset.saveFile(node.getName(), node.getGenome().getChromosome(), node.getOptions());
            LinkedList<Node> children = node.getChildren();
            for (Node child : children) {
                queue.offer(child);
            }
        }
    }
    public double getOptionsNumber() {
        return optionsNumber;
    }

    public void setOptionsNumber(int optionsNumber) {
        this.optionsNumber = optionsNumber;
    }
}
