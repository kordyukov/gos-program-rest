package ru.fors.gosprogramrest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Configuration
public class WebConfig {
    @Value("${file-request}")
    private String fileRequest;
    @Value("${file-response}")
    private String fileResponse;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean("fileRequest")
    public File fileRequest() throws IOException {
        return getFileForName(fileRequest);
    }

    private File getFileForName(String fileName) throws IOException {
        String uri = Objects.requireNonNull(this.getClass().getClassLoader().getResource(".")).getFile() + fileName;
        File file = new File(uri);
        if (file.createNewFile()) {
            log.info("File: {}, uri: {} is created!", fileName, uri);
        } else {
            log.info("File: {}, uri: {} already exists.", fileName, uri);
        }
        return file;
    }

    @Bean("fileResponse")
    public File fileResponse() throws IOException {
        return getFileForName(fileResponse);
    }
}
