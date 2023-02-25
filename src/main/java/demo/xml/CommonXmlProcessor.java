package demo.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import demo.model.Catalog;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

public class CommonXmlProcessor {
	
	public static void demoParseXmlFile(String path) {

		try (BufferedReader reader = new BufferedReader(new FileReader(new File(path), StandardCharsets.UTF_8))) {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			
			String line = null;

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				Catalog catalog = parse(jaxbContext, line);
				System.out.printf("%s %n", catalog);
				
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static Catalog parse(JAXBContext jaxbContext, String xml) {	
		
		Catalog catalog = null;

		try {

			StringReader sr = new StringReader(xml);
			
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			catalog = (Catalog) unmarshaller.unmarshal(sr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
		return catalog;
	}

}
