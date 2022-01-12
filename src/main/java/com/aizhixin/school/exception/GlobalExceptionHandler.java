package com.aizhixin.school.exception;

import com.aizhixin.school.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public Object exception(HttpServletResponse response, Exception e) {
		int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
		response.setStatus(status);
		return Result.build(status, e.getMessage());
	}

	/**
	 * 参数异常验证
	 * @param response
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Object methodArgumentNotValidException(HttpServletResponse response, Exception e) {
		int status = HttpStatus.BAD_REQUEST.value();
		response.setStatus(status);
		MethodArgumentNotValidException ex = (MethodArgumentNotValidException)e;
		return Result.build(status, ex.getBindingResult().getFieldError().getDefaultMessage());
	}

	@ExceptionHandler({BaseException.class})
	public Result sportExceptionHandler(HttpServletResponse response, BaseException exception) {
		response.setStatus(exception.getCode());
		return Result.build(exception.getCode(), exception.getMessage());
	}

	/**
	 * 返回错误提示
	 */
	@ExceptionHandler(value = CommonException.class)
	public Object commonException(HttpServletResponse response, Exception e) {
		int status = HttpStatus.PRECONDITION_FAILED.value();
		response.setStatus(status);
		return Result.build(HttpStatus.PRECONDITION_FAILED.value(), e.getMessage());
	}




}
