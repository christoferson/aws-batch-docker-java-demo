package demo.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CommonFileProcessor {

	public static void demoReadFileLineByLine(String path) {		

		try (BufferedReader reader = new BufferedReader(new FileReader(new File(path), StandardCharsets.UTF_8))) {
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
