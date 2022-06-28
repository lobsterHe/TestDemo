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

import java.util.Date;
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
        validateTime(carVo.getBeginTime(), carVo.getEndTime());
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

    private void validateTime(Date beginTime, Date endTime){
        if(beginTime.getTime()>=endTime.getTime()){
            throw new BusinessException(ExceptionEnum.END_TIME_MORE_THAN_BEGIN_TIME.getCode(), ExceptionEnum.END_TIME_MORE_THAN_BEGIN_TIME.getMsg());
        }
        if(beginTime.getTime()<new Date().getTime()){
            throw new BusinessException(ExceptionEnum.BEGIN_TIME_LESS_THAN_NOW_TIME.getCode(), String.format(ExceptionEnum.END_TIME_MORE_THAN_BEGIN_TIME.getMsg()));
        }
    }
}
