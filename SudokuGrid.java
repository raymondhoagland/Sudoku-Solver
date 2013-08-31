

//import java.util.ArrayList;
//import java.awt.Point;
//import java.util.ArrayList;
//import java.util.List;


class SudokuGrid{
	int[][] grid = new int[9][9];
	SudokuGrid(int[][] nums){
		grid = nums;
	}
	SudokuGrid duplicate(){
		return new SudokuGrid(grid);
	}
	boolean isValidColumn(int column){
		int[] tmp = new int[9];
		for(int i=0; i<grid.length;i++){
			if(grid[i][column]!=0)
				tmp[grid[i][column]-1]++;
		}
		for(int i=0; i<tmp.length;i++){
			if(tmp[i]>1)
				return false;
		}
		return true;
	}
	boolean isValidRow(int row){
		int[] tmp = new int[9];
		for(int i=0; i<grid.length;i++){
			//System.out.println(grid[i][row]);
			if(grid[row][i]!=0)
				tmp[grid[row][i]-1]++;
		}
		for(int i=0; i<tmp.length;i++){
			if(tmp[i]>1)
				return false;
		}
		return true;
	}
	boolean isValidMini(int row, int column){
		int[] tmp = new int[9];
		if(column<3){
			if(row<3){
				for(int i=0; i<3;i++){
					for(int k=0; k<3;k++){
						int c = grid[i][k]-1;
						if(c>-1)
							tmp[c]++;
					}
				}
			}
			else if(row<6){
				for(int i=3; i<6;i++){
					for(int k=0; k<3;k++){
						int c = grid[i][k]-1;
						if(c>-1)
							tmp[c]++;
					}
				}
			}
			else if(row<9){
				for(int i=6; i<9;i++){
					for(int k=0; k<3;k++){
						int c = grid[i][k]-1;
						if(c>-1)
							tmp[c]++;
					}
				}
			}
		}
		else if(column<6){
			if(row<3){
				for(int i=0; i<3;i++){
					for(int k=3; k<6;k++){
						int c = grid[i][k]-1;
						if(c>-1)
							tmp[c]++;
					}
				}
			}
			else if(row<6){
				for(int i=3; i<6;i++){
					for(int k=3; k<6;k++){
						int c = grid[i][k]-1;
						if(c>-1)
							tmp[c]++;
					}
				}
			}
			else if(row<9){
				for(int i=6; i<9;i++){
					for(int k=3; k<6;k++){
						int c = grid[i][k]-1;
						if(c>-1)
							tmp[c]++;
					}
				}
			}
		}
		else if(column<9){
			if(row<3){
				for(int i=0; i<3;i++){
					for(int k=6; k<9;k++){
						int c = grid[i][k]-1;
						if(c>-1)
							tmp[c]++;
					}
				}
			}
			else if(row<6){
				for(int i=3; i<6;i++){
					for(int k=6; k<9;k++){
						int c = grid[i][k]-1;
						if(c>-1)
							tmp[c]++;
					}
				}
			}
			else if(row<9){
				for(int i=6; i<9;i++){
					for(int k=6; k<9;k++){
						int c = grid[i][k]-1;
						if(c>-1)
							tmp[c]++;
					}
				}
			}
		}
		for(int i=0; i<tmp.length;i++){
			if(tmp[i]>1)
				return false;
		}
		return true;
	}
	void add(int row, int column, int value){
		grid[row][column] = value;
	}
	void add(Location x, int value){
		grid[x.getY()][x.getX()] = value;
	}
	void remove(int row, int column){
		grid[row][column] = 0;
	}
	void remove(Location x){
		grid[x.getY()][x.getX()] = 0;
	}
	Location getBestPositon(){
		Location bestLocation = null;
		int max = -1;
		for(int i=0; i<grid.length;i++){
			for(int k=0; k<grid[i].length;k++){
				if(grid[i][k]==0){
					Location trial = new Location(k,i); 
					int tmp = trial.numAdjacents(grid)*trial.numColumn(grid)*trial.numRow(grid);
					if(tmp>max){
						max = tmp;
						bestLocation = trial;
					}
				}
			}
		}
		//System.out.println(mostAdjacents);
		return bestLocation;
	}
	int numEmpty(){
		int count = 0;
		for(int i=0; i<grid.length; i++){
			for(int k=0; k<grid[i].length; k++){
				if(grid[i][k]==0)
					count++;
			}
		}
		return count;
	}
	boolean isFull(){
		for(int i=0; i<grid.length;i++){
			for(int k=0; k<grid[i].length; k++){
				if(grid[i][k]==0)
					return false;
			}
		}
		return true;
	}
}
class Location{
	int x,y;
	Location(){
		x = -1;
		y = -1;
	}
	Location(int x, int y){
		this.x = x;
		this.y = y;
	}
	int getX(){
		return this.x;
	}
	int getY(){
		return this.y;
	}
	double getDistance(Location two){
		double xDiff = Math.pow((this.x-two.x), 2);
		double yDiff = Math.pow((this.y-two.y), 2);
		return Math.sqrt(xDiff+yDiff);
	}
	int numColumn(int[][] grid){
		int count = 0;
		for(int i=0; i<grid.length; i++){
			if(grid[i][this.x]!=0)
				count++;
		}
		return count;
	}
	int numRow(int[][] grid){
		int count = 0;
		for(int i=0; i<grid[this.y].length; i++){
			if(grid[this.y][i]!=0)
				count++;
		}
		return count;
	}
	int numAdjacents(int[][] grid){
		int column = this.x;
		int row    = this.y;
		int count  = 0;
		if(column<3){
			if(row<3){
				for(int i=0; i<3;i++){
					for(int k=0; k<3;k++){
						int c = grid[i][k];
						if(c>0)
							count++;
					}
				}
			}
			else if(row<6){
				for(int i=3; i<6;i++){
					for(int k=0; k<3;k++){
						int c = grid[i][k];
						if(c>0)
							count++;
					}
				}
			}
			else if(row<9){
				for(int i=6; i<9;i++){
					for(int k=0; k<3;k++){
						int c = grid[i][k];
						if(c>0)
							count++;
					}
				}
			}
		}
		else if(column<6){
			if(row<3){
				for(int i=0; i<3;i++){
					for(int k=3; k<6;k++){
						int c = grid[i][k];
						if(c>0)
							count++;
					}
				}
			}
			else if(row<6){
				for(int i=3; i<6;i++){
					for(int k=3; k<6;k++){
						int c = grid[i][k];
						if(c>0)
							count++;
					}
				}
			}
			else if(row<9){
				for(int i=6; i<9;i++){
					for(int k=3; k<6;k++){
						int c = grid[i][k];
						if(c>0)
							count++;
					}
				}
			}
		}
		else if(column<9){
			if(row<3){
				for(int i=0; i<3;i++){
					for(int k=6; k<9;k++){
						int c = grid[i][k];
						if(c>0)
							count++;
					}
				}
			}
			else if(row<6){
				for(int i=3; i<6;i++){
					for(int k=6; k<9;k++){
						int c = grid[i][k];
						if(c>0)
							count++;;
					}
				}
			}
			else if(row<9){
				for(int i=6; i<9;i++){
					for(int k=6; k<9;k++){
						int c = grid[i][k];
						if(c>0)
							count++;
					}
				}
			}
		}
		return count;
	}
}