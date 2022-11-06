package com.nhnacademy.gw1.parking.entity;

import com.nhnacademy.gw1.parking.exception.CarNumOver4DigitsException;

public class Car {
    private final int vehicleNumber;
    private final CarType carType;

    private Car(int vehicleNumber, CarType carType) {
        this.vehicleNumber = vehicleNumber;
        this.carType = carType;
    }

    public static Car makeCar(int vehicleNumber, CarType carType) {
        isCarNumNotOver4Digits(vehicleNumber);
        return new Car(vehicleNumber, carType);
    }

    private static boolean isCarNumNotOver4Digits(int vehicleNumber) { //자동차 번호가 4자리수를 넘지 않았는지 확인
        int carNumberDigits = (int) (Math.log10(vehicleNumber) + 1);
        if (carNumberDigits > 4) {
            throw new CarNumOver4DigitsException(vehicleNumber);
        }
        return true;
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public CarType getCarType() {
        return carType;
    }
}

