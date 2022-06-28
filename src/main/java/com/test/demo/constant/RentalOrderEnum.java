package com.test.demo.constant;

public enum RentalOrderEnum {

    RENTAL("1","rental"),
    LEASE_RENEWAL("2", "Lease renewal"),
    RETURN("3", "return");

    private String status;

    private String desc;

    RentalOrderEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

}
