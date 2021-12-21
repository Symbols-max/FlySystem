package com.example.demo;

import com.example.demo.controller.Controller;
import com.example.demo.model.Coupon;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.service.CouponService;
import com.example.demo.service.TicketService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoApplication {

    public static void main(String[] args) throws Exception {
        CouponService couponService=CouponService.getInstance();
       Coupon coupon=new Coupon(1);
            couponService.addCoupon(coupon);

        TicketService ticketService=TicketService.getInstance();
        for (int i = 0; i < 10; i++) {
            ticketService.addTicket(new Ticket(i,10+i,"Place"+i,i));
        }

        UserService userService=UserService.getInstance();
        Coupon[] coupons={new Coupon(1000),coupon};
        User user=new User(1,new Ticket(1,11,"Place1",1),1,coupons);
        userService.addUser(user);

      //  Exception
     //   System.out.println(userService.useCoupon(user,user.getCoupons()[0]));

        Controller controller=new Controller(user,ticketService,userService);
       boolean b=controller.checkTicket();
       if(b==true){
           if(controller.checkIn()){
               System.out.println("Вы прошли проверку");
           }
       }

        // throw Exception
//        User user1=new User(1,new Ticket(1,11,"Place1",1),1,coupons);
//        userService.addUser(user1);
    }

}
