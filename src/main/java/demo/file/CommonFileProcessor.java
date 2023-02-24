package demo.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CommonFileProcessor {

	public static void demoReadFileLineByLine(String path) {		

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
