/*
 * @Descripttion: 
 * @version: 
 * @Author: liu
 * @Date: 2021-10-25 20:15:55
 * @LastEditors: Andy
 * @LastEditTime: 2021-10-27 22:12:20
 */
package com.tree;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Random;
public class Genome {
    /**
     * 基因组数据
     */
    private String[][] chromosome;
    /**
     * @description: 构造函数，接受一个原始的数据
     * @return:
     * @author: liu
     */
    public Genome(String[] chromosome){
        this.chromosome = new String[chromosome.length][];
        for (int i = 0; i < chromosome.length; i++) {
            this.chromosome[i] = chromosome[i].split(" ");//将字符串以空格为标志转化为字符串数组
        }
    }
    /**
     * @description: 构造函数，接受处理过的数据
     * @return:
     * @author: liu
     */
    public Genome(String[][] chromosome){
        this.chromosome = chromosome;
    }
    /**
     * @description: 返回染色体的条数
     * @return:
     * @author: liu
     */
     public int getChromosomeLength()
     {
         return this.chromosome.length;
     }
    /**
     * @description: 反转操作
     * @return: com.tree.Genome
     * @author: liu
     */
    public Genome reversal()
    {
        System.out.print("开始进行反转操作：");

        //判断在哪一条染色体上发生反转事件
        Random random = new Random();
        int a = 0;
        a= random.nextInt(getChromosomeLength());//产生随机数测试正常
        //随机产生进行反转操作的两个位置,并使得1始终处于小的位置
        int position1 = random.nextInt(this.chromosome[a].length);
        int position2 = random.nextInt(this.chromosome[a].length);
        if(position2 < position1)
        {
            int temp = position2;
            position2 = position1;
            position1 = temp;
        }
        //中间的片段、前面的片段、后面的片段
        String [] initialSection = Arrays.copyOfRange(this.chromosome[a],position1, position2);
        String[] frontSection = Arrays.copyOfRange(this.chromosome[a],0,position1);
        String[] behindSection = Arrays.copyOfRange(this.chromosome[a],position2,this.chromosome[a].length);

        //将切片进行反转处理
        String [] finalSection = new String[initialSection.length];
        for (int i = 0; i <initialSection.length ; i++) {
            finalSection[i]  = (initialSection[initialSection.length-i-1]);//这里只是将字符串反转，但是并未取负
        }
        //将切片进行取负处理
        Util.Negate(finalSection);

        //切片反转后测试，正确
        System.out.println("选择的染色体是第"+a+"条");
        System.out.println("切片取反以后的样子：");
        System.out.println(Arrays.deepToString(finalSection));

        //marry_01为前两个片段组合到一起，marry_02是marry_01与最后一个片段的组合，最终得到了反转之后的一条染色体
        String[] marry_01 = new String[this.chromosome[a].length];
        String[] marry_02 = new String[this.chromosome[a].length];
        if(frontSection.length == 0){
            if(behindSection.length > 0 && finalSection.length > 0){
                marry_02 = Util.margeArray(finalSection, behindSection);
            }
            else if(behindSection.length > 0 && finalSection.length == 0){
                marry_02 = behindSection;
            }
            else if(behindSection.length == 0 && finalSection.length > 0){
                marry_02 = finalSection;
            }
        }
        else if(frontSection.length > 0){
            if(finalSection.length == 0 && behindSection.length == 0){
                marry_02 = frontSection;
            }
            else if(finalSection.length > 0 && behindSection.length == 0){
                marry_02 = Util.margeArray(frontSection, finalSection);
            }
            else if(finalSection.length == 0 && behindSection.length > 0){
                marry_02 = Util.margeArray(frontSection, behindSection);
            }
            else if(finalSection.length > 0 && behindSection.length > 0){
                marry_01 = Util.margeArray(frontSection, finalSection);
                marry_02 = Util.margeArray(marry_01, behindSection);
            }
        }

        //存储最终的基因组，由于反转只发生在一条染色体上，所以另外所有的没有变化
        String[][] result = new String[getChromosomeLength()][];
        for(int i =0 ; i<this.chromosome.length ; i++)
        {
            result[i] = this.chromosome[i];
        }
        result[a] = marry_02;

        for(int i=0; i<this.chromosome.length; i++ )
        {
            Util.printArray(result[i]);
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
        return new Genome(result);
    }

    /**
     * @description: 转位操作
     * @return: com.tree.Genome
     * @author: liu
     */
    public Genome transposition()
    {

        System.out.print("开始进行转位操作：");

        Random random = new Random();
        int a = random.nextInt(getChromosomeLength());//用于随机在任意一条染色体发生事件
        System.out.println("选择的染色体是第"+a+"条");

        //随机产生i，j用于切分出来一段字符串,并使得i始终小于j。
        int i;int j;
        //避免i与j产生的值是一样的（其实应该差距为2，不然无法产生中间的k值）
        do {
             i = random.nextInt(this.chromosome[a].length);
             j = random.nextInt(this.chromosome[a].length);
        }while(Math.abs(i-j)<=1 || i == 0 || i==this.chromosome[a].length|| j == this.chromosome[a].length||j==0);
        if(i > j)
        {
            int temp = i;
            i= j;
            j = temp;
        }
        //接着在i和j之间产生一个随机数
        int k;
        do {
            //System.out.println("在这里我卡住了");
             k = random.nextInt(j - i) + i;
        }while(i == k || j == k);

        //打印选择的三个位置
        System.out.print("选择的三个位置分别是：");
        System.out.print(i+"* ");
        System.out.print(k+"* ");
        System.out.println(j+"*");


        String [] part1 = Arrays.copyOf(this.chromosome[a],i);
        String [] part2 = Arrays.copyOfRange(this.chromosome[a],i,k);
        String [] part3 = Arrays.copyOfRange(this.chromosome[a],k,j);
        String [] part4 = Arrays.copyOfRange(this.chromosome[a],j,this.chromosome[a].length);

        String [] result1 = Util.margeArray(part1,part3);
        String [] result2 = Util.margeArray(part2,part4);
        String [] result3 = Util.margeArray(result1,result2);

        //存储最终的基因组，由于转位只发生在一条染色体上，所以另外所有的没有变化
        String[][] result = new String[getChromosomeLength()][];
        for(int z =0 ; z<this.chromosome.length ; z++)
        {
            result[z] = this.chromosome[z];
        }
        result[a] = result3;

        //控制台观察测试
        for(int z=0; z<this.chromosome.length; z++ )
        {
            Util.printArray(result[z]);
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
        return  new Genome(result);
    }
    /**
     * @description: 移位操作
     * @return: com.tree.Genome
     * @author: liu
     */
    public Genome Aversion(){

        System.out.print("开始进行移位操作：");

        Random random = new Random();

        //在众多染色体中选择两条染色体进行移位操作，移位操作是在两条染色体上进行的。
        int strip_01 = random.nextInt(getChromosomeLength());
        //两条染色体不能是同一条
        int strip_02 = strip_01;
        do{strip_02 = random.nextInt(getChromosomeLength());}while(strip_02==strip_01);

        System.out.println("选择的是第"+strip_01+"和第"+strip_02+"条染色体");

        //分别找到长的与短的染色体，根据长的染色体来找位置，然后限制位置的产生，不符合限制就重新产生。
        String  [] min;
        String  [] max;
        if(this.chromosome[strip_01].length>this.chromosome[strip_02].length)
        {
            min = this.chromosome[strip_02];
            max = this.chromosome[strip_01];
        }
        else
        {
            min = this.chromosome[strip_01];
            max = this.chromosome[strip_02];
        }

        //测试
        //System.out.println(min.length+"*");

        //需要分别在两条染色体生产生一个随机的断开位置,分别为postion_01与position_02，此位置符合高斯分布。
        int position_01 = 1;int position_02 = 1;
        do {
            double i = random.nextGaussian() * Math.sqrt(max.length/2) + (max.length / 2);
            position_01 = (int) i;
        }while(position_01 <= 0 || position_01 >= min.length);
        do {
            double i = random.nextGaussian() * Math.sqrt(max.length/2) + (max.length / 2);
            position_02 = (int) i;
        }while(position_02 <= 0 || position_02 >= min.length ||position_01 == position_02);


        System.out.println("断开的位置分别为："+position_01+"、"+position_02);

        //染色体字符串的断开与重组片段
        String[] part1 = Arrays.copyOf(this.chromosome[strip_01], position_01);
        String[] part2 = Arrays.copyOfRange(this.chromosome[strip_01], position_01, this.chromosome[strip_01].length);
        String[] part3 = Arrays.copyOf(this.chromosome[strip_02], position_02);
        String[] part4 = Arrays.copyOfRange(this.chromosome[strip_02], position_02, this.chromosome[strip_02].length);

        String[] result1 = Util.margeArray(part1, part4);//X1Y2的组合形式
        String[] result2 = Util.margeArray(part3, part2);//Y1X2的组合形式
        String[] result3 = Util.margeArray(part1, part3);//X1Y1的组合形式
        String[] result4 = Util.margeArray(part2, part4);//X2Y2的组合形式
        String[] result5 = Util.margeArray(part1, Util.Negate(part3));//X1-Y1的组合形式
        String[] result6 = Util.margeArray(Util.Negate(part2), part4);//-X2Y2的组合形式

        //充当发生哪一种移位操作a=0，选择前前移位；否则选择前后移位。
        int a = 0;//
        //根据两个位点之间的差值，设置选择两种移位的概率
        int b = Math.abs(position_01-position_02);
        double c  = 1/b;//选择前后移位的概率
        a = Util.falseRandomTwo(c);

        String[][] result = new String[getChromosomeLength()][];

        //前前移位
        if(a==0) {
            System.out.println("我选择了前前移位");
            for(int i = 0; i<getChromosomeLength(); i++)
            {
                result[i] = this.chromosome[i];
            }
            result[strip_01] = result1;
            result[strip_02] = result2;
        }
        //前后移位
        else {
            System.out.println("我选择了前后移位");
            for(int i = 0; i<getChromosomeLength(); i++)
            {
                result[i] = this.chromosome[i];
            }
            result[strip_01] = result5;
            result[strip_02] = result6;
        }

        //打印操作完成以后的染色体
        for(int z=0; z<this.chromosome.length; z++ )
        {
            Util.printArray(result[z]);
        }
        System.out.println("------------------------------------------------------------------------------------------------------");

        return new Genome(result);
    }

    /**
     * @description: 分裂操作
     * @return: com.tree.Genome
     * @author: liu
     */
    public Genome split(){
        System.out.print("开始进行分裂操作：");
        Random random = new Random();

        //选择不至于太短的染色体进行分裂，避免出现极端的情况
        int a = random.nextInt(getChromosomeLength());
        do{
            a = random.nextInt(getChromosomeLength());
        }while(this.chromosome[a].length <= 3);

        //用正态分布以染色体一半的长度为基准点，求分裂的位置
        int position1 = random.nextInt(this.chromosome[a].length);
        do {
            double i = random.nextGaussian() * Math.sqrt(this.chromosome[a].length/2) + (this.chromosome[a].length / 2);
            position1 = (int) i;
        }while(position1 <= 0 || position1 >= this.chromosome[a].length);
        System.out.print("选择了第"+a+"条染色体，");
        System.out.println("并是从第"+position1+"个位置断开的！");

        String[] part_01 = Arrays.copyOf(this.chromosome[a],position1);
        String[] part_02 = Arrays.copyOfRange(this.chromosome[a],position1,this.chromosome[a].length);
        String[][] result = new String[this.chromosome.length+1][];

        for (int i = 0; i < this.chromosome.length; i++) {
            result[i] = this.chromosome[i];//是要比result中少一个元素的
        }
        result[a] = part_01;//被分裂的字符串的位置存储第一条分裂后的染色体
        result[this.chromosome.length] = part_02;//最后一个位置存储分裂后的第二条染色体

        for(int i =0 ; i<=this.chromosome.length ; i++)
        {
            Util.printArray(result[i]);
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
        return new Genome(result);
    }
    /**
     * @description: 合并操作
     * @return: com.tree.Genome
     * @author: liu
     */
    public Genome combine(){

        System.out.println("开始进行合并操作");
        Random random = new Random();

        int x = Util.randomTwo(); //选择怎么合并，有一种是X1X2，有另外一种是X1-X2

        int pingJun = 0;//求染色体的平均长度
        int sum = 0;
        for(int i=0;i<this.chromosome.length;i++)
        {
            sum += this.chromosome[i].length;
        }
        pingJun = sum / this.chromosome.length;

        int bianLiang = this.chromosome.length*8;//用此变量来控制选择机制随染色体条数变化
        int bianliang_02  =  pingJun/4*3;
        System.out.println(bianliang_02+"*");

        //如果只有两条染色体就不能进行合并了，因为其他的操作需要两条以上染色体才行
        if(this.chromosome.length > 2) {
            //选择较短染色体的可能性较大-产生a
            int count = 0;//count进行计数，达到一定要求则不得不选一个较长的染色体，所以要求越高，选择较长染色体的概率也就越小
            int a = random.nextInt(getChromosomeLength());
            do{
                a = random.nextInt(getChromosomeLength());
                count ++;
            }while(this.chromosome[a].length > bianliang_02 && count <= bianLiang);

            //选择较短的染色体可能性较大-产生b
            int count2 = 0;
            int b  = random.nextInt(getChromosomeLength());
            do{
                b = random.nextInt(getChromosomeLength());
                count2 ++;
            }while((this.chromosome[b].length > bianliang_02 && count2 <=bianLiang) ||(b==a));


            //a与b是不同的两个数，始终让a小于b
            int temp;
            if (a > b) {temp = a;a = b;b = temp;}

            //字符串的取出与合并后的字符串
            String [] part1 = this.chromosome[a];
            String [] part2 = this.chromosome[b];
            String [] result1 = Util.margeArray(part1,part2);//以X1X2的方式进行合并
            String [] result2 = Util.margeArray(part1,Util.Negate(part2));//以X1-X2的方式进行合并
            //Util.printArray(result1);
            //Util.printArray(result2);
            String[][] result = new String[this.chromosome.length][];//大小还是合并以前的大小

            //进行合并
            for (int i = 0; i < this.chromosome.length; i++)//整体赋值
                {
                    result[i] = this.chromosome[i];
                }
                //分两种情况进行合并
                if(x==0) {
                    result[a] = result1;}
                else{result[a] = result2;}

                //如果b就是最后一条染色体
                if (b == this.chromosome.length - 1) {
                    result[b] = null;
                }
                //否则
                else {
                    for (int i = b; i < this.chromosome.length - 1; i++) {
                        result[i] = result[i + 1];
                    }
                }

            //a始终小于b，去除a，b位置的染色体进行合并
            System.out.println("随机选的两条染色体分别是：" + "第" + a + "条" + "第" + b + "条");

            //我们只需要长度减一个，所以重新定义二维数组进行存储，去除最后一个元素
            String[][] result_end = new String[this.chromosome.length - 1][];//大小比以前要小一号
            for (int i = 0; i < this.chromosome.length - 1; i++) {
                result_end[i] = result[i];
                Util.printArray(result_end[i]);
            }
            System.out.println("------------------------------------------------------------------------------------------------------");
            return new Genome(result_end);

        }
        else//如果染色体的条数小于等于两条的话
        {
            String[][] result_end = new String[this.chromosome.length][];
            for(int i =0 ; i < this.chromosome.length ; i++)
            {
                result_end[i] = this.chromosome[i];
            }
            System.out.println("------------------------------------------------------------------------------------------------------");
            return new Genome(result_end);
        }
    }
    /**
     * @description: 获取染色体数据
     * @return: java.lang.String[][]
     * @author: liu
     */
    public String[][] getChromosome() {
        return chromosome;
    }
    /**
     * @description: 设置染色体数据
     * @return: void
     * @author: liu
     */
    public void setChromosome(String[][] chromosome) {
        this.chromosome = chromosome;
    }
}

