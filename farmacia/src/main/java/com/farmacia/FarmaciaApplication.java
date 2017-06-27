package com.farmacia;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.farmacia.exception.WebException;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Order(Ordered.LOWEST_PRECEDENCE)
@EnableSwagger2
@EnableAutoConfiguration
@Configuration
@EnableAsync
@EnableJpaAuditing
@EnableScheduling
public class FarmaciaApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(FarmaciaApplication.class, args);
	}
	
    @Bean
    public SwaggerConfig swagger() {
        return new SwaggerConfig();
    }
    
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {

            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                Exception ex) {
                if (ex.getClass().isAssignableFrom(BindException.class)) {
                    BindException bindex = (BindException) ex;
                    final StringBuilder message = new StringBuilder();

                    List<ObjectError> allErrors = bindex.getAllErrors();
                    for (ObjectError a : allErrors) {
                        message.append(a.getDefaultMessage()).append("\n");
                    }
                    try {
                        response.sendError(HttpStatus.BAD_REQUEST.value(), message.toString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return new ModelAndView();
                }
                if (ex instanceof com.farmacia.exception.WebException) {
                    WebException webex = (WebException) ex;
                    try {
                        response.sendError(webex.getStatus().value(), ex.getMessage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return new ModelAndView();
                }
                
                return null;
            }
        });
        super.configureHandlerExceptionResolvers(exceptionResolvers);
    }
}
