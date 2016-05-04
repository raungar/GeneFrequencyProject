
import java.util.*;
import java.util.Scanner;
import java.io.*;

class ReadFile
{
	String fileName;
	int line_count; //lines in the file
	String locus; //name of the gene
	String junk; //for unneeded columns
	int beginning;
	int ending;
	int max = 0;
	float log2fluor;
	GeneInfo g = new GeneInfo(locus, beginning, ending, log2fluor);
	int my_size = 0;

	ArrayList<GeneInfo> geneTable = new ArrayList<GeneInfo>();

	ReadFile()
	{	
		fileName = "sex_chrom.txt";
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
		String start; //Temporary string for beginning of gene 
	    String fluor; //Temporary string for expression

		try 
		{	         

		    BufferedReader read_in = new BufferedReader(new FileReader(fileName));
		    String extension = fileName.substring(fileName.length() -3);
			//if( extension == ".txt")
			{
				//return false;
			}
	         String line;
	         max = beginning;
	        //Loops to read in the file
	       while ((line = read_in.readLine()) != null)
	       {
	       		//Line scanner reads individual words
	       		Scanner s = new Scanner(line).useDelimiter("\t"); 

	       		//Hard coded for watermelon.gff file. Will need to be adapted in formatting is different.
	       		//Stores import data. 
	       		
	       		if (line_count == 0)
	       			System.out.print("");//continue; //skips header
	       		else
	       		{
	       			locus = s.next(); 
	       			junk = s.next(); junk = s.next(); 
		       		fluor = s.next();
		       		junk = s.next(); junk = s.next();
		       		start = s.next(); 


		       		//Variables are read in as a string. 
		       		//This converts string to an int for computation. 
		       		int beginning = Integer.parseInt(start);
		       		float ending = Float.parseFloat(fluor);

		       		//System.out.println(locus + " " + beginning + " " + log2fluor);

		       		if (beginning > max)
		       			max = beginning;


		            //Add this line of data to array
		            geneTable.add(new GeneInfo(locus,beginning, beginning +1, log2fluor));
		           // my_size++;
		        }

		            //Counts number of lines in the file
		            line_count++;
	        }
	        //System.out.println("MAX: " + max);
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
	// This function reads watermelon.gff file
	// And stores the relevant data in an array
	// And counts the number of lines in the file               
	//--------------------------------------------------------------
	public boolean ReadTxt()
	{
		//File reads in everything as a string. These are placeholders for integers.
		String start; //Temporary string for beginning of gene 
	    String end; //Temporary string for end of gene

		try 
		{
			//Hard coding important data for watermelon.faa SPECIFICALLY!
	         BufferedReader read_in = new BufferedReader(new FileReader(fileName));
	         String line;
	         max = beginning;
	        //Loops to read in the file
	       while ((line = read_in.readLine()) != null)
	       {
	       		//Line scanner reads individual words
	       		Scanner s = new Scanner(line).useDelimiter("\t"); 

	       		//Hard coded for watermelon.gff file. Will need to be adapted in formatting is different.
	       		//Stores import data. 
	       		
	       		if (line_count == 0)
	       			System.out.print("");//continue; //skips header
	       		else
	       		{
	       			
		       		locus = s.next(); 
		       		start = s.next();
		       		end = s.next(); 
		       		//start = s.next();

		       		//Variables are read in as a string. 
		       		//This converts string to an int for computation. 
		       		int beginning = Integer.parseInt(start);
		       		int ending = Integer.parseInt(end);

		       		//System.out.println(locus + " " + beginning + " " + log2fluor);

		       		if (beginning > max)
		       			max = beginning;


		            //Add this line of data to array
		            geneTable.add(new GeneInfo(locus,beginning, ending,0));
		           // my_size++;
		        }

		            //Counts number of lines in the file
		            line_count++;
	        }
	        //System.out.println("MAX: " + max);
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
			System.out.println(geneTable.get(i).expression);
		}	
	}

	//--------------------------------------------------------------
	// This function prints the size of the array                
	//--------------------------------------------------------------
	public int my_size()
	{
		//System.out.println("SIZE: " + geneTable.get(geneTable.size()).size);
		// return geneTable.get(geneTable.size()-1).start + 1;
		//return start+1;
		return max;
	}
}