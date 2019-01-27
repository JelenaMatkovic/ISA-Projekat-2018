package isa.config;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

	 @ExceptionHandler(value = NullPointerException.class)
	 public void handleNullPointerException(NullPointerException exception, HttpServletResponse response) throws IOException {
	      response.sendError(400,exception.getMessage());
	 }
}
