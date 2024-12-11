package com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.services;

import com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.configuration.Configuration;
import com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.models.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class TicketService {
    private ExecutorService executorService;
    private boolean systemRunning = false;
    private TicketPool ticketPool;

    private final ConfigurationService configService;
    private Configuration currentConfig;

    public TicketService(ConfigurationService configService) {
        this.configService = configService;
    }

    public Configuration loadConfiguration() {
        currentConfig = configService.loadConfigFile();
        return currentConfig;
    }

    public void saveConfiguration(Configuration config) {
        configService.saveConfigFile(config);
        this.currentConfig = config;
    }

    public boolean startSystem() {
        if (systemRunning) {
            System.out.println("System is already running");
            return false;
        }

        // Load configuration if not already loaded
        if (currentConfig == null) {
            currentConfig = loadConfiguration();
        }

        // Create a new ExecutorService
        executorService = Executors.newFixedThreadPool(4);

        // Create ticket pool
        ticketPool = new TicketPool(currentConfig.getMaxCapacity());

        // Create and submit vendors and customers
        Vendor vendor1 = new Vendor(1,ticketPool, currentConfig.getReleaseRate(), currentConfig.getTotalTickets());
        Vendor vendor2 = new Vendor(2,ticketPool, currentConfig.getReleaseRate(), currentConfig.getTotalTickets());

        Customer customer1 = new Customer(1,ticketPool, currentConfig.getRetrievalRate(), currentConfig.getTotalTickets() / 2);
        Customer customer2 = new Customer(2,ticketPool, currentConfig.getRetrievalRate(), currentConfig.getTotalTickets() / 2);

        // Submit tasks to ExecutorService
        executorService.submit(vendor1);
        executorService.submit(vendor2);
        executorService.submit(customer1);
        executorService.submit(customer2);

        systemRunning = true;
        System.out.println("System started successfully");
        return true;
    }

    public boolean stopSystem() {
        if (!systemRunning) {
            return false;
        }

        try {
            // Shutdown the executor service
            executorService.shutdown();
            // Wait for tasks to complete
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                // Force shutdown if tasks don't complete
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            // Handle interruption
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        systemRunning = false;
        System.out.println("System stopped");
        return true;
    }

    public void exitSystem() {
        stopSystem();
        System.exit(0);
    }

    public int getPoolSize() {
        if (!systemRunning || ticketPool == null) {
            return 0;
        }
        return ticketPool.getPoolSize();
    }
}