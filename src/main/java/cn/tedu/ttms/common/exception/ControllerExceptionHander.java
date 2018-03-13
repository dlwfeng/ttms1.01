package cn.tedu.ttms.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
/**
 * 全局异常处理对象：实现所有Controller中的异常
 * @ControllerAdvice 声明的类可以作为统一的异常处理对象
 */
@ControllerAdvice
public class ControllerExceptionHander {
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult handleServiceException(ServiceException e){
		e.printStackTrace();
		return new JsonResult(e);
	}
}