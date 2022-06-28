package com.test.demo.dao;

import com.test.demo.constant.RentalOrderEnum;
import com.test.demo.entity.RentalOrder;
import com.test.demo.exception.BusinessException;
import com.test.demo.exception.ExceptionEnum;
import com.test.demo.rpc.CarVo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRentalOrderDao {

    private static Map<Integer, RentalOrder> rentalOrderRepository = new ConcurrentHashMap<Integer, RentalOrder>();

    private AtomicInteger rentalOrderCount = new AtomicInteger(0);

    public RentalOrder createRentalOrder(CarVo carVo) {
        int id = rentalOrderCount.incrementAndGet();
        RentalOrder rentalOrder = new RentalOrder();
        rentalOrder.setId(id);
        rentalOrder.setRentalTime(carVo.getBeginTime());
        rentalOrder.setReturnTime(carVo.getEndTime());
        rentalOrder.setUserName(carVo.getUserName());
        rentalOrder.setStatus(RentalOrderEnum.RENTAL.getStatus());
        rentalOrder.setCardId(carVo.getId());
        rentalOrderRepository.put(id, rentalOrder);
        return rentalOrder;
    }

    public Integer returnCar(Integer id) {
        RentalOrder rentalOrder = rentalOrderRepository.get(id);
        if (rentalOrder==null){
            throw new BusinessException(ExceptionEnum.ORDER_ID_IS_NOT_EXIST);
        }
        Date date = new Date();
        if(rentalOrder.getReturnTime().getTime()< date.getTime()){
            rentalOrder.setReturnTime(date);
        }
        rentalOrder.setStatus(RentalOrderEnum.RETURN.getStatus());
        rentalOrderRepository.put(id, rentalOrder);
        Integer cardId = rentalOrder.getCardId();
        return cardId;
    }
}
