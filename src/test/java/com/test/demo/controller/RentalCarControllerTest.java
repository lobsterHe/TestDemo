package com.test.demo.controller;

import com.test.demo.entity.RentalOrder;
import com.test.demo.exception.BusinessException;
import com.test.demo.rpc.CarDto;
import com.test.demo.rpc.CarVo;
import com.test.demo.service.IRentalCarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("User Controller Test")
public class RentalCarControllerTest {

    @Autowired
    private IRentalCarService rentalCarService;

    @Test
    void rentalCar() {
        CarVo carVo = new CarVo();
        carVo.setId(1);
        carVo.setUserName("bob");
        carVo.setBeginTime(new Date());
        carVo.setEndTime(new Date(new Date().getTime()+36000l));
        assertInstanceOf(RentalOrder.class, rentalCarService.rentalCar(carVo));
        CarVo carVo1 = new CarVo();
        carVo1.setId(1);
        carVo1.setUserName("bob");
        carVo1.setBeginTime(new Date());
        carVo1.setEndTime(new Date(new Date().getTime()+36000l));
        rentalCarService.rentalCar(carVo1);
        CarVo carVo2 = new CarVo();
        carVo2.setId(1);
        carVo2.setUserName("bob");
        carVo2.setBeginTime(new Date());
        carVo2.setEndTime(new Date(new Date().getTime()+36000l));
        assertThrows(BusinessException.class, () -> rentalCarService.rentalCar(carVo2));
        CarVo carVo3 = new CarVo();
        carVo3.setId(1);
        carVo3.setUserName("bob");
        carVo3.setBeginTime(new Date(new Date().getTime()+36000l));
        carVo3.setEndTime(new Date());
        assertThrows(BusinessException.class, () -> rentalCarService.rentalCar(carVo3));
    }

    @Test
    void returnCar() {
        assertThrows(BusinessException.class, () -> rentalCarService.returnCar(10));
        CarVo carVo = new CarVo();
        carVo.setId(2);
        carVo.setUserName("bob");
        carVo.setBeginTime(new Date());
        carVo.setEndTime(new Date(new Date().getTime()+36000l));
        assertInstanceOf(RentalOrder.class, rentalCarService.rentalCar(carVo));
        assertTrue(rentalCarService.returnCar(1));

    }

    @Test
    void getCarStock() {
        List<CarDto> carStock = rentalCarService.getCarStock();
        assertEquals(2, carStock.size());
    }

}