package tools;

public class Pagination {
	private int totalRows;
	private int currentPage;
	private int maxPage;
	private int pageSize=10;
	private int previousPage;
	private int next;
	private int start;
	private int end;
	private int pageRange=5;
	
	public Pagination(){}
	
	public void calculate_maxPage(){
		if ((this.totalRows > 0) && (this.pageSize>0)){
			this.maxPage = Math.floorDiv(this.totalRows,this.pageSize ); 
			if ((this.totalRows % this.pageSize) > 0) {
				this.maxPage++;
			}	
		}else{
			this.maxPage = 0;
		}
	}


	public int getTotalRows() {
		return totalRows;
	}


	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getMaxPage() {
		return maxPage;
	}


	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getPreviousPage() {
		return previousPage;
	}


	public void setPreviousPage() {
		if (this.currentPage>1) this.previousPage =this.currentPage-1;
		else this.previousPage = 1;
	}

	public int getNext() {
		return next;
	}

	public void setNext() {
		if (this.currentPage<this.maxPage) this.next=this.currentPage+1;
		else this.next=this.maxPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart() {
		if ((this.currentPage-this.pageRange)>1) this.start=this.currentPage-this.pageRange;
		else this.start=1;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd() {
		if ((this.currentPage+this.pageRange)<this.maxPage) this.end=this.currentPage+this.pageRange;
		else this.end=this.maxPage;
				
	}

	public int getPageRange() {
		return pageRange;
	}

	public void setPageRange(int pageRange) {
		this.pageRange = pageRange;
	}

	 

		
}
