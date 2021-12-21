package com.example.demo.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Coupon {
    private long id;
    private int discount;
    private final int[] discounts={10,50,60};

    public Coupon(long id) {
        this.id = id;
        int i= (int) (Math.random()*3);
        this.discount = discounts[i];
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }


    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", discount=" + discount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coupon coupon = (Coupon) o;

        if (id != coupon.id) return false;
        return discount == coupon.discount;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + discount;
        return result;
    }
}
