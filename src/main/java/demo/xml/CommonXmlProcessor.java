package demo.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

public class CommonXmlProcessor {
	
	public static <T> List<T> demoParseXmlFile(String path, Class<T> clazz) {
		
		List<T> elements = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(new File(path), StandardCharsets.UTF_8))) {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			
			String line = null;

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				T element = parse(jaxbContext, line, clazz);
				System.out.printf("%s %n", element);
				elements.add(element);
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return elements; 

	}
	
	public static <T> T parse(JAXBContext jaxbContext, String xml, Class<T> clazz) {	
		
		T element = null;

		try {

			StringReader sr = new StringReader(xml);
			
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			element = clazz.cast(unmarshaller.unmarshal(sr));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
		return element;
	}

}
