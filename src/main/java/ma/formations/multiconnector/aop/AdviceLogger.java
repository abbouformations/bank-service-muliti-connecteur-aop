package ma.formations.multiconnector.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
public class AdviceLogger {

    @Before("@annotation(Tracabilite)")
    public void trace(JoinPoint joinPoint) throws Throwable {
        String methodeName = joinPoint.getSignature().getName();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(new Date());
        //  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = "anonyme";
        System.out.println(methodeName + " called by " + userName + " at " + dateString);

    }
}
