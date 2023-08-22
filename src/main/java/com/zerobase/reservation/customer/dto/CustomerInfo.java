package com.zerobase.reservation.customer.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfo {

    private int customerId;
    private int restaurantId;
    private boolean reservationYn;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
}
