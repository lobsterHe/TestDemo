package com.test.demo.rpc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("Rental Order")
public class RentalOrderDto {

    @ApiModelProperty(value = "order id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(value = "userName", dataType = "String")
    private String userName;

    @ApiModelProperty(value = "card type", dataType = "String")
    private String cardType;

    @ApiModelProperty(value = "rental time", dataType = "Date")
    private Date rentalTime;

    @ApiModelProperty(value = "return time", dataType = "Date")
    private Date returnTime;

    @ApiModelProperty(value = "order status", dataType = "String")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardId() {
        return cardType;
    }

    public void setCardId(String cardType) {
        this.cardType = cardType;
    }

    public Date getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(Date rentalTime) {
        this.rentalTime = rentalTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
