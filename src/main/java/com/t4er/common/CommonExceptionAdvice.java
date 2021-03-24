package com.t4er.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;

/*�������� ���� ó�� ���
 * [1] @ExceptionHandler�� �̿��ϴ� ���
 * [2] @ControllerAdvice�� �̿��ϴ� ���
 * [3] @ResponseStatus�� �̿��� HTTP�����ڵ� ó��
 * 
 * */
@Log4j
@ControllerAdvice
public class CommonExceptionAdvice {
	
	//��� ���ܸ� ó���ϴ� �޼ҵ�
	//@ControllerAdvice�� �� Ŭ���� ��ü�� ��Ʈ�ѷ����� �߻��ϴ� ���ܸ� 
	//���������� ó���ϴ� Ŭ�������� ����ϴ� ��
	@ExceptionHandler(NumberFormatException.class)
	public String commonHandler(Exception ex) {
		log.error("���ڷ� �Է��ؾ� �ؿ� : "+ex.getMessage());
		return "user/errorAlert";
	}

}
