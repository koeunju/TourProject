package com.t4er.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;

/*스프링의 예외 처리 방법
 * [1] @ExceptionHandler를 이용하는 방법
 * [2] @ControllerAdvice를 이용하는 방법
 * [3] @ResponseStatus를 이용한 HTTP상태코드 처리
 * 
 * */
@Log4j
@ControllerAdvice
public class CommonExceptionAdvice {
	
	//모든 예외를 처리하는 메소드
	//@ControllerAdvice는 이 클래스 객체가 컨트롤러에서 발생하는 예외를 
	//전문적으로 처리하는 클래스임을 명시하는 것
	@ExceptionHandler(NumberFormatException.class)
	public String commonHandler(Exception ex) {
		log.error("숫자로 입력해야 해요 : "+ex.getMessage());
		return "user/errorAlert";
	}

}
