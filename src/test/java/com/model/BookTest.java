package com.model;
import com.model.Book;
import static org.junit.Assert.*;
public class BookTest {
	public void createBookTest(String name, String[] authors, String checkedByUserID) {
		String[] au={"Praveen", "Dan Brown"};
		Book book=Book.createBook("Davinci Code",au);
		assertEquals(book.getName(),"Davinci Code");
	}
}
