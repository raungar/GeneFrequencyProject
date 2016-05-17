import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/*		|||||||TO DO||||||

	- END AT MAX LENGTH
	- FAA FILES
	- Detailed error handling for user input
*/


class SlidingFrame
{
	int frame_left;
	int frame_right;
	int slide; //increment
	int n_runs; //how many times the frame has slided
	int gene_nums; //just needed for initializaiton of DataTable class
	int genes; //this is the actual variable tellign the number of genes
	Random rand;
	Scanner reader;
	ReadFile read_file;

	int array_num = 0;

	int[] anArray;

	//DataTable t = new DataTable(n_runs, gene_nums);

	ArrayList<DataTable> slideTable = new ArrayList<DataTable>();
	ArrayList<DataTable> randomTable; 


	SlidingFrame()
	{
		this.frame_left = 0;
		this.frame_right = 1000; //this number will determine the size of the frame
		this.slide = 250; //this number will designate how much to slide the frame
		this.array_num = 0;
		this.n_runs = 0;
		this.rand = new Random();
		this.reader = new Scanner(System.in); //read in user input
		read_file = new ReadFile();

	}

	//--------------------------------------------------------------
	// This function is responsible for sliding the reading frame 
	// After the current frame is tested for collision  
	// Length parameter ensures the frame does not extend             
	//--------------------------------------------------------------
	public void slide(int length)
	{
		System.out.print("Please enter the size of the frame: ");
		frame_right = reader.nextInt();
		System.out.print("Please enter the amount to increment each time: ");
		slide = reader.nextInt();

		System.out.println("MY LENGTH: " + length);
		while (frame_right < length)
		{
			collision();
			frame_left += slide;
			frame_right += slide;
			slideTable.add(new DataTable(n_runs * slide,genes));
		}

		//Includes end of file
		if (frame_right > length)
		{
			frame_right = length;
			collision();
		}
	}

	//------------------------------------------------------------------
	// This function asks the user to input a number of random segments 
	// To generate and the size of each fragment. It will then loop
	// Through to determine how many times how many times (y value)
	// A certain number of genes in a segment (x value) is found.
	// This informatio is stored in the randomTable ArrayList.            
	//------------------------------------------------------------------
	public void random(int length)
	{
		randomTable = new ArrayList<DataTable>(); //new random table in case random(length) has already been called
		genes = 0; //set to zero to reset in case another function used
		

		//User input
		System.out.print("Please enter a number of random segments to generate: ");
		int num_segments = reader.nextInt();
		System.out.print("Please enter the size of these fragments: ");
		int frame_size = reader.nextInt();

		int random_location; 

		for (int i = 0; i < num_segments; i++)
		{
			random_location = rand.nextInt(length); //generate a random start location in the range of the chromosome
			frame_left = random_location;
			frame_right = random_location + frame_size;
			while (random_location + frame_size > length) //keeps frame within parameters
			{
				random_location = rand.nextInt(length); 
			}

			collision(); //updates genes at that location

			boolean already_exists = false; //if x value already exists

			if (genes == 19)
				System.out.println("Nineteen genes found at " + random_location);

			if (i == 0)
				randomTable.add(new DataTable(genes,1)); //for first run, add first data to table
			
			else
			{
				int location_already_exists = 0; //records location in for loop for use outside of for loop
				for (int f = 0; f < randomTable.size(); f++)
				{
					if (randomTable.get(f).x == genes) //if x value is already in table
					{
						already_exists = true;
						location_already_exists = f;
					}
				}

				if (already_exists == true) //if the x value is already in the table
				{
					int temp =randomTable.get(location_already_exists).y;
					randomTable.get(location_already_exists).setY(temp +1);	// then add one to the y value (times this x value has occurred)
				}

				else
				{
					randomTable.add(new DataTable(genes,1)); //add another field if x value isn't already in table
				}
				
			}

			//PRINT OUT WHAT'S HAPPENING EACH ITERATION FOR TESTING 
			//int t = i+1;
			//System.out.println("TABLE SIZE: " + randomTable.size() + " after " + t + " runs  (" + genes + " genes in this location)");

		}

	}

