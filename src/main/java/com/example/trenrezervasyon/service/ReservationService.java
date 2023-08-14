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
        ReservationResponse response = new ReservationResponse();
        List<Wagon> availableWagons = new ArrayList<>();
        List<Wagon> allWagons = wagonRepository.findAll();
        int kisiSayisi = request.getRezervasyonYapilacakKisiSayisi();

        if (request.isKisilerFarkliVagonlaraYerlestirilebilir()) {
            for (Wagon wagon : allWagons) {
                double occupancyRate = (double) wagon.getOccupiedSeats() / wagon.getCapacity();

                if (occupancyRate < 0.7) {
                    availableWagons.add(wagon);
                }
            }


            response.setRezervasyonYapilabilir(true);

            List<YerlesimAyrinti> yerlesimAyrintiList = new ArrayList<>();
            int remainingPassengerCount = kisiSayisi;

            for (Wagon wagon : availableWagons) {
                int passengersToPlace = 0;
                int emptySeats = wagon.getCapacity() - wagon.getOccupiedSeats();
                while (remainingPassengerCount >= 0) {
                    // Calculate empty seats in wagon (capacity - occupiedSeats)
                    emptySeats = emptySeats - 1;
                    boolean isWagonAvailable = isCapacityAvailable(emptySeats, wagon.getCapacity());

                    if (isWagonAvailable && remainingPassengerCount > 0) {
                        passengersToPlace = passengersToPlace + 1;
                    }
                    else {
                        YerlesimAyrinti yerlesimAyrinti = new YerlesimAyrinti();
                        yerlesimAyrinti.setVagonAdi("Vagon " + wagon.getId());
                        yerlesimAyrinti.setKisiSayisi(passengersToPlace);
                        yerlesimAyrintiList.add(yerlesimAyrinti);
                        break;
                    }
                    remainingPassengerCount -= 1;
                }

            }
            response.setYerlesimAyrinti(yerlesimAyrintiList);
        } else {
            ////////////////////////////
            response.setRezervasyonYapilabilir(false);
        }

        return response;
    }

    public boolean isCapacityAvailable( int occupiedSeats, int capacity){
        double occupancyRate = (double) occupiedSeats / capacity;
        return occupancyRate >= 0.3;
    }
}