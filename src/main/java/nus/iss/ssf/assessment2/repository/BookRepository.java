package nus.iss.ssf.assessment2.repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void save(String workId, String bookDetail) {
        redisTemplate.opsForValue().set(normalize(workId), bookDetail, 10L, TimeUnit.MINUTES);
    }

    public Optional<String> get(String workId) {
        Optional<String> bookCache = Optional.ofNullable(redisTemplate.opsForValue().get(normalize(workId)));
        return bookCache;
    }

    public String normalize(String s) {
        return s.trim().toLowerCase();
    }
}
