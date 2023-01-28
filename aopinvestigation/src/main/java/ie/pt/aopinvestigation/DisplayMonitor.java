package ie.pt.aopinvestigation;


// This class monitors all calls to methods called display()

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


public class DisplayMonitor {
/*
    @Before("execution(* ie.pt.aopinvestigation.*.display(..))")
    public void LogBeforeDisplayCalled() {

        System.out.println("About to call a display method");
        System.out.println(System.currentTimeMillis());
    }

    @After("execution(* ie.pt.aopinvestigation.*.display(..))")
    public void LogAfterDisplayCalled() {

        System.out.println("display() method finished");
        System.out.println(System.currentTimeMillis());
    }
*/
    @Around("execution(* ie.pt.aopinvestigation.*.display(..))")
    public void timeDisplayMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        // System.out.printf("can't call this method()");

        Object obj = joinPoint.getThis();

        if (obj.getClass().getSimpleName().contains("TestClass")) {
            // slow method
            System.out.println("can't run slow method");

            TestClass tc = (TestClass) obj;

            System.out.println("***TestClass:");
            System.out.println(tc.getMessage());
            System.out.println("-------------");


        } else {
          joinPoint.proceed();

        }

        long endTime = System.currentTimeMillis();

        System.out.println("Time taken:" + (endTime - startTime));

    }
}
