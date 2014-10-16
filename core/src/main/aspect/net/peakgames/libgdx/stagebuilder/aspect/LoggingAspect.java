package net.peakgames.libgdx.stagebuilder.aspect;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.badlogic.gdx.scenes.scene2d.utils.ClickListener.touchDown(..))")
    public void touchEntryPoint() {
    }

    @Before("touchEntryPoint()")
    public void touchLogging(JoinPoint joinPoint) {
        System.out.println("TOUCH : " + joinPoint.getSignature());

        InputListener listener = (InputListener) joinPoint.getTarget();
        System.out.println("Listener : " + listener);


        System.out.println("Event : " + joinPoint.getArgs()[0]);
        System.out.println("X : " + joinPoint.getArgs()[1]);
        System.out.println("Y : " + joinPoint.getArgs()[2]);
        System.out.println("Pointer : " + joinPoint.getArgs()[3]);
        System.out.println("button : " + joinPoint.getArgs()[4]);

        System.out.println("Actor : " + ((Event)joinPoint.getArgs()[0]).getTarget().getName());
    }

}
