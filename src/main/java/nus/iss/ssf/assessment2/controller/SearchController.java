package nus.iss.ssf.assessment2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nus.iss.ssf.assessment2.model.Book;
import nus.iss.ssf.assessment2.service.BookService;

@Controller
@RequestMapping(path="/book")
public class SearchController {

    @Autowired
    private BookService bookSvc;

    @GetMapping
    public String searchBook(@RequestParam(required = true) String searchTerm, Model model) {
        List<Book> books = bookSvc.search(searchTerm);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("books", books);
        return "searchResult";
    }

}
