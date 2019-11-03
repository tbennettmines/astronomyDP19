package astronomyDP19;

import java.io.IOException;
import java.util.ArrayList;

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
	}

}
