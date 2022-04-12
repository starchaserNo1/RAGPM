package com.tree;

import java.io.IOException;

public class Miller
{
    public static void main(String[] args) throws IOException
    {
        Dataset.structFile = args[0];
        Dataset.parameterFile = args[1];
        Dataset.genomeFile = args[2];
        Dataset.path = args[3];
        Main.main(args);
    }
}