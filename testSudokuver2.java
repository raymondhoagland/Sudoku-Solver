import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import java.util.ArrayList;

public class testSudokuver2{
	static int loc = 0;
	static List<Location> fills;
	static void printGrid(SudokuGrid grid){
		for(int i=0; i<grid.grid.length;i++){
			for(int k=0; k<grid.grid[i].length; k++){
				System.out.print(grid.grid[i][k]+" | ");
			}
			System.out.println();
			System.out.println("------------------------------------");
		}
	}
	static boolean solve(SudokuGrid grid){
		//SudokuGrid tmp = grid;
		int num = grid.numEmpty();
		fills = new ArrayList<Location>();
		for(int i=0; i<num;i++){
			Location c = grid.getBestPositon();
			fills.add(c);
			grid.add(c, 1);
		}
		for(int i=0; i<num;i++){
			grid.remove(fills.get(i));
		}
		return(solveX(grid));
	}
	static boolean solveX(SudokuGrid grid){
		if(grid.isFull()){
			printGrid(grid);
			return true;
		}
		else{
			Location new_Loc = fills.get(loc);
			loc++;
			for(int i=1; i<=9; i++){
				SudokuGrid tmp = grid.duplicate();
				tmp.add(new_Loc, i);
				if(tmp.isValidRow(new_Loc.getY())&&tmp.isValidColumn(new_Loc.getX())&&tmp.isValidMini(new_Loc.getY(), new_Loc.getX())){
					if(solveX(tmp))
						return true;
				}
			}
			grid.remove(new_Loc);
			loc--;
			return false;
		}
	}
	public static void main(String[] args){
		int[][] nums = new int[9][9];
		File file = new File("puzzle.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			for(int i=0; i<9; i++){
				for(int k=0; k<9; k++){
					nums[i][k]  = scanner.nextInt();
				}
			}
			double time = System.currentTimeMillis();
			solve(new SudokuGrid(nums));
			System.out.println(System.currentTimeMillis()-time);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}