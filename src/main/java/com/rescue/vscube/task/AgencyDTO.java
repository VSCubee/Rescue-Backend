package com.rescue.vscube.task;

import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.models.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgencyDTO {
    private Agency agency;

    private String region;


}
