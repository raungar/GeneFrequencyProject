//---------------------------------------------------------------------------------
// This class stores the pertinent information from the file
// The variable gene_name stores the given name of the gene
// The variable start stores the start location of the gene
// The variable end stores the end location of the gene          
//---------------------------------------------------------------------------------

class GeneInfo
{
	public int start;
	public int end;
	public String gene_name;

   public GeneInfo(String gene_name, int start, int end) {
       this.gene_name = gene_name;
       this.start = start;
       this.end = end;
   }

	public int getStart() {
	    return start;
	}

	public void setStart(int start) {
	    this.start = start; 
	}

	public int getEnd() {
	    return end;
	}

	public void setEnd(int end) {
	    this.end = end;
	}

	public String getGeneName() {
	    return gene_name;
	}

	public void setPersonName(String gene_name) {
	    this.gene_name = gene_name;
	}

}