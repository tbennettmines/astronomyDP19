package astronomyDP19;

import java.util.*;

public class FindMaxEvents {
	
	int[][] table;
	
	public FindMaxEvents() {}
	
	public void printTable(int[][] table) {
		for (int i = 0; i < table.length; i++) {
			System.out.println(Arrays.toString(table[i]));
		}
	}
	
	public Integer dpSolution(List<Integer> eventCoordinates) {
		int n = eventCoordinates.size();
		if (table == null)
			table = new int[n][2*n - 1];
		
		// Populate table with 0s to indicate valid positions j at time i
		// and -1's to indicate invalid positions
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < 2*n - 1; j++) {
				if(j >= n - 1 - i && j <= n - 1 + i) {
					table[i][j] = 0;
				}
				else {
					table[i][j] = -1;
				}
			}
		}
		
		// Set position d_n in the last row to 1
		table[n - 1][eventCoordinates.get(n - 1) + n - 1] = 1;
		
		// Iterate through and populate each element according to the maximum
		// of the 3 immediate elements below the current row at the position
		// and increment by 1 if the position is at d_i
		for (int i = n - 2; i >= 0;i--) {
			for (int j = n - 1 - i; j <= n - 1 + i; j++) {
				
				table[i][j] = Math.max(table[i+1][j-1], Math.max(table[i+1][j], table[i+1][j+1]) );
				
				if (j == eventCoordinates.get(i) + n - 1 && table[i][j] > 0) {
					table[i][j] = table[i][j] + 1;
				}
			}
		}
		return table[0][n-1];
	}
	
	public List<Integer> solutionCoords() {
		List<Integer> coords = new ArrayList<>();
		
		// table.length = 2n - 1 --> table[0][ptr] yields starting point, time t = 0
		int ptr = ((table[0].length + 1) / 2) - 1;
		// Telescope starts at pos 0 (i.e. ptr)
		int time = 0;
		// First, we must check below and to the left. If 0, then we will be moving the telescope to the right. Else, left.
		boolean moveRight = (table[1][ptr - 1] == 0);
		
		// For time from 1 to n (going down the rows of the table)
		// If the below or diagonal entry changes, then we can view the event at T, with coordinate T+1
		// Zero-check is for when there's no longer an event in the same location
		// --> O(n)
		for (int i = 1; i < table.length; i++) {
			if (moveRight) {
				if (table[i][ptr] < table[i - 1][ptr] && table[i][ptr] != 0) {
					coords.add(i);
					continue;
				}
				else if (table[i][ptr + 1] < table[i - 1][ptr]) {
					coords.add(i);
				}
				ptr++;
			}
			else {
				if (table[i][ptr] < table[i - 1][ptr] && table[i][ptr] != 0) {
					coords.add(i);
					continue;
				}
				else if (table[i][ptr - 1] < table[i - 1][ptr]) {
					coords.add(i);
				}
				ptr--;
			}
		}
		coords.add(table.length);
		
		return coords;
	}
	
	public List<Integer> findMovement(List<Integer> coords, List<Integer> eventCoordinates){
		List<Integer> movement = new ArrayList<Integer>();
		for(int i = 0; i<coords.size()-1; i++) {
			int time = coords.get(i+1)-coords.get(i);
			int change = eventCoordinates.get(coords.get(i+1)-1)-eventCoordinates.get(coords.get(i)-1);
			for (int j = 0; j<time; j++) {
				if(change > 0) {
					movement.add(1);
					change = change - 1;
				}
				else if(change < 0) {
					movement.add(-1);
					change = change + 1;
				}
				else {
					movement.add(0);
				}
			}
		}
		return movement;
	}
}
