package com.ethbek.mezion.inventory.service.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.OperationNotSupportedException;
import javax.naming.directory.InvalidSearchFilterException;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
	static final String EXCEPTIONMSG = "Error occurred in transaction :{}";
	static final String RESULT = "EXPECTATION FAILED";
	static final String GENERALEXPECTIONMSG = "General Expection";
	static final String INVALIDMSG = "Invalid Search Input";
	static final String SERIALIZATIONMSG = "Data Serialization failed";
	static final String DATACONSTRAINTMSG = "Data Constraint Violation";
	static final String SERVICEMSG = "Internal Micro Service Error";
	static final String OPERATIONNOTSUPPORTEDMSG = "No Such Operation Supported";
	static final String DUPLICATIEDKEYMSG = "Duplicated Entry";
	static final String DATAINTEGRATMSG = "Data Integrity Violation";
	static final String DATAACCESSSMSG = "Data Access violation";
	static final String NONTRANSIENTDATAMSG  = "Non Transient Data exception";
	static final String NOAVAILABLEDATAMSG  = "No Available Data exception";
	static final String RUNTIMEMSG  = "Micro Service Runtime error";
	static final String SQLMSG = "Database sql exception";

	@ExceptionHandler(value = {SQLException.class})
	public ResponseEntity<Object> handleDBException(SQLException ex){

	ErrorMessage errorMessage = new ErrorMessage(SQLMSG,417,RESULT);
		log.error(EXCEPTIONMSG,ex.getMessage(),ex);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(value = {Throwable.class})
	public ResponseEntity<Object> handleDBException(Throwable ex){
		ErrorMessage errorMessage = new ErrorMessage(GENERALEXPECTIONMSG,417,RESULT);
		log.error(EXCEPTIONMSG,ex.getMessage(),ex);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
	}
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleDBException(Exception ex){
		ErrorMessage errorMessage = new ErrorMessage(GENERALEXPECTIONMSG,417,RESULT);
		log.error(EXCEPTIONMSG,ex.getMessage(),ex);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(value = {RuntimeException.class})
	public ResponseEntity<Object> handleDBException(RuntimeException ex){
		ErrorMessage errorMessage = new ErrorMessage(RUNTIMEMSG,500,RESULT);
		log.error(EXCEPTIONMSG,ex.getMessage(),ex);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(value = {OperationNotSupportedException.class})
	public ResponseEntity<Object> handleDBException(OperationNotSupportedException ex){
		ErrorMessage errorMessage = new ErrorMessage(OPERATIONNOTSUPPORTEDMSG,406,RESULT);
		log.error(EXCEPTIONMSG,ex.getMessage(),ex);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler(value = {InvalidSearchFilterException.class})
	public ResponseEntity<Object> handleDBException(InvalidSearchFilterException ex){
		ErrorMessage errorMessage = new ErrorMessage(INVALIDMSG,417,RESULT);
		log.error(EXCEPTIONMSG,ex.getMessage(),ex);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(value = {DuplicateKeyException.class})
	public ResponseEntity<Object> handleDBException(DuplicateKeyException ex){
		ErrorMessage errorMessage = new ErrorMessage(DUPLICATIEDKEYMSG,409,RESULT);
		log.error(EXCEPTIONMSG,ex.getMessage(),ex);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.CONFLICT);
	}
	@ExceptionHandler(value = {DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDBException(DataIntegrityViolationException ex){
		ErrorMessage errorMessage = new ErrorMessage(DATAINTEGRATMSG,409,RESULT);
		log.error(EXCEPTIONMSG,ex.getMessage(),ex);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.CONFLICT);
	}
	@ExceptionHandler(value = {NonTransientDataAccessException.class})
	public ResponseEntity<Object> handleDBException(NonTransientDataAccessException ex){
		ErrorMessage errorMessage = new ErrorMessage(NONTRANSIENTDATAMSG,409,RESULT);
		log.error(EXCEPTIONMSG,ex.getMessage(),ex);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.CONFLICT);
	}
	@ExceptionHandler(value = {DataAccessException.class})
	public ResponseEntity<Object> handleDBException(DataAccessException ex){
		ErrorMessage errorMessage = new ErrorMessage(DATAACCESSSMSG,451,RESULT);
		log.error(EXCEPTIONMSG,ex.getMessage(),ex);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
	}
	@ExceptionHandler(value = {NoAvailableResourceException.class})
	public ResponseEntity<Object> handleNullException(NoAvailableResourceException ex){
		ErrorMessage errorMessage = new ErrorMessage(NOAVAILABLEDATAMSG,417,RESULT);
		log.error(EXCEPTIONMSG,ex.getMessage(),ex);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(value = {NullPointerException.class})
	public ResponseEntity<Object> handleNullException(NullPointerException ex){
		ErrorMessage errorMessage = new ErrorMessage(NOAVAILABLEDATAMSG,417,RESULT);
		log.error(EXCEPTIONMSG,ex.getMessage(),ex);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
	}
}
