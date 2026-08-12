package com.SwimcoachPlatform.coach.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "pools")
@AllArgsConstructor
@NoArgsConstructor
public class Pool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String city;

    @Getter @Setter
    private String postalCode;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private String email;

    @Column(length = 1000)
    @Getter @Setter
    private String description;

    @Getter @Setter
    private boolean active;


    @ManyToMany(mappedBy = "pools")
    @Getter @Setter
    private List<Coach> coaches = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "coach_pool",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "pool_id")
    )
    @Getter @Setter
    private List<Pool> pools = new ArrayList<>();






}
