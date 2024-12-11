package com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.configuration;

public class Configuration {
    private int totalTickets;
    private int releaseRate;
    private int retrievalRate;
    private int maxCapacity;

    // Default constructor
    public Configuration() {}

    public Configuration(int totalTickets, int releaseRate, int retrievalRate, int maxCapacity) {
        this.totalTickets = totalTickets;
        this.releaseRate = releaseRate;
        this.retrievalRate = retrievalRate;
        this.maxCapacity = maxCapacity;
    }

    // Getters and setters
    public int getTotalTickets() {
        return totalTickets;
    }

    public int getReleaseRate() {
        return releaseRate;
    }

    public int getRetrievalRate() {
        return retrievalRate;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}