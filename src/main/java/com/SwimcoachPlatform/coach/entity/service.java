package com.SwimcoachPlatform.coach.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "services")
@AllArgsConstructor
@NoArgsConstructor

public class service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String price;
    @Getter @Setter
    private String duration;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private coach coach;


   /* @OneToMany(mappedBy = "service")
    private List<Booking> bookings = new ArrayList<>();

    */


    //private Coach coach;


}
