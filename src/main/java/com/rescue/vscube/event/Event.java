package com.rescue.vscube.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.models.Coordinate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Column(name="created_on")
    private Timestamp createdOn;

    private String description;

    @ElementCollection
    @Convert(converter = CoordinateListConverter.class)
    private List<Coordinate> coordinates;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Agency createdBy;

    @PrePersist
    public void setDefaultValues() {
        if (createdOn == null) {
            createdOn = new Timestamp(System.currentTimeMillis());
        }
    }
}
