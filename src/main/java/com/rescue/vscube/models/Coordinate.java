package com.rescue.vscube.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Coordinate {
    private float longitude;
    private float latitude;

    @JsonCreator
    public Coordinate(@JsonProperty("latitude") float latitude, @JsonProperty("longitude") float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
