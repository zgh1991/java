package org.ssm.dufy.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class FileUploadExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse response, Object handler, Exception ex) {
		
		//ex.printStackTrace();
		FileUploadException fileException = null;

        //如果抛出的是系统自定义的异常则直接转换
        if(ex instanceof FileUploadException) {
        	fileException = (FileUploadException) ex;
        } else {
            //如果抛出的不是系统自定义的异常则重新构造一个未知错误异常
            //这里我就也有CustomException省事了，实际中应该要再定义一个新的异常
        	fileException = new FileUploadException("系统未知错误");
        }

        //向前台返回错误信息
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", fileException.getMessage());
        modelAndView.setViewName("upload_error");

        return modelAndView;
	}

}
