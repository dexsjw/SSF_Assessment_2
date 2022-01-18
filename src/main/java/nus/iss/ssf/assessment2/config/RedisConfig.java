package nus.iss.ssf.assessment2.config;

import static nus.iss.ssf.assessment2.Constants.ENV_REDIS_PW;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import nus.iss.ssf.assessment2.Application;

@Configuration
public class RedisConfig {
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Optional<Integer> redisPort;

    @Value("${spring.redis.database}")
    private Integer redisDatabase;

    private final String redisPassword;

    public RedisConfig() {
        redisPassword = System.getenv(ENV_REDIS_PW);
    }

    @Bean
    public RedisTemplate<String, String> createRedisTemplate() {
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        if (redisPort.isPresent())
            config.setPort(redisPort.get());
        if (null != redisPassword) {
            config.setPassword(redisPassword);
            logger.info("Redis password set!");
        } else {
            logger.warning("Redis password not set!");
            System.exit(1);
        }
        config.setDatabase(redisDatabase);

        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
        jedisFac.afterPropertiesSet();
        logger.log(Level.INFO, "redis host: %s, port: %s".formatted(redisHost, redisPort));

        final RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisFac);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}
