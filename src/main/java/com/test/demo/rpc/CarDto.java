package com.test.demo.rpc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Car")
public class CarDto {

    @ApiModelProperty(value = "car type, 1:camry 2 bwm 650", dataType = "Integer")
    private Integer carId;

    @ApiModelProperty(value = "rental car type desc", dataType = "String")
    private String cardTypeDesc;

    @ApiModelProperty(value = "rental end stock", dataType = "Integer")
    private Integer stock;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCardTypeDesc() {
        return cardTypeDesc;
    }

    public void setCardTypeDesc(String cardTypeDesc) {
        this.cardTypeDesc = cardTypeDesc;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
