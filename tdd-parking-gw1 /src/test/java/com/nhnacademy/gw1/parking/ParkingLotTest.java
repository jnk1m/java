package com.nhnacademy.gw1.parking;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.CarType;
import com.nhnacademy.gw1.parking.exception.NoVacantException;
import com.nhnacademy.gw1.parking.exception.TrucksRegulationException;
import com.nhnacademy.gw1.parking.parkinglot.Entrance;
import com.nhnacademy.gw1.parking.parkinglot.ParkingLot;
import com.nhnacademy.gw1.parking.repository.MapCarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParkingLotTest {

    ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
    }

    /* 헬퍼 함수입니다*/
    private Car make_car_and_park() {
        Car car = Car.makeCar(1234, CarType.COMPACT_CAR);
        parkingLot.enteringParkingLot(car);
        return car;
    }

    @Test
    @DisplayName("자동차 한대가 주차되는지 테스트")
    void parked_a_car_success() {
        make_car_and_park();
        assertThat(parkingLot.getParkingSpaces().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("주차칸이 전부 찼을 때 에러를 던지는지 테스트")
    void no_vacant_left_test() {
        for (int i = 0; i < 9; i++) {
            make_car_and_park();
        }

        Car car = Car.makeCar(1235, CarType.COMPACT_CAR);
        parkingLot.enteringParkingLot(car);

        assertThatThrownBy(() -> parkingLot.enteringParkingLot(car))
                .isInstanceOf(NoVacantException.class)
                .hasMessageContaining("There is no empty space in parking lot");
    }

    @Test
    @DisplayName("트럭 주차불가 테스트")
    void no_truck_enter_exception() {
        Car truck = Car.makeCar(1234, CarType.TRUCK);
        assertThatThrownBy(() -> parkingLot.enteringParkingLot(truck))
                .isInstanceOf(TrucksRegulationException.class)
                .hasMessageContaining("Trucks do not enter");
    }

    @Test
    @DisplayName("차량 진입 시 차량의 정보를 스캔하는지 확인")
    void scan_car_num_time() {
        Car car = make_car_and_park();
        Entrance entrance = new Entrance();

        assertThat(entrance.scanCarNumber(car)).isEqualTo(car.getVehicleNumber());
        assertThat(entrance.scanEntryTime()).isEqualTo(MapCarRepository.carMap.get(car.getVehicleNumber()));
    }

    @Test
    @DisplayName("주차한 차량의 정보가 레포지토리에 들어가는지 확인")
    void car_num_time_into_repo_success() {
        make_car_and_park();
        assertThat(MapCarRepository.carMap.size()).isEqualTo(1);
    }

}
