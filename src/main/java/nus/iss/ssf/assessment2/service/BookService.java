package nus.iss.ssf.assessment2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import nus.iss.ssf.assessment2.model.Book;

@Service
public class BookService {

    public List<Book> search(String searchTerm) {
        List<Book> books = new ArrayList<>();
        for (Book b: books) {
            b.setBookTitle(searchTerm);
            b.setWorkId("/index");
        }
        return books;
    }
    
}
