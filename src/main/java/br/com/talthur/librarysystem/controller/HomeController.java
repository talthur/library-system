package br.com.talthur.librarysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.talthur.librarysystem.BookRepository;
import br.com.talthur.librarysystem.models.Book;

@Controller
public class HomeController {
	
	@Autowired
	BookRepository bookRepository;
	
	@RequestMapping("/home")
	public String homeApp(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "listBooks";
	}
	
	@GetMapping("/add")
	public String bookForm (Model model) {
		
		model.addAttribute("book", new Book());
		return "bookForm";
	}
	
	@PostMapping("/process")
	public String processForm(@Validated Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "bookForm";
			
		}else {
			bookRepository.save(book);
			return "redirect:/";
		}
	}

}
