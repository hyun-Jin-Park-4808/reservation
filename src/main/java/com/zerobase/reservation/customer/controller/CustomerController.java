package com.zerobase.reservation.customer.controller;

import com.zerobase.reservation.customer.dto.*;
import com.zerobase.reservation.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customer/register") // 식당 예약
    public CustomerReservationDto.Response register(@RequestBody CustomerReservationDto.Request request) {
        return CustomerReservationDto.Response.from(
                customerService.register(
                        request.getRestaurantId(),
                        request.getRestaurantName(),
                        request.getReservationDate(),
                        request.getReservationTime(),
                        request.getPhone()
                )
        );
    }

    @PostMapping("/customer/visit") // 방문 체크
    public CustomerVisitedCheck.Response visit(@RequestBody CustomerVisitedCheck.Request request) {
        return CustomerVisitedCheck.Response.from(
                customerService.visit(
                        request.getRestaurantId(),
                        request.getRestaurantName(),
                        request.getPhone(),
                        request.getCustomerId()
                )
        );
    }

    @PostMapping("/customer/review") // 리뷰 작성
    public CustomerReviewDto.Response review (@RequestBody CustomerReviewDto.Request request) {
        return CustomerReviewDto.Response.from(
                customerService.review(
                        request.getRestaurantId(),
                        request.getCustomerId(),
                        request.getStarRating(),
                        request.getReview()
                )
        );
    }

    @GetMapping("/customer/list") // 예약 리스트 조회
    public List<CustomerInfo> getCustomersByDate (@RequestBody CustomerListDto.Request request) {

        return customerService.getCustomersByDate(
                request.getRestaurantId(), request.getDate())
                .stream().map(Customer
                        -> CustomerInfo.builder()
                        .customerId(Customer.getCustomerId())
                        .restaurantId(Customer.getRestaurantId())
                        .reservationYn(Customer.isReservationYn())
                        .reservationDate(Customer.getReservationDate())
                        .reservationTime(Customer.getReservationTime())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/customer/reservation-approval") // 예약 승인/거절
    public CustomerReservationApproval.Response reservationApproval
            (@RequestBody CustomerReservationApproval.Request request) {
        return CustomerReservationApproval.Response.from(
                customerService.reservationCheck(
                        request.isReservationYn(),
                        request.getCustomerId()
                )
        );
    }
}
