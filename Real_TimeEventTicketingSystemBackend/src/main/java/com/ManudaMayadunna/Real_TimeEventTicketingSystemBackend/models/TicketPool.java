package com.ManudaMayadunna.Real_TimeEventTicketingSystemBackend.models;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private final List<Ticket> tickets;
    private final int maxCapacity;
    private final Lock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    private boolean customersFinished;

    public TicketPool(int maxCapacity) {
        this.tickets = new LinkedList<>();
        this.maxCapacity = maxCapacity;
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
        this.customersFinished = false;
    }

    public void addTicket(Ticket ticket) throws InterruptedException {
        lock.lock();
        try {
            while (tickets.size() >= maxCapacity) {
                notFull.await();
            }
            tickets.add(ticket);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Ticket buyTicket() throws InterruptedException {
        lock.lock();
        try {
            while (tickets.isEmpty() && !customersFinished) {
                notEmpty.await();
            }
            return tickets.isEmpty() ? null : tickets.remove(0);
        } finally {
            lock.unlock();
        }
    }

    public void setCustomersFinished() {
        lock.lock();
        try {
            customersFinished = true;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public boolean areCustomersFinished() {
        return customersFinished;
    }

    public int getPoolSize() {
        lock.lock();
        try {
            return tickets.size();
        } finally {
            lock.unlock();
        }
    }
}