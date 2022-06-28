package com.test.demo.service.impl;

import com.test.demo.dao.RentalCarDao;
import com.test.demo.dao.UserRentalOrderDao;
import com.test.demo.entity.RentalOrder;
import com.test.demo.exception.BusinessException;
import com.test.demo.exception.ExceptionEnum;
import com.test.demo.rpc.CarDto;
import com.test.demo.rpc.CarVo;
import com.test.demo.service.IRentalCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RentalCarServiceImpl implements IRentalCarService {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private RentalCarDao rentalCarDao;

    @Autowired
    private UserRentalOrderDao userRentalOrderDao;

    @Override
    public RentalOrder rentalCar(CarVo carVo) {
        validateParam(carVo.getId(), "car id");
        validateParam(carVo.getBeginTime(), "beginTime");
        validateParam(carVo.getEndTime(), "endTime");
        validateParam(carVo.getUserName(), "username");
        // reduce stock
        rentalCarDao.rentalCar(carVo);
        // create rental order
        return  userRentalOrderDao.createRentalOrder(carVo);
    }

    @Override
    public boolean returnCar(Integer id) {
        validateParam(id, "order id");
        Integer carId = userRentalOrderDao.returnCar(id);
        return rentalCarDao.returnCar(carId);
    }

    @Override
    public List<CarDto> getCarStock() {
        return rentalCarDao.getAll();
    }

    private void validateParam(Object object, String desc){
        if(object==null){
            String simpleName = object.getClass().getSimpleName();
            logger.error("{} is null", simpleName);
            throw new BusinessException(ExceptionEnum.IS_NOT_NULL.getCode(), String.format(ExceptionEnum.IS_NOT_NULL.getMsg(), desc));
        }
    }
}
