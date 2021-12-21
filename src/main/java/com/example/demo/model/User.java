package com.example.demo.model;

import com.example.demo.service.TicketService;
import com.example.demo.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

//
//@NoArgsConstructor
public class User {
    private final static Logger logger=Logger.getLogger(User.class.getName());
    private long id;
    private Ticket ticket;
    private int baggage_id;
    private Coupon[] coupons;

    @Autowired
    Ticket service;

    public User(long id, Ticket ticket, int baggage_id, Coupon[] coupons) throws Exception {
        this.id = id;
        UserService userService=UserService.getInstance();
        User user=userService.findUserByTicket(ticket);
        try {
            if (user == null) {
                this.ticket = ticket;
            } else {
                throw new Exception();
            }
        }
        catch (Exception e){
            logger.log(Level.WARNING,"Билет занят",e);
        }
        this.baggage_id = baggage_id;
        this.coupons = coupons;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getBaggage_id() {
        return baggage_id;
    }

    public void setBaggage_id(int baggage_id) {
        this.baggage_id = baggage_id;
    }

    public Coupon[] getCoupons() {
        return coupons;
    }

    public void setCoupons(Coupon[] coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", ticket=" + ticket +
                ", baggage_id=" + baggage_id +
                ", coupons=" + Arrays.toString(coupons) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (baggage_id != user.baggage_id) return false;
        if (ticket != null ? !ticket.equals(user.ticket) : user.ticket != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(coupons, user.coupons);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (ticket != null ? ticket.hashCode() : 0);
        result = 31 * result + baggage_id;
        result = 31 * result + Arrays.hashCode(coupons);
        return result;
    }
}
