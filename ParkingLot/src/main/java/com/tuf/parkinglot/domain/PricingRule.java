package com.tuf.parkinglot.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class PricingRule {

    private final UUID id;
    private final VehicleType vehicleType;
    private double ratePerHour;
    private double flatRate;

    public PricingRule(VehicleType vehicleType, double ratePerHour, double flatRate) {
        this.id = UUID.randomUUID();
        this.vehicleType = vehicleType;
        this.ratePerHour = ratePerHour;
        this.flatRate = flatRate;
    }

    public void updateFlatRate(double flatRate) {
        this.flatRate = flatRate;
    }

    public void updateHourlyRate(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public void updateRates(double flatRate, double ratePerHour) {
        updateFlatRate(flatRate);
        updateHourlyRate(ratePerHour);
    }
}
