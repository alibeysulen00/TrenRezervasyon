package com.example.trenrezervasyon.model;

import com.example.trenrezervasyon.entities.Wagon;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {
    private Wagon wagon;
    private int rezervasyonYapilacakKisiSayisi;
    private boolean kisilerFarkliVagonlaraYerlestirilebilir;
}
