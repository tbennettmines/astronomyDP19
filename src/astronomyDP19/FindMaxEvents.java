package astronomyDP19;

import java.util.ArrayList;

public class FindMaxEvents {
	
	public FindMaxEvents() {
		
	}
	
	public Integer dpSolution(ArrayList<Integer> eventCoordinates) {
		int n = eventCoordinates.size();
		int[][] table = new int[2*n-1][n];
		for(int i = 0;i<2*n-1;i++) {
			for(int j = 0;j<n;j++) {
				if(i>n-j && i<n+j) {
					table[i][j] = 0;
				}
				else {
					table[i][j] = -1;
				}
			}
		}
		table[eventCoordinates.get(n-1)+n-1][n-1] = 1;
		for(int i = 0;i<2*n-1;i++) {
			for(int j = n-2;j>=0;j--) {
				int max = -1;
				for(int k = -1; k< 2; k++) {
					if(i+k>0 && i+k<2*n-1) {
						int temp = table[i+k][j+1];
						if(temp>max) {
							max = temp;
						}
					}
				}
				table[i][j] = max;
				if(i == eventCoordinates.get(j)+n-1) {
					table[i][j] = table[i][j] + 1;
				}
			}
		}
		return table[n-1][0];
	}
}
