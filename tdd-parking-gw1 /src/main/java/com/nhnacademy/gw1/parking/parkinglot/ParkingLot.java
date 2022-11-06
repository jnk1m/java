package com.nhnacademy.gw1.parking.parkinglot;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.CarType;
import com.nhnacademy.gw1.parking.exception.NoVacantException;
import com.nhnacademy.gw1.parking.exception.TrucksRegulationException;
import com.nhnacademy.gw1.parking.repository.MapCarRepository;
import com.nhnacademy.gw1.parking.repository.ParkingRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int TOTAL_PARKING_SPACES = 10;
    private final List<Car> parkingSpaces = new ArrayList<>(TOTAL_PARKING_SPACES);

    ParkingRepository parkingRepository = new MapCarRepository();
    Entrance entrance = new Entrance();

    public List<Car> getParkingSpaces() {
        return parkingSpaces;
    }

    public void enteringParkingLot(Car car) {
        isVacantSpaceLeft(); //주차장에 빈 공간이 있는지 확인
        isTruck(car); //들어오려는 차가 트럭인지 확인

        int carNumber = entrance.scanCarNumber(car);
        LocalDateTime entryTime = entrance.scanEntryTime();
        parkingRepository.saveEnteringCarInfo(carNumber, entryTime); //진입한 차의 번호와 진입 시간을 레포지토리에 저장

        parkingCar(car);
    }

    private void parkingCar(Car car) {
        parkingSpaces.add(car);
    } //주차

    private boolean isVacantSpaceLeft() {
        if (parkingSpaces.size() == TOTAL_PARKING_SPACES) {
            throw new NoVacantException();
        }
        return true;
    }

    private boolean isTruck(Car car) {
        if (car.getCarType() == CarType.TRUCK) {
            throw new TrucksRegulationException();
        }
        return true;
    }
}
