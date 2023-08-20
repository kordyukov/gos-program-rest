package ru.fors.gosprogramrest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@Configuration
public class WebConfig {
    @Value("${file-request}")
    private String fileRequest;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public File file() throws URISyntaxException, IOException {
        String uri = this.getClass().getClassLoader().getResource(".").getFile() + fileRequest;
        File file = new File(uri);
        if (file.createNewFile()) {
            log.info("File: {} is created!", uri);
        } else {
            log.info("File: {} already exists.", uri);
        }
        return file;
    }
}
