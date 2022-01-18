package nus.iss.ssf.assessment2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.ssf.assessment2.model.Book;
import nus.iss.ssf.assessment2.repository.BookRepository;
import static nus.iss.ssf.assessment2.Constants.*;

@Service
public class BookCacheService {

    @Autowired
    private BookRepository bookRepo;
    
    public void save(String workId, Book bookDetail) {
        String value = String.join(DELIMITER, bookDetail.getBookTitle(), bookDetail.getDescription(), bookDetail.getExcerpt());
        bookRepo.save(workId, value);
    }

    public Book get(String workId) {
        Optional<String> bookCache = bookRepo.get(workId);
        if (bookCache.isPresent()) {
            String[] bookDetails = bookCache.get().split(DELIMITER);
            Book bookDetail = new Book(bookDetails[0], bookDetails[1], bookDetails[2]);
            return bookDetail;
        } else {
            return new Book();
        }
    }

    public Optional<String> check(String workId) {
        Optional<String> bookCache = bookRepo.get(workId);
        return bookCache;
    }

}
