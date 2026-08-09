package com.tuf.parkinglot.repository;

import com.tuf.parkinglot.domain.Payment;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentRepository {

    private Map<UUID, Payment> payments = new ConcurrentHashMap<>();
    private Map<UUID, List<UUID>> ticketToPayments = new ConcurrentHashMap<>();

    public Payment save(Payment payment) {
        payments.put(payment.getId(), payment);
        ticketToPayments.computeIfAbsent(payment.getTicketId(), k -> new ArrayList<>())
                .add(payment.getId());
        return payment;
    }

    public Optional<Payment> findById(UUID paymentId) {
        return Optional.ofNullable(payments.get(paymentId));
    }

    public List<Payment> findByTicketId(UUID ticketId) {
        List<UUID> paymentIds = ticketToPayments.get(ticketId);
        if (paymentIds == null) return Collections.emptyList();

        return paymentIds.stream()
                .map(payments::get)
                .filter(Objects::nonNull)
                .toList();
    }

    public List<Payment> findAll() {
        return new ArrayList<>(payments.values());
    }

    public void update(Payment payment) {
        if (payments.containsKey(payment.getId())) {
            payments.put(payment.getId(), payment);
        }
    }

    public void delete(UUID paymentId) {
        Payment removedPayment = payments.remove(paymentId);
        if (removedPayment != null) {
            List<UUID> paymentIds = ticketToPayments.get(removedPayment.getTicketId());
            if (paymentIds != null) {
                paymentIds.remove(paymentId);
            }
        }
    }

    public void clear() {
        payments.clear();
        ticketToPayments.clear();
    }
}
