package com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.configuration.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ConfigurationService {
    private static final String CONFIG_FILE = "config.json";
    private final ObjectMapper objectMapper;

    public ConfigurationService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void saveConfigFile(Configuration config) {
        try {
            objectMapper.writeValue(new File(CONFIG_FILE), config);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save the configuration", e);
        }
    }

    public Configuration loadConfigFile() {
        try {
            File file = new File(CONFIG_FILE);
            if (file.exists()) {
                return objectMapper.readValue(file, Configuration.class);
            } else {
                // Return a default configuration if no file exists
                return new Configuration(100, 2, 1, 100);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load the configuration file", e);
        }
    }
}