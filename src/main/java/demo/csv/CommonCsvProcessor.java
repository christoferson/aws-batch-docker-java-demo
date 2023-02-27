package demo.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.opencsv.CSVWriter;

public class CommonCsvProcessor {

	public static void writeLineByLine(String path, List<String[]> lines) {
		Charset charset = StandardCharsets.UTF_8;
	    try (CSVWriter writer = new CSVWriter(new BufferedWriter(new FileWriter(new File(path), charset)))) {
	        for (String[] line : lines) {
	            writer.writeNext(line);
	        }
	    } catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	    
}
