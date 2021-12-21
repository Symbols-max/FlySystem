package com.example.demo.service;

import com.example.demo.exception.MyException;
import com.example.demo.model.Coupon;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;

    @Autowired
    CouponService couponService;

    @Autowired
    TicketService ticketService;

    private static volatile UserService userService;

    private UserService(){
        this.couponService=CouponService.getInstance();
        this.ticketService=TicketService.getInstance();
        init();
    }

    public static UserService getInstance() {
        UserService userServ = userService;
        if (userServ != null) {
            return userServ;
        }
        synchronized (UserService.class) {
            if (userService == null) {
                userService = new UserService();
            }
            return userService;
        }
    }

    private void init(){
        users =new ArrayList<>();
    }

    public boolean addUser(User user){
        if(users.add(user)){
            return true;
        }
        return false;
    }

    public boolean deleteUser(User user){
        if(users.remove(user)){
            return true;
        }
        return false;
    }

    public boolean checkUser(User user){
        if(users.contains(user)){
            return true;
        }
        return false;
    }

    public boolean checkBaggage(int baggageId){
        for (User user:users) {
            if(user.getBaggage_id()==baggageId){
                return true;
            }
        }

        return false;
    }

    public boolean checkBaggageAndDestination(int baggageId,int destinationId){
        for (User user:users) {
            if(user.getTicket().getPlaceTo()==destinationId && user.getBaggage_id()==baggageId){
                return true;
            }
        }

        return false;
    }

        public double useCoupon(User user, Coupon coupon) throws MyException {
        double newPrice =user.getTicket().getPrice();
        if(couponService.checkCoupon(coupon.getId())) {
            for (User user1:users){
                if(user1.equals(user)){
                    ticketService.changePrice(coupon.getDiscount(),user.getTicket());
                    user.getTicket().setPrice(newPrice);
                    newPrice=user.getTicket().getPrice();
                }
            }
        }
        else{
            throw new MyException("Цена не изменилась");
        }
        return newPrice;
        }

        public User findUserByTicket(Ticket ticket){
        for(User user:users){
            if(user.getTicket().equals(ticket)){
                return user;
            }
        }
        return null;
        }

    public List<User> getUsers() {
        return users;
    }

}
