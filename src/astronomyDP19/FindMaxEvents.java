package astronomyDP19;

import java.util.*;

public class FindMaxEvents {
	
	int[][] table;
	public List<Integer> eventCoordinates;
	
	public void printTable(int[][] table) {
		for (int i = 0; i < table.length; i++) {
			System.out.println(Arrays.toString(table[i]));
		}
	}
	
	public Integer dpSolution(List<Integer> eventCoordinates) {
		this.eventCoordinates = eventCoordinates;
		
		int n = eventCoordinates.size();
		if (table == null)
			table = new int[n+1][2*n + 1];
		
		// Populate table with 0s to indicate valid positions j at time i
		// and -1's to indicate invalid positions
		for(int i = 0; i < n+1; i++) {
			for(int j = 0; j < 2*n + 1; j++) {
				if(j >= n - i && j <= n + i) {
					table[i][j] = 0;
				}
				else {
					table[i][j] = -1;
				}
			}
		}
		
		// Set position d_n in the last row to 1
		table[n][eventCoordinates.get(n - 1) + n] = 1;
		
		// Iterate through and populate each element according to the maximum
		// of the 3 immediate elements below the current row at the position
		// and increment by 1 if the position is at d_i
		for (int i = n - 1; i >= 1; i--) {
			for (int j = n - i; j <= n + i; j++) {
				
				table[i][j] = Math.max(table[i+1][j-1], Math.max(table[i+1][j], table[i+1][j+1]) );
				
				if (j == eventCoordinates.get(i - 1) + n && table[i][j] > 0) {
					table[i][j] = table[i][j] + 1;
				}
			}
		}
		
		table[0][n] = Math.max(table[1][n-1], Math.max(table[1][n], table[1][n+1]) );
		
		return table[0][n];
	}
	
	public int getMaxi(int[] array) {
		int m = Integer.MIN_VALUE;
		
		for (int i = 0; i < array.length; i++) {
		    if (array[i] > m) {
		      m = i;
		    }
		}
		return m;
	}

	// Gets a list of the optimal times where events can be observed
	public TracebackStep solutionCoords() {
		TracebackStep step = getLargestSubset(0, table.length - 1, eventCoordinates);
		
		// Sort the list of times so they're in chronological order
		Collections.sort(step.coords);
		Collections.reverse(step.movements);
		
		return step;
	}
	
	private TracebackStep getLargestSubset(int row, int col, List<Integer> eventCoords) {
		TracebackStep step = new TracebackStep();
		
		// If you are on the last row, return a step with the time of the last event
		if (row == table.length - 1) {
			step.coords.add(table.length - 1);
			return step;
		}
		
		// If the event occurs in the current column and row, add the event time
		// (row) to the step
		if (row != 0 && col == eventCoords.get(row - 1) + eventCoords.size()) {
			step.coords.add(row);
		}
		
		TracebackStep leftStep, downStep, rightStep;
		leftStep = new TracebackStep();
		downStep = new TracebackStep();
		rightStep = new TracebackStep();
		
		// Get step with largest subset of events from the left,
		// right, and same column on the next row
		if (col > 1 && table[row + 1][col - 1] != 0) {
			leftStep = getLargestSubset(row + 1, col - 1, eventCoords);
		}
		if (table[row + 1][col] != 0) {
			downStep = getLargestSubset(row + 1, col, eventCoords);
		}
		if (col < 2*table.length && table[row + 1][col + 1] != 0) {
			rightStep = getLargestSubset(row + 1, col + 1, eventCoords);
		}
		
		// Depending on which step has the larger subset of events,
		// add the event times and movements to the current step
		if (leftStep.coords.size() > downStep.coords.size() && 
			leftStep.coords.size() > rightStep.coords.size()) {
			step.coords.addAll(leftStep.coords);
			step.movements = leftStep.movements;
			step.movements.add("-1"); // Add -1 to movements, indicating a left movement
		}
		else if (downStep.coords.size() > leftStep.coords.size() && 
				 downStep.coords.size() > rightStep.coords.size()) {
				step.coords.addAll(downStep.coords);
				step.movements = downStep.movements;
				step.movements.add("0"); // Add 0 to movements, indicating no movement
		}
		else if (rightStep.coords.size() > leftStep.coords.size() && 
				rightStep.coords.size() > downStep.coords.size()) {
				step.coords.addAll(rightStep.coords);
				step.movements = rightStep.movements;
				step.movements.add("+1"); // Add +1 to movements, indicating a right movement
		}
		
		return step;
	}
}
