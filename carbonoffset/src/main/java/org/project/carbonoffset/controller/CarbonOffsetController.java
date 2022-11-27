package org.project.carbonoffset.controller;


import org.project.carbonoffset.exception.InvalidInputException;
import org.project.carbonoffset.model.CarbonOffset;
import org.project.carbonoffset.model.response.CarbonOffsetResponse;
import org.project.carbonoffset.service.CarbonOffsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/carbon")
public class CarbonOffsetController {

    @Autowired
    CarbonOffsetService service;

    @PostMapping("/offset")
    public ResponseEntity<CarbonOffsetResponse> updateProjectOffsetDetails(@RequestBody CarbonOffset carbonOffset) throws InvalidInputException {
        CarbonOffsetResponse createdCarbonOffset = service.updateProjectOffsetDetails(carbonOffset);
        return new ResponseEntity<>(createdCarbonOffset, HttpStatus.CREATED);
    }

    @GetMapping("/offset")
    public ResponseEntity<List<CarbonOffsetResponse>> getProjectOffsetDetails(){
        List<CarbonOffsetResponse> sortedCarbonOffsetResponse= service.getTop3Projects();
        return new ResponseEntity<>(sortedCarbonOffsetResponse, HttpStatus.CREATED);
    }
}
