package mum.asd.domain;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HotelUser {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
//    private long address_id;
    @Enumerated
    private Gender gender;
    @OneToOne(fetch = FetchType.EAGER)
    private Address address;
    private String email;
    private String credit;
    @OneToOne(fetch = FetchType.EAGER)
    private Payment payment;
    @OneToMany(fetch = FetchType.EAGER)
    @IndexColumn(name="id")
    private List<Booking> bookingList = new ArrayList<>();
    @Enumerated
    private UserType userType;
//    @OneToMany
    @OneToMany(fetch = FetchType.EAGER)
    @IndexColumn(name="id")
    private List<Promotion> promotions;

    public HotelUser() {
        promotions = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
//    public long getAddressID() {
//        return address_id;
//    }
//
//    public void setAddressID(long id) {
//        this.address_id = id;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }
    
    public void addBookingToBookingList(Booking booking) {
    	this.bookingList.add(booking);
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
}
