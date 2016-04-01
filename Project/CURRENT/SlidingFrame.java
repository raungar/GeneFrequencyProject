import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/*		|||||||TO DO||||||

	- START AT ZERO
	- END AT MAX LENGTH
	- FAA FILES
	- USER INPUT FOR SLIDE SIZE AND FRAME_RIGHT

*/


class SlidingFrame
{
	int frame_left;
	int frame_right;
	int slide; //increment
	int n_runs; //how many times the frame has slided
	int gene_nums; //just needed for initializaiton of DataTable class
	int genes; //this is the actual variable tellign the number of genes

	int array_num = 0;

	int[] anArray;

	DataTable t = new DataTable(n_runs, gene_nums);

	ArrayList<DataTable> myTable = new ArrayList<DataTable>();


	SlidingFrame()
	{
		this.frame_left = 0;
		this.frame_right = 1000; //this number will determine the size of the frame
		this.slide = 250; //this number will designate how much to slide the frame
		this.array_num = 0;
		this.n_runs = 0;
	}

	//--------------------------------------------------------------
	// This function is responsible for sliding the reading frame 
	// After the current frame is tested for collision  
	// Length parameter ensures the frame does not extend             
	//--------------------------------------------------------------
	public void slide(int length)
	{
		while (frame_right < length)
		{
			collision();
			frame_left += slide;
			frame_right += slide;
			myTable.add(new DataTable(n_runs * slide,genes));
		}

		//Includes end of file
		if (frame_right > length)
		{
			frame_right = length;
			collision();
		}
	}

	//----------------------------------------------------------------
	// This is a "collision detection" function
	// It interprets if a gene has "collided" with the reading frame
	// If it has, it is recorded in the DataTable          
	//----------------------------------------------------------------
	public void collision()
	{

		genes = 0;
		ReadFile read_file = new ReadFile();
		read_file.Read_GFF();

		for(int j = 0; j < read_file.geneTable.size(); j++)
		{
			if (read_file.geneTable.get(j).start < frame_right && read_file.geneTable.get(j).end > frame_left)
			{
				genes++;
			}
		}

		
		n_runs++; //shows how many times the table has slided
	}

	//--------------------------------------------------------------
	// This function prints the x column output to file OutputX.txt 
	// And prints the y column output to OutputY.txt                
	//--------------------------------------------------------------
	public void print_array()
	{
		try
		{
			PrintStream out_x = new PrintStream(new FileOutputStream("OutputX.txt"));
			PrintStream out_y = new PrintStream(new FileOutputStream("OutputY.txt"));

			for (int i = 0; i < myTable.size(); i++)
				out_x.println(myTable.get(i).location);

			for (int i = 0; i < myTable.size(); i++)
					out_y.println(myTable.get(i).gene_num);
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
		ReadFile read_me = new ReadFile();
		SlidingFrame frame = new  SlidingFrame();

		if (read_me.Read_GFF() == true) //read the file
		{


			int choice = -1;
			Scanner reader = new Scanner(System.in); //read in user input
			
			// Gui g = new Gui();
			// g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// g.setSize(700,250);
			// g.setVisible(true);

			System.out.println("Welcome.");
			System.out.println("What do you want to do?");
			System.out.println("To print important data in your file, press 1.");
			System.out.println("To get a list of genes per frame, press 2.");
			System.out.println("To exit, press 3.");


			while (choice != 3)
			{
				System.out.println("Please enter a number: ");
				choice = reader.nextInt(); //read in user input for menu



				if (choice == 1)
				{
					read_me.print();
				}
				
				else if (choice == 2)
				{
					System.out.println("Your output is printed to two files");
					System.out.println("Your x axis is printed to OutputX.txt");
					System.out.println("Your y axis is printed to OutputY.txt");

					int length = read_me.my_size();
					frame.slide(length);
					frame.print_array();
				}

				else if (choice == 3)
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