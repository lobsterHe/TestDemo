package com.test.demo.dao;

import com.test.demo.entity.Car;
import com.test.demo.exception.BusinessException;
import com.test.demo.exception.ExceptionEnum;
import com.test.demo.rpc.CarDto;
import com.test.demo.rpc.CarVo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RentalCarDao {

    private static Map<Integer, Car> rentalCarRepository = new ConcurrentHashMap<Integer, Car>();

    static {
        Car camry = new Car();
        camry.setId(1);
        camry.setCarModel("Toyota Camry");
        camry.setStock(2);
        rentalCarRepository.put(1, camry);
        Car bwm = new Car();
        bwm.setId(2);
        bwm.setCarModel("BWM 650");
        bwm.setStock(2);
        rentalCarRepository.put(2, bwm);
    }

    public boolean rentalCar(CarVo carVo) {
        Car car = rentalCarRepository.get(carVo.getId());
        if(car.getStock() == 0){
            throw new BusinessException(ExceptionEnum.NO_STOCK);
        }
        car.setStock(car.getStock()-1);
        rentalCarRepository.put(carVo.getId(), car);
        return true;
    }

    public List<CarDto> getAll() {
        List<CarDto> carDtoList = new ArrayList<>();
        for (Integer id : rentalCarRepository.keySet()) {
            CarDto carDto = new CarDto();
            carDto.setCarId(rentalCarRepository.get(id).getId());
            carDto.setCardTypeDesc(rentalCarRepository.get(id).getCarModel());
            carDto.setStock(rentalCarRepository.get(id).getStock());
            carDtoList.add(carDto);
        }
        return carDtoList;
    }

    public Boolean returnCar(Integer carId) {
        Car car = rentalCarRepository.get(carId);
        car.setStock(car.getStock()+1);
        rentalCarRepository.put(carId, car);
        return true;
    }
}
