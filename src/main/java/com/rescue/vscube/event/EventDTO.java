package com.rescue.vscube.event;

import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.models.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private Long id;

    private String name;

    private Timestamp createdOn;

    private String description;

    private List<Coordinate> coordinates;

    private Long createdBy;

    private String region;

    private List<Agency> agencies;

}
