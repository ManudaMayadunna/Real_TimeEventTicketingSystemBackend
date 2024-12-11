package com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.models;

public class Vendor implements Runnable {
    private static int globalTicketIdCounter = 1;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final int totalTickets;
    private final int vendorId;

    public Vendor(int vendorId,TicketPool ticketPool, int ticketReleaseRate, int totalTickets) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.totalTickets = totalTickets;
        this.vendorId = vendorId;
    }

    @Override
    public void run() {
        try {
            while (globalTicketIdCounter <= totalTickets) {
                synchronized (Vendor.class) {
                    Ticket ticket = new Ticket(globalTicketIdCounter++);
                    try {
                        System.out.println("Vendor "+vendorId + " attempting to add ticket: " + ticket);
                        ticketPool.addTicket(ticket);
                        System.out.println("Vendor "+vendorId + " added ticket: " + ticket);
                    } catch (InterruptedException e) {
                        System.out.println("Vendor "+vendorId + " interrupted while adding tickets");
                        Thread.currentThread().interrupt();
                        break;
                    }
                }

                try {
                    Thread.sleep(ticketReleaseRate * 1000);
                } catch (InterruptedException e) {
                    System.out.println("Vendor "+vendorId + " interrupted while sleeping");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Vendor "+vendorId + " encountered an unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Vendor "+vendorId + " finished running");
            ticketPool.setCustomersFinished();
        }
    }
}