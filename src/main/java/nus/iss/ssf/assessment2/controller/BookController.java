package nus.iss.ssf.assessment2.controller;

import static nus.iss.ssf.assessment2.Constants.BEAN_BOOKCACHE;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nus.iss.ssf.assessment2.Application;
import nus.iss.ssf.assessment2.model.Book;
import nus.iss.ssf.assessment2.service.BookCacheService;
import nus.iss.ssf.assessment2.service.BookService;

@Controller
public class BookController {

    private Logger logger = Logger.getLogger(Application.class.getName());

    @Autowired
    private BookService bookSvc;

    @Autowired
    @Qualifier(BEAN_BOOKCACHE)
    private BookCacheService bookCacheSvc;
    
    @GetMapping(path="/book/{workId}")
    public String getBook(@PathVariable(required = true) String workId, Model model) {
        
        if (bookCacheSvc.check(workId).isPresent()) {
            Book bookDetail = bookCacheSvc.get(workId);
            model.addAttribute("bookDetail", bookDetail);
            return "bookDetail";
        } else {
            Book bookDetail = bookSvc.getBook(workId);
            bookCacheSvc.save(workId, bookDetail);
            model.addAttribute("bookDetail", bookDetail);
            return "bookDetail";
        }
        
        //logger.log(Level.INFO, "%s %s %s".formatted(bookDetail.getBookTitle(), bookDetail.getDescription(), bookDetail.getExcerpt()));
    }

}
