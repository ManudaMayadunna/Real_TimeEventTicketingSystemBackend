package com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.models;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private final int numberOfTicketsToBuy;
    private int customerId;

    public Customer(int customerId,TicketPool ticketPool, int customerRetrievalRate, int numberOfTicketsToBuy) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.numberOfTicketsToBuy = numberOfTicketsToBuy;
        this.customerId=customerId;
    }

    @Override
    public void run() {
        System.out.println(customerId + " started running");
        int ticketsBought = 0;

        try {
            while (ticketsBought < numberOfTicketsToBuy) {
                if (ticketPool.areCustomersFinished()) {
                    System.out.println(customerId + " detected customers are finished");
                    break;
                }

                try {
                    Ticket ticket = ticketPool.buyTicket();
                    if (ticket == null) {
                        System.out.println("Customer "+customerId + " received null ticket");
                        break;
                    }

                    ticketsBought++;
                    System.out.println("Customer "+customerId + " bought ticket: " + ticket +
                            " (Tickets bought: " + ticketsBought + ")");

                    Thread.sleep(customerRetrievalRate * 1000);
                } catch (InterruptedException e) {
                    System.out.println("Customer "+customerId + " interrupted while buying tickets");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Customer "+customerId + " encountered an unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Customer "+customerId + " finished running. Total tickets bought: " + ticketsBought);
        }
    }
}