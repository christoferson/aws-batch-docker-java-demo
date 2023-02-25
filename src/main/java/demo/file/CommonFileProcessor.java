package demo.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
	
	public static void demoWriteFileLineByLine(String path, List<String> lines) {		
		
		Charset charset = StandardCharsets.UTF_8;
		//charset = Charset.forName("Cp1047");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path), charset))) {
			
			for (String line : lines) {
				writer.write(line);
				writer.write("\r\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
