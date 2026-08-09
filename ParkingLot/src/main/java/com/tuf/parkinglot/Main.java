package com.tuf.parkinglot;

import com.tuf.parkinglot.domain.VehicleType;
import com.tuf.parkinglot.handler.EntryHandler;
import com.tuf.parkinglot.repository.*;
import com.tuf.parkinglot.service.SlotService;
import com.tuf.parkinglot.service.TicketService;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String HR = "-----------------------------------------------------";

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
        boolean running = true;

        while (running) {
            printMenu();
            log.info("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleEntry(entryHandler);
                    break;
//                case "2":
//                    handleVehicleExit();
//                    break;
//                case "3":
//                    handleAdmin();
//                    break;
                case "4":
                    log.info("Exiting the system. Goodbye");
                    running = false;
                    break;
                default:
                    log.error("Incorrect choice. Please enter again: ");
            }
        }
    }

    private static void handleEntry(EntryHandler entryHandler) {
        log.info("Enter vehicle license plate number: ");
        String licensePlate = scanner.nextLine().trim();
        VehicleType type = null;
        while (true) {
            log.info("""
                    Choose vehicle type.
                    1. Car
                    2. Bike
                    3. Truck
                    4. EV
                    """);
            String vehicleType = scanner.nextLine().trim();
            type = switch (vehicleType) {
                case "1" -> VehicleType.CAR;
                case "2" -> VehicleType.BIKE;
                case "3" -> VehicleType.TRUCK;
                case "4" -> VehicleType.EV;
                default -> null;
            };

            if (type != null) break;
            else log.warn("Please enter correct choice.");
        }

        entryHandler.enterVehicle(licensePlate, type);
    }

//    private static void initParkingLot() {
//
//    }

    private static void printMenu() {
        log.info(HR);
        log.info("1. Generate ticket");
        log.info("2. Generate receipt");
        log.info("3. Admin control");
        log.info("4. Exit");
        log.info(HR);
    }


}