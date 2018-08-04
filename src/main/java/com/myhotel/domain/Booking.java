package com.myhotel.domain;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;
import org.springframework.boot.autoconfigure.data.ldap.LdapDataAutoConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    private String bookingNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @OneToMany(fetch = FetchType.EAGER)
    @IndexColumn(name="id")
    private List<Room> rooms = new ArrayList<Room>();
    private Boolean checkInStatus;
    private Boolean checkOutStatus;
    private String startDate_S;

    public Booking() {
    }

    public String getStartDate_S() {
        return startDate_S;
    }

    public void setStartDate_S(String startDate_S) {
        this.startDate_S = startDate_S;
    }

    public String getEndDate_S() {
        return endDate_S;
    }

    public void setEndDate_S(String endDate_S) {
        this.endDate_S = endDate_S;
    }

    private String endDate_S;
    @OneToOne
    private Payment payment;
    
//    public Booking() {
//
//    }
    
    public Booking(String startDate, String endDate) {
		super();
		this.startDate_S = startDate;
		this.endDate_S = endDate;
		
		// Generate booking number
		LocalDate lDate = LocalDate.now();
		this.bookingNumber = lDate.toString();
	}
    
    public Booking(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		
		// Generate booking number
		LocalDate lDate = LocalDate.now();
		this.bookingNumber = lDate.toString();
	}

	public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Boolean getCheckInStatus() {
        return checkInStatus;
    }

    public void setCheckInStatus(Boolean checkInStatus) {
        this.checkInStatus = checkInStatus;
    }

    public Boolean getCheckOutStatus() {
        return checkOutStatus;
    }

    public void setCheckOutStatus(Boolean checkOutStatus) {
        this.checkOutStatus = checkOutStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
