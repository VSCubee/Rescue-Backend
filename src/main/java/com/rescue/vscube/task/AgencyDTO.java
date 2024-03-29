package com.rescue.vscube.task;

import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.models.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgencyDTO {
    private Agency agency;

    private String region;

    private List<Coordinate> location;
}
