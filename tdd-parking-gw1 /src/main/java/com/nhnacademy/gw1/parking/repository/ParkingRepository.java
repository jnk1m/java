package com.nhnacademy.gw1.parking.repository;

import java.time.LocalDateTime;

public interface ParkingRepository {

    public void saveEnteringCarInfo(Integer carNum, LocalDateTime enteringTime);


}
