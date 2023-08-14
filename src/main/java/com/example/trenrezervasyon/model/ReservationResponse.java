package com.example.trenrezervasyon.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservationResponse {
    private boolean rezervasyonYapilabilir;
    private List<YerlesimAyrinti> yerlesimAyrinti;
}
