package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.TicketService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Controller {

    @Autowired
   private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private User user;

    public Controller(User user,TicketService ticketService,UserService userService){
        this.user=user;
        this.ticketService=ticketService;
        this.userService=userService;
    }

    public boolean checkTicket() {
        if(!ticketService.checkTicket(user.getTicket())){
            return false;
        }

        int i = 0;
        for (User u:userService.getUsers()){
            if(u.getTicket().equals(user.getTicket())){
                i++;
            }
        }
        if(i>1){
            return false;
        }
        return true;
    }
    public boolean checkIn(){
        return userService.checkBaggageAndDestination(user.getBaggage_id(),user.getTicket().getPlaceTo());
    }
}