	//----------------------------------------------------------------
	// This is a "collision detection" function
	// It interprets if a gene has "collided" with the reading frame
	// If it has, it is recorded in the DataTable          
	//----------------------------------------------------------------
	public int collision()
	{

		genes = 0;
		//System.out.println("GENE TABLE SIZE: " + read_file.geneTable.size() + "\n");
		for (int j = 0; j < read_file.geneTable.size(); j++)
		{
			if (read_file.geneTable.get(j).start <= frame_right && read_file.geneTable.get(j).start >= frame_left)
			{
				genes++;
			}
		}
		
		n_runs++; //shows how many times the table has slided
		return genes;
	}

	//-------------------------------------------------------------------
	// This function prints the x column output to file OutputSlideX.txt 
	// And prints the y column output to OutputSlideY.txt 
	// For the information from the slideTable array list               
	//-------------------------------------------------------------------
	public void print_slide()
	{
		try
		{
			PrintStream out_x = new PrintStream(new FileOutputStream("OutputSlideX.txt"));
			PrintStream out_y = new PrintStream(new FileOutputStream("OutputSlideY.txt"));

			for (int i = 0; i < slideTable.size(); i++)
				out_x.println(slideTable.get(i).x);

			for (int i = 0; i < slideTable.size(); i++)
					out_y.println(slideTable.get(i).y);

		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error: File not found.");
		}
	}

	//--------------------------------------------------------------------
	// This function prints the x column output to file OutputRandomX.txt 
	// And prints the y column output to OutputRandomY.txt  
	// For the information from the randomTable array list                  
	//--------------------------------------------------------------------
	public void print_random()
	{
		try
		{
			PrintStream out_x = new PrintStream(new FileOutputStream("OutputRandomX.txt"));
			PrintStream out_y = new PrintStream(new FileOutputStream("OutputRandomY.txt"));

			//System.out.println("RANDOM SIZE: " + randomTable.size());

			for (int i = 0; i < randomTable.size(); i++)
				out_x.println(randomTable.get(i).x);

			for (int i = 0; i < randomTable.size(); i++)
					out_y.println(randomTable.get(i).y);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error: File not found.");
		}
	}


	//--------------------------------------------------------------
	// This main function is responsible for user directions
	// And general initialization of the program               
	//--------------------------------------------------------------
	public static void main(String[] args)
	{
		SlidingFrame frame = new  SlidingFrame();


		Scanner reader = new Scanner(System.in); //read in user input

		//ReadFile readme = new ReadFile();

		if (frame.read_file.ReadTxt() == true) //read the file
		{
			int length = frame.read_file.my_size();

			int choice = -1;

			System.out.println("\nWhat do you want to do?");
			System.out.println("To print important data in your file, press 1.");
			System.out.println("To get a list of genes per frame, press 2.");
			System.out.println("To get a random distribution press 3");
			System.out.println("To show the menu again, press 4");
			System.out.println("To exit, press 5.");


			while (choice != 5)
			{
				System.out.println("Please enter a number: ");
				choice = reader.nextInt(); //read in user input for menu



				if (choice == 1)
				{
					frame.read_file.print();
					System.out.println("\n\n {To see the menu again press four}");
				}
				
				else if (choice == 2)
				{
					
					frame.slide(length);
					frame.print_slide();

					System.out.println("Your output is printed to two files");
					System.out.println("Your x axis is printed to OutputSlideX.txt");
					System.out.println("Your y axis is printed to OutputSlideY.txt");
					System.out.println("\n\n{To see the menu again press four}");
				}

				else if (choice == 3)
				{
					frame.random(length); //call random with size of chromosome as a parameter
					frame.print_random();


					System.out.println("Your output is printed to two files");
					System.out.println("Your x axis is printed to OutputRandomX.txt");
					System.out.println("Your y axis is printed to OutputRandomY.txt");
					System.out.println("\n\n{To see the menu again press four}");
				}

				else if (choice == 4)
				{
					System.out.println("\nWhat do you want to do?");
					System.out.println("To print important data in your file, press 1.");
					System.out.println("To get a list of genes per frame, press 2.");
					System.out.println("To get a random distribution press 3");
					System.out.println("To show the menu again, press 4");
					System.out.println("To exit, press 5.");
				}

				else if (choice == 5)
				{
					break;
				}

				else
				{
					System.out.println("Invalid number. Try again.");

				}
			
			}

		}
		else
		{
			System.out.println("Error: File not found");
		}
	}
}