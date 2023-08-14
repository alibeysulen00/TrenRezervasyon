package com.example.trenrezervasyon.controller;

import com.example.trenrezervasyon.model.ReservationRequest;
import com.example.trenrezervasyon.model.ReservationResponse;
import com.example.trenrezervasyon.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/request")
    public ResponseEntity<ReservationResponse> requestReservation(@RequestBody ReservationRequest request) {
        ReservationResponse response = reservationService.checkReservation(request);
        return ResponseEntity.ok(response);
    }
}
