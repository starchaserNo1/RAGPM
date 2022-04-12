## What is RAGPM?
RAGPM is a program for reconstructing Ancestral gene orders as CARs
(Contiguous Ancestral Regions). It is based on probability and adjacency pairs.
The method runs from a phylogeny tree (with branch lengths needed) and a file holding the genomes of all the leaf nodes on the tree. Now the RAGPM is in its version 1.0, developed using Java.
## How to use RAGPM.jar ?
###Input:
1. A phylogenetic tree structureed in Newick format.(eg: example\_for\_RAGPM/newick_tree)
2. A file holding the genomes of all the leaf nodes on the tree.(eg: example\_for\_RAGPM/leafGenomes\_for\_RAGPM)
3. the out directory
###Output:
After running, in the out directory:<br>
There will be a file named "process" holding the brief process of reconstructing all the ancestral(branch) nodes;<br>
for each ancestral(branch) node, there will be a file named "RAGPM\_Reconstructed\_nodeNum" holding the genome of the node;<br>
### Command:
java -jar RAGPM.jar PathOfTree PathOfLeafGenomes outDirectory<Br>
####Take "examples/example\_for\_RAGPM/" as an example:<br>
step 1: cd example\_for\_RAGPM/ <br>
step 2: java -jar RAGPM.jar newick_Tree leafGenomes\_for\_RAGPM ./<br> 
The "./" in step2 equals to "example\_for\_RAGPM/"<br>
Reading the files in "example\_for\_RAGPM/" will give you the details on how they were structured.<br><br>
If the required inputs are not at the same directory, please specify the absolute path for them.
## What is GSS ?
GSS is a program used to generate examples of simulated evolution process. It is developed using Java. Current version 1.0.
## How to use GSS.jar?
###Input:
1. A phylogenetic tree structureed in Newick format.(eg: example\_for\_GSS/tree.txt)
2. A file holds the genome of root-node.(eg: example\_for\_GSS/rootGenome.txt)
3. A file to specify the parameters involved.(eg: example\_for\_GSS/parameters.txt)
#### about parameters：
<b>first line:</b> the sum of events occurring on all branches on the tree.<br>
<b>second line:</b> the proportion of the four types of events occurring.(reversal,transposition, translocation, fission-and-fusion)
### Command:
java -jar GSS.jar PathOfTree PathOfParameters PathOfRootGenome outPath<Br>
####Take "examples/example\_for\_GSS/" as an example:<br>
step 1: cd example\_for\_GSS/<br>
step 2: java -jar GSS.jar tree.txt rootGenome.txt parameters.txt ./<br> 
The "./" in step2 equals to "example\_for\_GSS/"<br>
Reading the files in "example\_for\_GSS/" will give you the details on how they were structured.<br><br>
If the required inputs are not at the same directory, please specify the absolute path for them.
### Output:
After running:<br>
for each node, there will be two files associated with it:<br>
1. <b>nodeName\_chromosomes.txt</b>: holds the genome of the node;<br>
2. <b>nodeName\_options.txt</b>: holds the process of generating the node;<br>
## source code
Source code of RAGPM and GSS are shared respectively at:<br>
1. <b>RAGPM</b>: RAGPM\_source<br>
2. <b>GSS</b>:	GSS\_source<br>
The design-thinking of the GSS is showed in ideas_GSS.docx.
## Sharing data
1. <b>Simulation Samples</b>： data/All.zip<br>
2. <b>Real Sample</b>:  data/realSample.zip<br>
## Tip
If there is any update or upgrade, we will let you know here.
