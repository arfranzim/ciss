package br.com.franzim.ciss.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ErroDeValidacaoHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus( code = HttpStatus.BAD_REQUEST )
	@ExceptionHandler( MethodArgumentNotValidException.class)
	public List< ErroDeFormularioDTO > handle( MethodArgumentNotValidException exception ) {
		
		List< ErroDeFormularioDTO > dtos = new ArrayList<>(0);
		
		exception.getBindingResult().getFieldErrors().forEach(
			e -> {
				dtos.add( ErroDeFormularioDTO.getInstance( e.getField(), messageSource.getMessage( e, LocaleContextHolder.getLocale() ) ) );
			}
		);
		
		return dtos;
	}
	
}
