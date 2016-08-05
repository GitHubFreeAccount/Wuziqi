package com.wzx.backgammon.kernel;

public class Action {
   
	int col;
	int row;
	
	public Action(int col,int row) {
		// TODO Auto-generated constructor stub
		  if ((col < 0) || (row < 0))
		      throw new IllegalArgumentException();
		    this.row = col;
		    this.col = row;
	}
	
	 public int getCol(){
	    return this.col;
	 }

	  public int getRow(){
	    return this.row;
	  }
	
}
