package com.tuniu.operation.framework.base.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuniu.operation.framework.base.json.JsonUtil;

@Aspect
public class AspectLog {

    private static Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Pointcut(value = "execution(* com.tuniu.ord.service.*.*(..))")
    public void pointcutA() {

    }

    @Around(value = "pointcutA()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        logger.info("class:{},in method:{},param:{}", className, methodName, JsonUtil.toString(joinPoint.getArgs()));

        Object rvt = joinPoint.proceed();

        logger.info("class:{},out method:{},result:{}", className, methodName, JsonUtil.toString(rvt));

        return rvt;
    }
}
