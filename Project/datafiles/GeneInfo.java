//---------------------------------------------------------------------------------
// This class stores the pertinent information from the file
// The variable gene_name stores the locus name
// The variable start stores the start location of the gene
// The variable end stores the log2fluor        
//---------------------------------------------------------------------------------

class GeneInfo
{
	public int start;
	public int end;
	public float expression;
	public String gene_name;
	public int size;

   public GeneInfo(String gene_name, int start, int end, float expression) {
       this.gene_name = gene_name;
       this.start = start;
       this.end = end;
       this.expression = expression;
   }

	public int getStart() {
	    return start;
	}

	public void setStart(int start) {
	    this.start = start; 
	    size++;
	}

	public int getEnd() {
	    return end;
	}

	public void setEnd(int end) {
	    this.end = end; 
	}

	public float getExpression() {
	    return expression;
	}

	public void setExpression(float expression) {
	    this.expression = expression;
	}

	public String getGeneName() {
	    return gene_name;
	}

	public void setGeneName(String gene_name) {
	    this.gene_name = gene_name;
	}

}