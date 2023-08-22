package com.zerobase.reservation.customer.service;

import com.zerobase.reservation.customer.dto.CustomerDto;
import com.zerobase.reservation.customer.entity.Customer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CustomerService {

    CustomerDto register(int restaurantId, String restaurantName,
                         LocalDate reservationDate, LocalTime reservationTime,
                         String phone);

    CustomerDto visit(int restaurantId, String restaurantName,
                      String phone, int customerId);

    CustomerDto review(int restaurantId, int customerId, String starRating, String review);

    List<Customer> getCustomersByDate(int restaurantId, LocalDate date);

    CustomerDto reservationCheck(boolean reservationYn, int customerId);
}
