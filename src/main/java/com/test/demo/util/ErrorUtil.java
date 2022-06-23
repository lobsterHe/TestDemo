package com.test.demo.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorUtil {

    /**
     * format errorInfo
     * @param e
     * @return
     */
    public static String errorInfoToString(Throwable e) {
        //try-with-resource语法糖 处理机制
        try(StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)){
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        }catch (Exception ignored){
            throw new RuntimeException(ignored.getMessage(),ignored);
        }
    }

}
