package com.rescue.vscube.task;

import com.rescue.vscube.event.CoordinateListConverter;
import com.rescue.vscube.models.Coordinate;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateDTO {

    private String region;

    @Convert(converter = CoordinateListConverter.class)
    private List<Coordinate> coordinates;
}
