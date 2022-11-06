package com.nhnacademy.gw1.parking.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MapCarRepository implements ParkingRepository {
    private static final int PARKING_SPACES = 10;
    public static Map<Integer, LocalDateTime> carMap = new HashMap<>(PARKING_SPACES);

    @Override
    public void saveEnteringCarInfo(Integer carNum, LocalDateTime enteringTime) {
        carMap.put(carNum, enteringTime);
    }

}
