package com.myhotel.domain;

import javax.persistence.*;

@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    private int receiptNumber;
    private int bookingNumber;
    private float total;
    @OneToOne
    private HotelUser customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public HotelUser getCustomer() {
        return customer;
    }

    public void setCustomer(HotelUser customer) {
        this.customer = customer;
    }
}
