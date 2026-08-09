package com.tuf.parkinglot.repository;

import com.tuf.parkinglot.domain.Floor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FloorRepository {
    private Map<Integer, Floor> floors = new ConcurrentHashMap<>();

    public Floor save(Floor floor) {
        floors.put(floor.getFloorNumber(), floor);
        return floor;
    }

    public Optional<Floor> findByNumber(int floorNumber) {
        return Optional.ofNullable(floors.get(floorNumber));
    }

    public List<Floor> findAll() {
        return new ArrayList<>(floors.values());
    }

    public void delete(int floorNumber) {
        floors.remove(floorNumber);
    }

    public void clear() {
        floors.clear();
    }
}
