package com.test.demo.service;

import com.test.demo.entity.RentalOrder;
import com.test.demo.rpc.CarDto;
import com.test.demo.rpc.CarVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRentalCarService {
    RentalOrder rentalCar(CarVo carVo);

    boolean returnCar(Integer id);

    List<CarDto> getCarStock();
}
