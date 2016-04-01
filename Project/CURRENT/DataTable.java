//---------------------------------------------------------------------------------
// This class stores the end information relevant for a general table
// The x axis is stored in location, or the left reading frame
// The y axis is stored in gene_num, which is the number of genes at that location            
//---------------------------------------------------------------------------------

class DataTable
{
	public int gene_num; //number of genes at location in array
	public int location;

   public DataTable(int location, int gene_num) {
       this.gene_num = gene_num;
       this.location = location;
   }

	public int getGeneNum() {
	    return gene_num;
	}

	public void setGeneNum(int gene_num) {
	    this.gene_num = gene_num;
	}

	public int getLocation() {
	    return location;
	}

	public void setLocation(int location) {
	    this.location = location; 
	}

}