package com.tuf.parkinglot;

import com.tuf.parkinglot.domain.Vehicle;
import com.tuf.parkinglot.domain.VehicleType;
import com.tuf.parkinglot.handler.EntryHandler;
import com.tuf.parkinglot.repository.*;
import com.tuf.parkinglot.service.SlotService;
import com.tuf.parkinglot.service.TicketService;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("info log");
        log.error("error log");
        log.warn("warn log");
        log.debug("debug log");

        // Initialize repositories
        TicketRepository ticketRepository = new TicketRepository();
        ParkingSlotRepository slotRepository = new ParkingSlotRepository();
        FloorRepository floorRepository = new FloorRepository();
        PricingRuleRepository pricingRuleRepository = new PricingRuleRepository();
        PaymentRepository paymentRepository = new PaymentRepository();

        // Initialize services
        TicketService ticketService = new TicketService(ticketRepository);
        SlotService slotService = new SlotService(slotRepository);

        // Initialize handlers
        EntryHandler entryHandler = new EntryHandler(ticketService, slotService);

        log.info("=============Welcome to Parking lot=============");
        Vehicle car = new Vehicle("BR01 26H4576", VehicleType.CAR);
        entryHandler.enterVehicle(car.getLicensePlate(), car.getVehicleType());
    }

    private static void initParkingLot() {

    }


}