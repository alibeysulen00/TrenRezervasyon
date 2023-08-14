package com.example.trenrezervasyon.model;

import com.example.trenrezervasyon.entities.Wagon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservationRequest {
    private List<Wagon> wagons;
    private int rezervasyonYapilacakKisiSayisi;
    private boolean kisilerFarkliVagonlaraYerlestirilebilir;
}
