package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.CarType;
import com.nhnacademy.gw1.parking.entity.User;
import com.nhnacademy.gw1.parking.parkinglot.Exit;
import com.nhnacademy.gw1.parking.repository.MapCarRepository;
import com.nhnacademy.gw1.parking.repository.PaycoMember;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingService {


    public long calculateParkedSecond(Car car) {
        LocalDateTime enteringTime = MapCarRepository.carMap.get(car.getVehicleNumber()); //진입 시간 가져오기
        Exit exit = new Exit();
        LocalDateTime leavingTime = exit.getLeavingCarTime(); //진출 시간 가져오기

        return ChronoUnit.SECONDS.between(enteringTime, leavingTime); //진입 - 진출 시간을 초 단위로 계산하여 반환
    }

    public int calculateParkingFee(Car car, User user, long parkedSeconds) {
        int fee = 0; //초기 요금 0원

        final int DAILY_RATE = 86400; //24시간까지
        final int OVER_30_END_60 = 3600; // 30분 초과 60분 이하
        final int FREE_FIRST_30_MINS = 1800; //최초 30분
        final int ADDITIONAL_TEN_MINS = 600; //추가 10분

        if (parkedSeconds == DAILY_RATE) { // 24시간 주차했다면, 86400까지
            fee += 15000;
        }

        if (parkedSeconds <= FREE_FIRST_30_MINS) { //1800까지
            return fee;
        }


        if (parkedSeconds <= OVER_30_END_60) { //3600까지
            fee += 1000;
            parkedSeconds -= OVER_30_END_60;
        }


        while (parkedSeconds != 0) { //남은 10분 계산, 600까지
            if (parkedSeconds < ADDITIONAL_TEN_MINS) {
                break;
            }
            fee += 500;
            parkedSeconds -= ADDITIONAL_TEN_MINS;

        }

        if (isCompactCar(car)) { //경차라면 50프로 할인
            fee = (int) (fee * 0.5);
        }

        if (isPaycoMember(user)) { //페이코 멤버라면 10프로 할인
            fee = (int) (fee * 0.9);
        }

        return fee;
    }

    private boolean isCompactCar(Car car) {
        if (car.getCarType() != CarType.COMPACT_CAR) {
            return false;
        }
        return true;
    }

    private boolean isPaycoMember(User user) {
        if (PaycoMember.paycoMember.get(user.getUserId()) == null) {
            return false;
        }
        return true;
    }


}
