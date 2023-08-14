package com.example.trenrezervasyon.service;

import com.example.trenrezervasyon.entities.Wagon;
import com.example.trenrezervasyon.model.ReservationRequest;
import com.example.trenrezervasyon.model.ReservationResponse;
import com.example.trenrezervasyon.model.YerlesimAyrinti;
import com.example.trenrezervasyon.repository.WagonRepository;
import com.example.trenrezervasyon.repository.WagonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private WagonRepository wagonRepository;

    public ReservationResponse checkReservation(ReservationRequest request) {
        List<Wagon> availableWagons = new ArrayList<>();
        List<Wagon> allWagons = wagonRepository.findAll();

        for (Wagon wagon : allWagons) {
            double occupancyRate = (double) wagon.getOccupiedSeats() / wagon.getCapacity();
            if (occupancyRate < 0.7) {
                availableWagons.add(wagon);
            }
        }

        ReservationResponse response = new ReservationResponse();
        if (availableWagons.isEmpty()) {
            response.setRezervasyonYapilabilir(false);
        } else {
            response.setRezervasyonYapilabilir(true);

            List<YerlesimAyrinti> yerlesimAyrintiList = new ArrayList<>();
            int remainingPassengerCount = request.getRezervasyonYapilacakKisiSayisi();

            for (Wagon wagon : availableWagons) {
                int emptySeats = wagon.getCapacity() - wagon.getOccupiedSeats();
                int passengersToPlace = Math.min(emptySeats, remainingPassengerCount);

                YerlesimAyrinti yerlesimAyrinti = new YerlesimAyrinti();
                yerlesimAyrinti.setVagonAdi("Vagon " + wagon.getId());
                yerlesimAyrinti.setKisiSayisi(passengersToPlace);
                yerlesimAyrintiList.add(yerlesimAyrinti);

                remainingPassengerCount -= passengersToPlace;
                if (remainingPassengerCount <= 0) {
                    break;
                }
            }

            response.setYerlesimAyrinti(yerlesimAyrintiList);
        }

        return response;
    }
}
