package com.controller;

import static spark.Spark.*;
import static com.commons.JsonUtil.*;
import java.util.List;
import com.commons.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Book;
import com.model.User;
import com.service.LibraryService;

public class LibraryController {

	public LibraryController(final LibraryService library) {

		get("/users", (req, res) -> library.getAllUsers(), json());

		post("/users", (req, res) -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				User user = mapper.readValue(req.body(), User.class);

				if (user.isValid()) {
					library.addUser(user);
					return user;
				} else {
					res.status(400);
					return new ResponseError("Bad Request");
				}
			} catch (Exception e) {
				return new ResponseError("Bad Request");
			}
		}, json());

		put("/users/:id", (req, res) -> {
			String userId = req.params("id");
			int userIndex = library.getUserIndex(userId);
			if (userIndex == -1) {
				res.status(404);
				return new ResponseError("User Not Found");
			}
			ObjectMapper mapper = new ObjectMapper();
			User user;
			try {
				user = mapper.readValue(req.body(), User.class);
				if (user.isValid()) {
					user.update(userId);
					library.updateUser(userIndex, user);
					return user;
				} else {
					res.status(400);
					return new ResponseError("Bad Request");
				}
			} catch (Exception e1) {
				res.status(500);
				return new ResponseError("Server Error");
			}

		}, json());

		get("/books", (req, res) -> library.getAllBooks(), json());

		get("/books/search", (req, res) -> {
			String name = req.queryParams("name");
			List<Book> bookList = library.getBookByName(name);
			return bookList;
		}, json());

		post("/books", (req, res) -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				System.out.println(req.body());
				Book book = mapper.readValue(req.body(), Book.class);

				if (book.isValid()) {
					library.addBook(book);
					return book;
				} else {
					res.status(400);
					return new ResponseError("Bad Request");
				}
			} catch (Exception e) {
				System.out.println(e);
				return new ResponseError("Bad Request");
			}
		}, json());

		put("/books/:id", (req, res) -> {
			String bookId = req.params("id");
			int bookIndex = library.getBookIndex(bookId);
			if (bookIndex == -1) {
				res.status(404);
				return new ResponseError("Book Not Found");
			}
			ObjectMapper mapper = new ObjectMapper();
			Book book;
			try {
				book = mapper.readValue(req.body(), Book.class);
				if (book.getCheckedOutBy() == null) {
					res.status(400);
					return new ResponseError("Bad Request");
				}
				book = library.checkOutBook(book.getCheckedOutBy(), bookId);
				return book;

			} catch (Exception e1) {
				res.status(500);
				return new ResponseError(e1.getMessage());
			}
		}, json());

		after((req, res) -> {
			res.type("application/json");
		});

		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(400);
			res.body(toJson(new ResponseError(e)));
		});
	}
}
