package com.tuf.parkinglot.repository;

import com.tuf.parkinglot.domain.PricingRule;
import com.tuf.parkinglot.domain.VehicleType;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PricingRuleRepository {
    private Map<VehicleType, PricingRule> rules = new ConcurrentHashMap<>();

    public PricingRule save(PricingRule rule) {
        rules.put(rule.getVehicleType(), rule);
        return rule;
    }

    public Optional<PricingRule> findByVehicleType(VehicleType vehicleType) {
        return Optional.ofNullable(rules.get(vehicleType));
    }


}
