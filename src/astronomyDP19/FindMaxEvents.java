package astronomyDP19;

import java.util.ArrayList;

public class FindMaxEvents {
	
	public FindMaxEvents() {
		
	}
	
	public Integer dpSolution(ArrayList<Integer> eventCoordinates) {
		int n = eventCoordinates.size();
		int[][] table = new int[n][2*n-1];
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<2*n-1;j++) {
				if(j>=n-1-i && j<=n-1+i) {
					table[i][j] = 0;
				}
				else {
					table[i][j] = -1;
				}
			}
		}
		System.out.println(table[0][n-1]);
		table[n-1][eventCoordinates.get(n-1)+n-1] = 1;
		for(int i = n-2;i>=0;i--) {
			System.out.println("i " +i);
			for(int j = n-1-i;j<=n-1+i;j++) {
				System.out.println("j " +j);
				int max = 0;
				boolean hitMax = false;
				for(int k = -1; k < 2; k++) {
					if(j+k>0 && j+k<2*n-1) {
						int temp = table[i+1][j+k];
						if(temp>max) {
							System.out.println("hit " + k);
							hitMax = true;
							max = temp;
						}
					}
				}
				table[i][j] = max;
				if(j == eventCoordinates.get(i)+n-1 && hitMax) {
					table[i][j] = table[i][j] + 1;
				}
				System.out.println("set" + table[i][j]);
			}
		}
		return table[0][n-1];
	}
}
