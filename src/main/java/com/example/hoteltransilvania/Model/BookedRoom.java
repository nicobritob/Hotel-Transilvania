package com.example.hoteltransilvania.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "BookedRoom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "checkIn")//Ingreso a la habitacion
    private LocalDate checkInDate;

    @Column(name = "checkOut")//Salida de la habitacion
    private LocalDate checkOutDate;

    @Column(name = "guestFullName")//Nombre completo del huesped
    private String guestFullName;

    @Column(name = "guestEmail")//email huesped
    private String guestEmail;

    @Column(name = "adults")//Cantidad de adultos
    private int NumOfAdults;

    @Column(name = "children")//Cantidad de ninos
    private int NumOfChildren;

    @Column(name = "totalGuest")//Total invitados, suma de adultos y ninos
    private int totalNumOfGuest;

    @Column(name = "confirmation_Code")//Codigo de confirmacion
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;








    public void calculateTotalNumberOfGuest(){

        this.totalNumOfGuest = this.NumOfAdults + NumOfChildren;
    }

    public void setNumOfAdults(int numOfAdults) {
        NumOfAdults = numOfAdults;
        calculateTotalNumberOfGuest();
    }

    public void setNumOfChildren(int numOfChildren) {
        NumOfChildren = numOfChildren;
        calculateTotalNumberOfGuest();
    }

    public BookedRoom(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }





}
