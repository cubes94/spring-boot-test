package com.whc.exception;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * Created by whc on 2017/3/29.
 */
@Configuration
public class ExceptionCustomizer implements EmbeddedServletContainerCustomizer {

    public static final String DEFAULT_ERROR_404_VIEW = "/hello";

    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, DEFAULT_ERROR_404_VIEW));
    }
}
