package com.example.hoteltransilvania.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

//Anotaciones
@Entity
@Table(name = "Room")
@Getter
@Setter
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id de la habitacion

    private String roomType; //Tipo de habitacion

    private BigDecimal roomPrice; //Precio de la habitacion

    private boolean isBooked = false; //Si esta reservado o no, si es falso esta disponible para reserva

    @Lob
    private Blob photo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)//si se realiza alguna operación en la entidad principal (por ej, guardarla en la base de datos), las operaciones correspondientes se realizarán automáticamente en las entidades relacionadas (en este caso, las instancias de BookedRoom).
    private List<BookedRoom> bookings;
    // lista de objetos BookedRoom llamada bookings.

    //establece una relación uno a muchos entre la entidad principal y la entidad BookedRoom. Las reservas de habitaciones se cargarán de forma lenta y cualquier operación realizada en la entidad principal se propagará a las reservas de habitaciones asociadas.




    public Room() {
        this.bookings = new ArrayList<>();
    }

    public void addBooking(BookedRoom booking){
        if (booking == null){
            bookings = new ArrayList<>();
        }
        booking.add(booking);
        booking.setRoom(this);
        isBooked = true;
        String bookingCode = RandomStringUtils.randomNumeric(10);
        booking.setBookingConfirmationCode(bookingCode);

    }
}
