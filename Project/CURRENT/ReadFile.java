
import java.util.*;
import java.util.Scanner;
import java.io.*;

class ReadFile
{
	String fileName;
	int line_count; //lines in the file
	String name; //name of the gene
	String junk; //for unneeded columns
	int beginning;
	int ending;
	GeneInfo g = new GeneInfo(name, beginning, ending);

	ArrayList<GeneInfo> geneTable = new ArrayList<GeneInfo>();

	ReadFile()
	{	
		fileName = "watermelon.gff";
		line_count = 0;
		
	}

	//--------------------------------------------------------------
	// This function reads watermelon.gff file
	// And stores the relevant data in an array
	// And counts the number of lines in the file               
	//--------------------------------------------------------------
	public boolean Read_GFF()
	{
		//File reads in everything as a string. These are placeholders for integers.
		String b; //Temporary string for beginning of gene 
	    String e; //Temporary string for end of gene

		try 
		{
			//Hard coding important data for watermelon.faa SPECIFICALLY!
	         BufferedReader read_in = new BufferedReader(new FileReader(fileName));
	         String line;

	        //Loops to read in the file
	       while ((line = read_in.readLine()) != null)
	       {
	       		//Line scanner reads individual words
	       		Scanner s = new Scanner(line).useDelimiter("\t"); 

	       		//Hard coded for watermelon.gff file. Will need to be adapted in formatting is different.
	       		//Stores import data. 
	       		junk = s.next(); junk = s.next(); 
	       		if (junk.equals("begin"))
	       			continue; //if the first line of the file isn't in use
	       		else
	       		{
		       		junk = s.next(); 
		       		b = s.next();
		       		e = s.next();
		       		junk = s.next(); junk = s.next(); junk = s.next();
		       		name = s.next();


		       		//To add more information for unclear gene types
		       		if (name.equals("similar"))
		       		{
		       			//name += " " + s.next() + " " + s.next() + " " + s.next() + " " + s.next();
		       		}

		       		//Variables are read in as a string. 
		       		//This converts string to an int for computation. 
		       		int beginning = Integer.parseInt(b);
		       		int ending = Integer.parseInt(e);

		            //Add this line of data to array
		            geneTable.add(new GeneInfo(name,beginning,ending));

		            //Counts number of lines in the file
		            line_count++;
		        }
	        }

	        read_in.close();

	        return true;
		}

		catch(FileNotFoundException error)
		{
			System.out.println("Unable to open file " + fileName);
			return false;
		}

		catch(IOException error)
		{
			System.out.println("Error reading file " + fileName);
			return false;
		}

	}

	//--------------------------------------------------------------
	// This function prints the gene name, the gene start
	// location, and the gene end location to the terminal           
	//--------------------------------------------------------------
	public void print()
	{
		System.out.println("*************************************");
		System.out.println("\nGENE / START POSITION / END POSITION");
		System.out.println("*************************************");
		for( int i = 0; i < geneTable.size(); i++)
		{
			System.out.print(geneTable.get(i).gene_name);
			System.out.print(" ");
			System.out.print(geneTable.get(i).start);
			System.out.print(" ");
			System.out.println(geneTable.get(i).end);
		}	
	}

	//--------------------------------------------------------------
	// This function prints the size of the array                
	//--------------------------------------------------------------
	public int my_size()
	{
		return geneTable.get(geneTable.size() -1).end;
	}
}