package com.example.demo.service;

import com.example.demo.model.Coupon;
import com.example.demo.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TicketService {
    private Set<Ticket> tickets;

    private static volatile TicketService ticketService;

    private TicketService(){
        init();
    }

    public static TicketService getInstance() {
        TicketService ticketServ = ticketService;
        if (ticketServ != null) {
            return ticketServ;
        }
        synchronized (TicketService.class) {
            if (ticketService == null) {
                ticketService = new TicketService();
            }
            return ticketService;
        }
    }

    private void init(){
        tickets =new HashSet<>();
    }

    public boolean addTicket(Ticket ticket){
        if(tickets.add(ticket)){
            return true;
        }
        return false;
    }

    public void changePrice(Ticket ticketOld,double newPrice){
        for(Ticket ticket:tickets){
            if(ticket.equals(ticketOld)){
                ticket.setPrice(newPrice);
            }
        }
    }

    public boolean deleteTicket(Ticket ticket){
        if(tickets.remove(ticket)){
            return true;
        }
        return false;
    }

    public boolean checkTicket(Ticket ticket){
        if(tickets.contains(ticket)){
            return true;
        }
        return false;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public boolean checkDestination(int id){

        for (Ticket ticket:tickets) {
            if(ticket.getPlaceTo()==id){
                return true;
            }
        }
        return false;
    }

    public void changePrice(int percent,Ticket ticket){
        for (Ticket ticket1:tickets){
            if(ticket1.equals(ticket)){
                double newPrice=ticket1.getPrice()*percent/100;
               changePrice(ticket,newPrice);
            }
        }
    }

}
