package astronomyDP19;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		InputFileReader in = new InputFileReader();
		ArrayList<Integer> eventCoordinates;
		try {
			eventCoordinates = in.readInputFile("input.txt");
		} catch (IOException e) {
			System.out.println(e);
			return;
		}
		FindMaxEvents solver = new FindMaxEvents();
		Integer maxEventsVisited = solver.dpSolution(eventCoordinates);
		System.out.println(maxEventsVisited);
		//System.out.println("Events to visit in order:");
		// input: 0 0 0 4 3 3 8 -1 6 8
		// Expected output: [1, 2, 5, 10]
		List<Integer> events = solver.solutionCoords();
		System.out.println(Arrays.toString(events.toArray()));
		List<Integer> movement = solver.findMovement(events, eventCoordinates);
		System.out.println(Arrays.toString(movement.toArray()));
		// -- Other inputs --
		// input: 1 -1 4 -3 5 8 9 -7 2 3 -10
		// Expected output: [2, 4, 8, 11]
	}

}
