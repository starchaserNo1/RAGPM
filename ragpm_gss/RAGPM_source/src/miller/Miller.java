package miller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Miller
{
    public static void main(String[] args)
    {
        String tree_path = args[0];
        String leafGenomes_path = args[1];
        String outPath = args[2];


        String treeStr = FileUtils.readFile(tree_path).get(0);
        List<List<String>> leafGenomes = new ArrayList<List<String>>();
        getLeafGenomes(leafGenomes,leafGenomes_path);

        Tree.construct(treeStr, leafGenomes);
        Tree.showTheTree(outPath);


        for(int i=0; i<Tree.tree.size(); i++)
        {
            TreeNode tn = Tree.tree.get(i);
            if(!(tn instanceof LeafNode))
            {
                SolveBranchNode.construct(tn, outPath, i);
                SolveBranchNode.solve1();
                SolveBranchNode.back();
            }
        }
        Tree.back();
    }

    public static void getLeafGenomes(List<List<String>> leafGenomes, String leafGenomesPath)
    {
        FileReader fileReader;
        BufferedReader bufferedReader;
        String temp;
        try
        {
            fileReader = new FileReader(leafGenomesPath);
            bufferedReader = new BufferedReader(fileReader);
            while((temp=bufferedReader.readLine())!=null)
            {
                if(temp.length()>0 && !temp.equals("\r") && !temp.equals("\n"))
                {
                    if(temp.charAt(0)=='>')
                    {
                        List<String> current = new ArrayList<String>();
                        current.add(temp);
                        leafGenomes.add(current);
                    }
                    else
                    {
                        leafGenomes.get(leafGenomes.size()-1).add(temp);
                    }
                }
            }

            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
