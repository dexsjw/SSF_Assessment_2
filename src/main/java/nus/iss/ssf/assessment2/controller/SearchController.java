package nus.iss.ssf.assessment2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nus.iss.ssf.assessment2.Application;
import nus.iss.ssf.assessment2.model.Book;
import nus.iss.ssf.assessment2.service.BookService;

@Controller
@RequestMapping(path="/book")
public class SearchController {
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    @Autowired
    private BookService bookSvc;

    @GetMapping
    public String searchBook(@RequestParam(required = true) String searchTerm, @ModelAttribute Book book, Model model) {
        List<Book> books = bookSvc.search(searchTerm);
/*         List<String> bookTitle = new ArrayList<>();
        List<String> bookUrl = new ArrayList<>();

        // to use stream method later
        for (Book b: books) {
            bookTitle.add(book.getBookTitle());
            bookUrl.add(book.getWorkId());
        } */
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("book", book);
        model.addAttribute("books", books);
        return "searchResult";
    }

}
