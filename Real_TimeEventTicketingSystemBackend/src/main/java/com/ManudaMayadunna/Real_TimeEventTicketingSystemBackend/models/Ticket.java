    package com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.models;

    public class Ticket {
        private final int ticketId;

        public Ticket(int ticketId) {
            this.ticketId = ticketId;
        }

        @Override
        public String toString() {
            return "Ticket ID: " + ticketId;
        }
    }