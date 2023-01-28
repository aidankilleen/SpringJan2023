package ie.pt.aopinvestigation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class TimedAspect {

    @Around("execution(* ie.pt.aopinvestigation.*.display(..))")
    public void timeMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Aspect activated.");
        Object obj = joinPoint.getThis();

        MethodSignature method = (MethodSignature)joinPoint.getSignature();

        if (method.getMethod().isAnnotationPresent(Timed.class)) {

            long startTime = System.currentTimeMillis();
            joinPoint.proceed();
            long endTime = System.currentTimeMillis();

            System.out.println("Time taken:" + (endTime - startTime));

        } else {
            joinPoint.proceed();
        }
    }
}
