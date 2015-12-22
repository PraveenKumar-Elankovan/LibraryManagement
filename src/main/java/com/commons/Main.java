package com.commons;

import com.controller.LibraryController;
import com.service.LibraryService;

public class Main {
	public static void main(String[] args) {
		new LibraryController(new LibraryService());
	}
}

