package com.test.demo.aop;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAopAdvice {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public void printMethodParams(JoinPoint point){
        if(point == null){
            return;
        }
        String class_name = point.getTarget().getClass().getName();
        String method_name = point.getSignature().getName();
        //重新定义日志
        logger = LoggerFactory.getLogger(point.getTarget().getClass());
        logger.info("class_name = {}",class_name);
        logger.info("method_name = {}",method_name);

        Object[] method_args = point.getArgs();

        try {
            String[] paramNames = getFieldsName(class_name, method_name);

            logParam(paramNames,method_args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] getFieldsName(String class_name, String method_name) throws Exception {
        Class<?> clazz = Class.forName(class_name);
        String clazz_name = clazz.getName();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(clazz);
        pool.insertClassPath(classPath);

        CtClass ctClass = pool.get(clazz_name);
        CtMethod ctMethod = ctClass.getDeclaredMethod(method_name);
        MethodInfo methodInfo = ctMethod.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if(attr == null){
            return null;
        }
        String[] paramsArgsName = new String[ctMethod.getParameterTypes().length];
        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
        for (int i=0;i<paramsArgsName.length;i++){
            paramsArgsName[i] = attr.variableName(i + pos);
        }
        return paramsArgsName;
    }


    private boolean isPrimite(Class<?> clazz){
        if (clazz.isPrimitive() || clazz == String.class){
            return true;
        }else {
            return false;
        }
    }


    private void logParam(String[] paramsArgsName,Object[] paramsArgsValue){
        if(paramsArgsName.length == 0 || paramsArgsValue.length == 0){
            logger.info("该方法没有参数");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<paramsArgsName.length;i++){
            //参数名
            String name = paramsArgsName[i];
            //参数值
            Object value = paramsArgsValue[i];
            buffer.append(name +" = ");
            if(isPrimite(value.getClass())){
                buffer.append(value + "  ,");
            }else {
                buffer.append(value.toString() + "  ,");
            }
        }
        logger.info(buffer.toString());
    }

    @Before("execution(public * com.test.demo.controller..*.*(..))")
    public void before(JoinPoint point) {
        this.printMethodParams(point);
    }

}
