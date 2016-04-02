//---------------------------------------------------------------------------------
// This class stores the end information relevant for a general table
// The x axis is stored in location, or the left reading frame
// The y axis is stored in gene_num, which is the number of genes at that location            
//---------------------------------------------------------------------------------

class DataTable
{
	public int y; //number of genes at location in array
	public int x;

   public DataTable(int x, int y) {
       this.y = y;
       this.x = x;
   }

	public int getY() {
	    return y;
	}

	public void setY(int y) {
	    this.y = y;
	}

	public int getX() {
	    return x;
	}

	public void setX(int x) {
	    this.x = x; 
	}

}