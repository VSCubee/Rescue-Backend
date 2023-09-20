package com.rescue.vscube.agency;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "agency")
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    @Size(max = 100)
    private String name;

    @Column(nullable = false)
    @Size(max = 100)
    private String login;

    @Column(nullable = false)
    @Size(max = 100)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String description;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    @Column(name = "registered_location", nullable = false)
    private String registeredLocation;

    @Column(name = "phone")
    private String phone;
}
