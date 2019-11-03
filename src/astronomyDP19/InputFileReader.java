package astronomyDP19;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputFileReader {
	private File file;
	private FileReader fileReader;
	private BufferedReader in;
	
	public InputFileReader() {
		
	}
	
	public ArrayList<Integer> readInputFile(String inputFile) throws IOException {
		// Create new problem definition
		ArrayList<Integer> eventCoordinates = new ArrayList<Integer>();
		
		// Local vars for reading in the file
		String line;
		String[] splitLine;
		
		// Open streams to file
		file = new File(inputFile);
		fileReader = new FileReader(file);
		in = new BufferedReader(fileReader);
			
		line = in.readLine();
		splitLine = line.split(" ");
			
		for (String c : splitLine) {
			if (!c.equals(""))
				eventCoordinates.add(Integer.parseInt(c));
		}
		
		return eventCoordinates;
	}
}
