package com.controller;

import static spark.Spark.*;
import static com.commons.JsonUtil.*;

import com.commons.*;
import com.model.Book;
import com.service.UserService;

public class UserController {

	public UserController(final UserService userService) {

		get("/users", (req, res) ->  userService.getAllUsers(), json());

		post("/users", (req, res) -> userService.createUser(
				req.queryParams("fName"), req.queryParams("mName"),
				req.queryParams("lName"),
				Integer.parseInt(req.queryParams("age")),
				req.queryParams("gender").toCharArray()[0],
				req.queryParams("phone"), req.queryParams("zip")), json());

		put("/users", (req, res) -> userService.updateUser(
				req.queryParams("id"), req.queryParams("fName"),
				req.queryParams("mName"), req.queryParams("lName"),
				Integer.parseInt(req.queryParams("age")),
				req.queryParams("gender").toCharArray()[0],
				req.queryParams("phone"), req.queryParams("zip")), json());

		get("/books", (req, res) -> userService.getAllBooks(), json());
		
		get("/books/:name", (req, res) -> {
			String name=req.params("name");
			Book bookObj = userService.getObjAndIndex("book",name).getBookObj();
			if (bookObj != null) {
				return bookObj;
			}
			res.status(400);
			return new ResponseError("No Book with name %s found", name);
		}, json());

		post("/books", (req, res) -> userService.createBook(
				req.queryParams("name"), 
				req.queryParams("authors").split(","),
				req.queryParams("checkedBy")), json());
		
		put("/books", (req,res) -> {
			Book bookObj=userService.checkOutBook(
				req.queryParams("userID"),
				req.queryParams("bookID"));
			if(bookObj!=null)
				return bookObj;
			res.status(400);
			return new ResponseError("Book ID %s is already checked Out", req.queryParams("bookID"));
			},json()); 

		after((req, res) -> {
			res.type("application/json");
		});

		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(400);
			res.body(toJson(new ResponseError(e)));
		});
	}
}
