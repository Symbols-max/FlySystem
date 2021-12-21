package com.example.demo.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Ticket {

 private long id;
 private double price;
 private String placeFrom;
 private int destinationId;

    public Ticket(long id, double price, String placeFrom, int destinationId) {
        this.id = id;
        this.price = price;
        this.placeFrom = placeFrom;
        this.destinationId=destinationId;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPlaceFrom() {
        return placeFrom;
    }

    public void setPlaceFrom(String placeFrom) {
        this.placeFrom = placeFrom;
    }

    public int getPlaceTo() {
        return destinationId;
    }

    public void setPlaceTo(int placeTo) {
        this.destinationId = placeTo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (id != ticket.id) return false;
        if (Double.compare(ticket.price, price) != 0) return false;
        if (destinationId != ticket.destinationId) return false;
        return placeFrom != null ? placeFrom.equals(ticket.placeFrom) : ticket.placeFrom == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (placeFrom != null ? placeFrom.hashCode() : 0);
        result = 31 * result + destinationId;
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", placeFrom='" + placeFrom + '\'' +
                ", destinationId=" + destinationId +
                '}';
    }
}
