package io.iamkyu.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Kj Nam
 * @since 2017-05-22
 */
@Component
@Aspect
public class PerformanceAspect {
    private static final Logger logger = LoggerFactory.getLogger(PerformanceAspect.class);

    @Pointcut("within(io.iamkyu.controller..*) || within(io.iamkyu.service..*)")
    public void perform() {
    }

    @Around("within(io.iamkyu.controller..*) || within(io.iamkyu.service..*)")
    public Object perform(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object value = pjp.proceed();
        long endTime = System.currentTimeMillis();

        logger.debug("execution time: {}, {}", pjp.getTarget().toString(), (endTime - startTime));
        return value;
    }

    @Pointcut("within(io.iamkyu.controller..*) || within(io.iamkyu.service..*)")
    public void loggingPointcut() {}

    @Around("loggingPointcut()")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        logger.debug("{}(): {}", methodName, pjp.getArgs());

        Object result = pjp.proceed();
        logger.debug("{}(): result={}", methodName, result);

        return result;
    }
}

