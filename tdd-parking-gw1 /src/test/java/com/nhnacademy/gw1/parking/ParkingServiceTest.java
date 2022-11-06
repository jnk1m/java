package com.nhnacademy.gw1.parking;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.CarType;
import com.nhnacademy.gw1.parking.entity.User;
import com.nhnacademy.gw1.parking.exception.NotEnoughMoneyException;
import com.nhnacademy.gw1.parking.parkinglot.Exit;
import com.nhnacademy.gw1.parking.parkinglot.ParkingLot;
import com.nhnacademy.gw1.parking.repository.MapCarRepository;
import com.nhnacademy.gw1.parking.repository.PaycoMember;
import com.nhnacademy.gw1.parking.service.ParkingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class ParkingServiceTest {
    ParkingService parkingService;
    ParkingLot parkingLot;
    Exit exit;
    User user;

    @BeforeEach
    void setUp() {
        parkingService = new ParkingService();
        parkingLot = new ParkingLot();
        exit = new Exit();
        user = new User(1234, 1000);
    }

    /* 헬퍼 함수입니다*/
    private Car make_car_and_park() {
        Car car = Car.makeCar(1234, CarType.MIDSIZE_CAR);
        parkingLot.enteringParkingLot(car);
        return car;
    }

    @Test
    @DisplayName("들어온 시간과 나가는 시간 계산되는지 테스트")
    void calculate_enter_leave_second() throws InterruptedException {
        Car car = make_car_and_park();

        LocalDateTime enteringTime = MapCarRepository.carMap.get(car.getVehicleNumber());
        Thread.sleep(1000);
        LocalDateTime leavingTime = exit.getLeavingCarTime();

        long parkedTime = ChronoUnit.SECONDS.between(enteringTime, leavingTime);

        ParkingService parkingService = new ParkingService();

        assertThat(parkingService.calculateParkedSecond(car)).isEqualTo(parkedTime);

    }


    @ParameterizedTest
    @ValueSource(ints = {1801, 3000, 3660, 21600})
    @DisplayName("요금 계산 테스트")
    void calculate_fee_success(int seconds) {
        Car car = make_car_and_park();

        int calculateParkingFee = parkingService.calculateParkingFee(car, user, seconds);

        log.debug(String.valueOf(calculateParkingFee));
        assertThat(calculateParkingFee).isNotNull();
    }

    @Test
    @DisplayName("경차 할인 테스트")
    void compact_car_discount_test() {
        Car car = Car.makeCar(1234, CarType.COMPACT_CAR);
        parkingLot.enteringParkingLot(car);

        int calculateParkingFee = parkingService.calculateParkingFee(car, user, 3200);

        assertThat(calculateParkingFee).isEqualTo(500);
    }

    @Test
    @DisplayName("잔액 부족 요금 계산 실패")
    void payment_not_enough_balance_exception() {
        Car car = make_car_and_park();

        int calculateParkingFee = parkingService.calculateParkingFee(car, user, 4200);

        assertThatThrownBy(() -> user.payFee(calculateParkingFee))
                .isInstanceOf(NotEnoughMoneyException.class)
                .hasMessageContaining("Please check your balance. Required balance is ");
    }

    @Test
    @DisplayName("페이코 비회원 요금 계산 성공 테스트")
    void no_payco_payment_success() {
        Car car = make_car_and_park();
        User noPaycoMember = new User(1234, 10000);

        int calculateParkingFee = parkingService.calculateParkingFee(car, noPaycoMember, 4200);

        assertThat(noPaycoMember.payFee(calculateParkingFee)).isTrue();
    }

    @Test
    @DisplayName("페이코 회원 요금 할인 테스트")
    @Order(6)
    void payco_discount_success() {
        Car car = make_car_and_park();

        PaycoMember.paycoMember.put(user.getUserId(), user);

        int calculateParkingFee = parkingService.calculateParkingFee(car, user, 4200);

        assertThat(calculateParkingFee).isEqualTo(3150);

    }

    @Test
    @DisplayName("경차 + 페이코 회원 할인 테스트")
    @Order(7)
    void payco_member_compact_car_discount() {
        Car car = Car.makeCar(1234, CarType.COMPACT_CAR);
        parkingLot.enteringParkingLot(car);

        PaycoMember.paycoMember.put(user.getUserId(), user);

        int calculateParkingFee = parkingService.calculateParkingFee(car, user, 4200);

        assertThat(calculateParkingFee).isEqualTo(1575);
    }

}
