package com.zerobase.reservation.customer.service;

import com.zerobase.reservation.customer.dto.CustomerDto;
import com.zerobase.reservation.customer.entity.Customer;
import com.zerobase.reservation.customer.repository.CustomerRepository;
import com.zerobase.reservation.exception.impl.*;
import com.zerobase.reservation.member.entity.Member;
import com.zerobase.reservation.member.repository.MemberRepository;
import com.zerobase.reservation.restaurant.dto.ReviewDto;
import com.zerobase.reservation.restaurant.entity.Restaurant;
import com.zerobase.reservation.restaurant.entity.Review;
import com.zerobase.reservation.restaurant.repository.RestaurantRepository;
import com.zerobase.reservation.restaurant.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public CustomerDto register(int restaurantId, String restaurantName,
                                LocalDate reservationDate, LocalTime reservationTime,
                                String phone) {
        Optional<Member> optionalMember = memberRepository.findByPhone(phone);
        if (optionalMember.isEmpty()) {
            throw new NoMemberException();
        }

        return CustomerDto.fromEntity(
                customerRepository.save(Customer.builder()
                        .restaurantName(restaurantName)
                        .restaurantId(restaurantId)
                        .reservationDate(reservationDate)
                        .reservationTime(reservationTime)
                        .phone(phone)
                        .build()
                )
        );
    }

    @Override
    public CustomerDto visit(int restaurantId, String restaurantName, String phone, int customerId) {
        Optional<Customer> optionalCustomer
                = customerRepository.findByCustomerId(customerId);

        if (optionalCustomer.isEmpty()) {
            throw new NoMemberException();
        }

        if (!optionalCustomer.get().isReservationYn()) {
            throw new DeniedException();
        }

        if (optionalCustomer.get().isVisitedYn()) {
            throw new AlreadyVisitedException();
        }

        LocalDate today = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        LocalTime time = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("kk:mm")));

        if (!optionalCustomer.get().getReservationDate().equals(today)) {
            throw new NotTodayException();
        }

        if (!time.isBefore(optionalCustomer.get().getReservationTime().minusMinutes(10))) {
            throw new TimeOverException();
        }

        return CustomerDto.fromEntity(
                customerRepository.save(Customer.builder()
                        .customerId(customerId)
                        .restaurantId(restaurantId)
                        .restaurantName(restaurantName)
                        .phone(phone)
                        .reservationYn(optionalCustomer.get().isReservationYn())
                        .reservationDate(optionalCustomer.get().getReservationDate())
                        .reservationTime(optionalCustomer.get().getReservationTime())
                        .visitedYn(true)
                        .build()
                )
        );
    }

    @Override
    public CustomerDto review(int restaurantId, int customerId, String starRating, String review) {
        Optional<Customer> optionalCustomer
                = customerRepository.findByCustomerId(customerId);

        if (!optionalCustomer.get().isVisitedYn()) {
            throw new NotVisitedException();
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        reviewRepository.save(Review.builder() // review 저장
                .starRating(starRating)
                .review(review)
                .restaurant(restaurant)
                .build()
        );

        // review에서 리뷰 업데이트된 restaurant에 해당하는 리뷰 개수 불러오기
        int count = reviewRepository.countByRestaurant(restaurant);

        String starRatings = restaurant.getStarRatings();
        double starRatingsDouble = 0;
        if (starRatings.equals("")) {
            starRatingsDouble = 0;
        } else {
            starRatingsDouble = Double.valueOf(starRatings);
        }
        starRatings = String.valueOf((starRatingsDouble + Double.valueOf(starRating)) / (double) count); // 별점 평균 저장

        restaurantRepository.save(Restaurant.builder()
                        .id(restaurantId)
                .userId(restaurant.getUserId())
                .restaurantName(restaurant.getRestaurantName())
                .locationX(restaurant.getLocationX())
                .locationY(restaurant.getLocationY())
                .detail(restaurant.getDetail())
                .starRatings(starRatings)
                .reviews(restaurant.getReviews())
                .build());

        return CustomerDto.fromEntity(
                customerRepository.save(Customer.builder()
                        .customerId(customerId)
                        .restaurantId(restaurantId)
                        .restaurantName(optionalCustomer.get().getRestaurantName())
                        .phone(optionalCustomer.get().getPhone())
                        .reservationYn(optionalCustomer.get().isReservationYn())
                        .reservationDate(optionalCustomer.get().getReservationDate())
                        .reservationTime(optionalCustomer.get().getReservationTime())
                        .visitedYn(true)
                        .starRating(starRating)
                        .review(review)
                        .build()
                )
        );
    }

    @Override
    public List<Customer> getCustomersByDate(int restaurantId, LocalDate date) {

        return customerRepository.findByRestaurantIdAndReservationDate(restaurantId, date);
    }

    @Override
    public CustomerDto reservationCheck(boolean reservationYn, int customerId) {

        Optional<Customer> optionalCustomer
                = customerRepository.findByCustomerId(customerId);

        return  CustomerDto.fromEntity(
                customerRepository.save(Customer.builder()
                        .customerId(customerId)
                        .restaurantId(optionalCustomer.get().getRestaurantId())
                        .restaurantName(optionalCustomer.get().getRestaurantName())
                        .phone(optionalCustomer.get().getPhone())
                        .reservationYn(reservationYn)
                        .reservationDate(optionalCustomer.get().getReservationDate())
                        .reservationTime(optionalCustomer.get().getReservationTime())
                        .visitedYn(optionalCustomer.get().isVisitedYn())
                        .build()
                )
        );
    }
}
