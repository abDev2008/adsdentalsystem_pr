package com.abletocode.adsdentalsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "surgeries")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Surgery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "surgery", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
}
