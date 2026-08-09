package com.tuf.parkinglot.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
public class Reciept {

    private final UUID id;
    private final UUID ticketId;
    private final LocalDateTime exitTime;
    private final Double totalFee;
    private PaymentStatus paymentStatus;

    public Reciept(UUID id, UUID ticketId, LocalDateTime exitTime, Double totalFee) {
        this.id = id;
        this.ticketId = ticketId;
        this.exitTime = exitTime;
        this.totalFee = totalFee;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void markAsPaid() {
        this.paymentStatus = PaymentStatus.SUCCESS;
    }

    public void markAsFailed() {
        this.paymentStatus = PaymentStatus.FAILED;
    }

}
