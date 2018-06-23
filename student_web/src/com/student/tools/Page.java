package com.student.tools;

public class Page {
	private int totalRows;
	private int currentPage;
	private int maxPage;
	private int pageSize=10;
		
	public Page(){
		
	}

	
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

	 

		
}
