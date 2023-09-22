package com.rescue.vscube.agency;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/agencies")
@RestController
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @GetMapping("/filter")
    public ResponseEntity<List<Agency>> filerAgencies(@RequestParam(name = "name", required = false) String name,
                                                      @RequestParam(name = "description", required = false) String description,
                                                      @RequestParam(name = "location", required = false) String location,
                                                      @RequestParam(name = "start_date", required = false) String startDate,
                                                      @RequestParam(name = "end_date", required = false) String endDate){
        return new ResponseEntity<List<Agency>>(agencyService.filter(name,description,location,startDate,endDate), HttpStatus.OK);
    }
}
