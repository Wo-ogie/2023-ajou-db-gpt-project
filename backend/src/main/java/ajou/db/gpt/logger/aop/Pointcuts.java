package ajou.db.gpt.logger.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* ajou.db.gpt.controller..*(..))")
    public void controllerPoint(){}

    @Pointcut("execution(* ajou.db.gpt.service..*(..))")
    public void servicePoint(){}

    @Pointcut("execution(* ajou.db.gpt.repository..*(..))")
    public void repositoryPoint(){}
}
