package com.zerobase.reservation.customer.repository;

import com.zerobase.reservation.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository <Customer, Integer> {
    Optional<Customer> findByCustomerId(int customerId);

    List<Customer> findByRestaurantIdAndReservationDate(int restaurantId, LocalDate date);
}
