package com.whr.dms.exceptions.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.whr.dms.exceptions.ParameterCheckException;


/**
 * spring mvc 全局异常捕获处理
 * @author Lyon
 *
 */
@Component
public class GloableExceptionHandler implements HandlerExceptionResolver {
	final Logger log = LoggerFactory.getLogger(GloableExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		//异常日志记录级别设定
		if(ex instanceof ParameterCheckException){
			//业务参数异常只做info级别日志
			log.info(ex.getMessage(),ex);
		}else{
			log.error(ex.getMessage(),ex);
		}
		
		//特殊异常提示
		String tip = null;
		
		//乐观锁更新冲突
		if(ex instanceof JpaOptimisticLockingFailureException){
			tip = "更新操作冲突，请确保在同一时间只有您一人在操作该条记录。";
		}
		
		//返回页面异常页面（"WEB-INF/views/error/error.jsp"）
		ModelAndView mv = new ModelAndView("error/error");
		mv.addObject("exception", ex);
		mv.addObject("stacktrace",ExceptionUtils.getStackTrace(ex));
		mv.addObject("tip", tip);
		return mv;
	}

}
