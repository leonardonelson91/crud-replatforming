package org.apache.struts.crud;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean strutsFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new StrutsPrepareAndExecuteFilter());
        filterRegistrationBean.addUrlPatterns(
                "/",
                "/index.action",
                "/inputPerson.action",
                "/deletePerson.action",
                "/savePerson.action");
        return filterRegistrationBean;
    }

}
