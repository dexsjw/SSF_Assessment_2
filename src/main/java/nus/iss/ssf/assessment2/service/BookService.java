package nus.iss.ssf.assessment2.service;

import static nus.iss.ssf.assessment2.Constants.URL_OPENLIBRARY;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import nus.iss.ssf.assessment2.model.Book;

@Service
public class BookService {

    public List<Book> search(String searchTerm) {
        
        final String url = UriComponentsBuilder
        .fromUriString(URL_OPENLIBRARY)
        .queryParam("title", searchTerm.trim().replace(" ", "+"))
        .queryParam("fields", "title,key")
        .queryParam("limit", "20")
        .toUriString();

        final RequestEntity<Void> req = RequestEntity
        .get(url)
        .accept(MediaType.APPLICATION_JSON)
        .build();

        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);

        if (resp.getStatusCode() != HttpStatus.OK)
        throw new IllegalArgumentException(
            "Error: Status code %s".formatted(resp.getStatusCode())
            );
        
        try {
            final InputStream is = new ByteArrayInputStream(resp.getBody().getBytes());
            final JsonReader reader = Json.createReader(is);
            final JsonObject data = reader.readObject();
            final JsonArray bookInfo = data.getJsonArray("docs");

            return bookInfo.stream()
                .map(v -> (JsonObject)v)
                .map(Book::create)
                .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();

    }
    
}
