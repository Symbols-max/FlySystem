package com.example.demo.service;

import com.example.demo.model.Coupon;
import com.example.demo.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CouponService {
    private Set<Coupon> coupons;
    private static volatile CouponService couponService;

    private CouponService() {
        init();
    }

    private void init(){
        coupons=new HashSet<>();
    }

    public static CouponService getInstance() {
        CouponService couponServ=couponService;
        if (couponServ != null) {
            return couponServ;
        }
        synchronized (CouponService.class) {
            if (couponService == null) {
                couponService = new CouponService();
            }
            return couponService;
        }
    }

    public boolean addCoupon(Coupon coupon){
        if(coupons.add(coupon)){
            return true;
        }
        return false;
    }

    public boolean deleteCoupon(Coupon coupon){
        if(coupons.remove(coupon)){
            return true;
        }
        return false;
    }

    public boolean checkCoupon(long id){
        for (Coupon coupon:coupons) {
            if(coupon.getId()==id){
                return true;
            }
        }
        return false;
    }

    public Set<Coupon> getCoupons() {
        return coupons;
    }
}
