package heguang5460.github.io.message.web.aop;

import cn.hutool.json.JSONUtil;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 统一日志处理切面
 * 专门处理web-Controller入参的日志
 *
 * @author heguang
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class WebLogAspect {

    @Pointcut("execution(public * heguang5460.github.io.message.web.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取当前请求的方法
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        // 当前请求的路径
        String urlStr = request.getRequestURI();
        // 获取当前请求的参数
        String parameter = getParameter(method, joinPoint.getArgs());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("请求路径：{}，\n 请求入参：{}", urlStr, parameter);
        Object result = joinPoint.proceed();
        stopWatch.stop();
        long consumeTime = stopWatch.getTotalTimeMillis();
        log.info("返回结果：{}，\n 请求响应时间(毫秒)：{}", JSONUtil.toJsonStr(result), consumeTime);
        return result;
    }

    /**
     * 获取请求参数，转换成JSON
     *
     * @author heugang
     */
    private String getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            // 将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
        }
        if (argList.size() == 0) {
            return "";
        } else {
            return JSONUtil.toJsonStr(argList);
        }
    }
}
