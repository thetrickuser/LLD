package com.tuf.parkinglot.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class Payment {

    private final UUID id;
    private final UUID ticketId;
    private final double amount;
    private final PaymentGateway gateway;
    private PaymentStatus status;

    public Payment(UUID ticketId, double amount, PaymentGateway gateway) {
        this.id = UUID.randomUUID();
        this.ticketId = ticketId;
        this.amount = amount;
        this.gateway = gateway;
        this.status = PaymentStatus.PENDING;
    }

    public void markAsSuccess() {
        this.status = PaymentStatus.SUCCESS;
    }

    public void markAsFailed() {
        this.status = PaymentStatus.FAILED;
    }
}
