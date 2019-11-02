package astronomyDP19;

import java.util.ArrayList;

public class FindMaxEvents {
	
	public FindMaxEvents() {
		
	}
	
	public void printTable(int[][] table) {
		
	}
	
	public Integer dpSolution(ArrayList<Integer> eventCoordinates) {
		int n = eventCoordinates.size();
		int[][] table = new int[n][2*n - 1];
		
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
		
		table[n - 1][eventCoordinates.get(n - 1) + n - 1] = 1;
		
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
}
