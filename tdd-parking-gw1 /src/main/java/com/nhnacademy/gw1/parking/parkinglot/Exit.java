package com.nhnacademy.gw1.parking.parkinglot;

import java.time.LocalDateTime;

public class Exit {

    public LocalDateTime getLeavingCarTime() {
        LocalDateTime leavingTime = LocalDateTime.now();
        return LocalDateTime.of(leavingTime.getYear(),
                leavingTime.getMonth(),
                leavingTime.getDayOfMonth(),
                leavingTime.getHour(),
                leavingTime.getMinute(),
                leavingTime.getSecond());
    }


}
