package nus.iss.ssf.assessment2.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nus.iss.ssf.assessment2.Application;
import nus.iss.ssf.assessment2.model.Book;
import nus.iss.ssf.assessment2.service.BookService;

@Controller
public class BookController {

    private Logger logger = Logger.getLogger(Application.class.getName());

    @Autowired
    private BookService bookSvc;
    
    @GetMapping(path="/book/{workId}")
    public String getBook(@PathVariable(required = true) String workId, Model model) {
        Book bookDetail = bookSvc.getBook(workId);
        logger.log(Level.INFO, "%s %s %s".formatted(bookDetail.getBookTitle(), bookDetail.getDescription(), bookDetail.getExcerpt()));
        model.addAttribute("bookDetail", bookDetail);
        return "bookDetail";
    }

}
