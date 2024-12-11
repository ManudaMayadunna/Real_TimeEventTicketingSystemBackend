package com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.controllers;

import com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.configuration.Configuration;
import com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/command")
public class CommandController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/config")
    public Configuration loadConfig() {
        return ticketService.loadConfiguration();
    }

    @PostMapping("/config")
    public Configuration saveConfig(@RequestBody Configuration config) {
        ticketService.saveConfiguration(config);
        return config;
    }

    @PostMapping("/start")
    public String startSystem() {
        boolean started = ticketService.startSystem();
        return started ? "System started successfully!" : "System is already running!";
    }

    @PostMapping("/stop")
    public String stopSystem() {
        boolean stopped = ticketService.stopSystem();
        return stopped ? "System stopped successfully!" : "System is not running!";
    }

    @PostMapping("/exit")
    public String exitSystem() {
        ticketService.exitSystem();
        return "System exited!";
    }

    @GetMapping("/poolsize")
    public int getPoolSize() {
        return ticketService.getPoolSize();
    }
}