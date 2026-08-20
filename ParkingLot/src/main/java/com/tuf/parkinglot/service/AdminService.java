package com.tuf.parkinglot.service;

import com.tuf.parkinglot.domain.Floor;
import com.tuf.parkinglot.repository.FloorRepository;
import com.tuf.parkinglot.repository.ParkingSlotRepository;
import com.tuf.parkinglot.repository.PricingRuleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminService {

    private final FloorRepository floorRepository;
    private final ParkingSlotRepository slotRepository;
    private final PricingRuleRepository pricingRuleRepository;

    public AdminService(FloorRepository floorRepository, ParkingSlotRepository slotRepository, PricingRuleRepository pricingRuleRepository) {
        this.floorRepository = floorRepository;
        this.slotRepository = slotRepository;
        this.pricingRuleRepository = pricingRuleRepository;
        log.info("Admin service initialized");
    }

    public void initializeParkingLot() {
        log.info("Initializing parking lot with defaults");

        // 3 floors
        for (int i=0; i<3; i++) {
            Floor floor = new Floor(i);
            floorRepository.save(floor);
        }

        // add slots to floor 0
        // add slots to floor 1
        // add slots to floor 2

        // initialize default pricing rules
    }

    private void addFloor(int floorNumber) {
        log.info("Adding floor with floor number: {}", floorNumber);
        if (floorRepository)
    }
}
