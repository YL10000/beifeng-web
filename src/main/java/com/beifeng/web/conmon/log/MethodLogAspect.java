/**
 * author <a href="mailto:zhsj0110@163.com">zhoushijun</a>
 */
package com.beifeng.web.conmon.log;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.beifeng.web.model.sys.log.OpLog;
import com.beifeng.web.service.IOpLogService;

/**
 * @author <a href="mailto:zhsj0110@163.com">zhoushijun</a>
 *
 */
@Aspect
public class MethodLogAspect {

	private static final Log logger = LogFactory.getLog(MethodLogAspect.class);
	
	@Autowired
	IOpLogService opLogService;
	
	//@Before("execution(* com.byws.framework.action.*.*(..))")
	public void before(JoinPoint point) throws Throwable {
		logger.info("打印========================");
	}

	@After("execution(* com.beifeng.web.controller.*.*(..))")
	public void after(JoinPoint point) {
//		logger.info("after");
		
		log(point, 1 , null);
		
	}

	/**
	 * 方法执行的前后调用
	 * @param point
	 * @return
	 * @throws Throwable
	 */
//	@Around("execution(* com.byws.framework.action.*.*(..))")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		
		log(point, 1, null);
		
		Object object;
		try {
			object = point.proceed();
		} catch (Exception e) {
			// 异常处理记录日志..log.error(e);
			throw e;
		}
		return object;
	}

	/**
	 * 方法运行出现异常时调用
	 * @param ex
	 */
	@AfterThrowing(pointcut = "execution(* com.beifeng.web.controller.*.*(..))", throwing = "ex")
	public void afterThrowing(JoinPoint point ,Exception ex) {
		logger.info("afterThrowing");
		logger.error(ex);
		log(point, 0 , ex.getMessage());
	}

	/**
	 * 获取方法的中文备注____用于记录用户的操作日志描述
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> getMthodRemark(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		
		Map<String,Object> ret = null;
		
		Class<?> targetClass = Class.forName(targetName);
		Method[] method = targetClass.getMethods();
		String methodRemark = "";
		for (Method m : method) {
			if (m.getName().equals(methodName)) {
				Class<?>[] tmpCs = m.getParameterTypes();
				if (tmpCs.length == arguments.length) {
					MethodLog methodLog = m.getAnnotation(MethodLog.class);
					if( null != methodLog ){
						methodRemark = methodLog.remark();
						
						ret = new HashMap<String, Object>();
						ret.put("methodRemark", methodRemark);
						
						for(Object arg : arguments){
							if( arg instanceof OpLog ){
								ret.put("opLog", arg);
							}
						}
					}
					
					break;
				}
			}
		}
		return ret;
	}

	
	private void log(JoinPoint point ,Integer result, String exception){

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		String remark = "";;
		
		try {  
			Map<String,Object> ret = getMthodRemark(point);
			if( null != ret ){
				remark = (String)ret.get("methodRemark");
				
				String methodName = point.getSignature().getName();
				String packagez = point.getThis().getClass().getName();

				OpLog opLog = (OpLog)ret.get("opLog");
				if( StringUtils.isBlank(opLog.getRemark())){
					logger.warn("日志内容不能为空，请通过oplog.set(text)设置日志");
				}
				opLog.setRemark(remark);
				opLog.setMethod(methodName);
				opLog.setException(exception);
				opLog.setResult(result);
				opLog.setCreate_time(new Date());
				opLogService.insert(opLog);
			}
		} catch (Exception e) {
			logger.error("MethodLogAspect Exception",e);
		}
		
	}
}