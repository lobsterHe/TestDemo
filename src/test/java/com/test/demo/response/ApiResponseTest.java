package com.test.demo.response;

import com.test.demo.exception.ExceptionEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;


@SpringBootTest
@DisplayName("Api Response Test")
class ApiResponseTest {

    @Test
    void construct() {
        ApiResponse response = new ApiResponse(ExceptionEnum.USER_NOT_EXIT.getCode(),ExceptionEnum.USER_NOT_EXIT.getMsg());
        Assertions.assertNotEquals("200", response.getCode());
        ApiResponse response1 = new ApiResponse(true);
        Assertions.assertEquals("200", response1.getCode());
    }
}