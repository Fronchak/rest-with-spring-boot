package com.fronchak.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fronchak.models.services.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	private BookService service;
}
