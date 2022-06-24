package com.test.demo.util;

import com.test.demo.exception.BusinessException;
import com.test.demo.exception.ExceptionEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Error Util Test")
class ErrorUtilTest {

    @Test
    void errorInfoToString() {
        Assertions.assertInstanceOf(String.class, ErrorUtil.errorInfoToString(new BusinessException(ExceptionEnum.USER_NOT_EXIT)));
    }
}