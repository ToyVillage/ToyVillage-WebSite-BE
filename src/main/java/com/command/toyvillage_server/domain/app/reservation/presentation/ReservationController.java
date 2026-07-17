package com.command.toyvillage_server.domain.app.reservation.presentation;

import com.command.toyvillage_server.domain.app.reservation.service.ReservationQueryListService;
import com.command.toyvillage_server.domain.app.reservation.service.ReservationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Reservation")
public class ReservationController {
    private final ReservationQueryService reservationQueryService;
    private final ReservationQueryListService reservationQueryListService;

    @GetMapping
}