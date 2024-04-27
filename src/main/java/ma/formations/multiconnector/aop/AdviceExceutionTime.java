package ma.formations.multiconnector.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AdviceExceutionTime {

    @Around("@annotation(ExecuitionTime)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        long time = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        time = System.currentTimeMillis() - time;
        System.out.println("La méthode " + joinPoint.getSignature().getName() +
                " a été exécuté dans " + time + " seconde");
        return proceed;

    }
}
