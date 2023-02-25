package demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="catalog")
public class Catalog {

	@XmlElement(name = "book")
	private List<Book> bookList = new ArrayList<>();

	@Override
	public String toString() {
		return String.format("Catalog [bookList=%s]", bookList);
	}

}
