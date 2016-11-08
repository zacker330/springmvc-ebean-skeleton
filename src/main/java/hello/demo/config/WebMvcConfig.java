package hello.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("hello.demo.component, hello.demo.controller")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(WebMvcConfig.class);


    private static final int CACHE_PERIOD = 31556926; // one year

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
        requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
    }

    @Bean
    public ViewResolver getViewResolver() {
        VelocityViewResolver resolver = new VelocityViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".vm");
        resolver.setContentType("text/html;charset=utf-8");
        return resolver;
    }

    @Bean
    public org.springframework.web.servlet.view.velocity.VelocityConfigurer velocityConfig() {
        VelocityConfigurer configurer = new VelocityConfigurer();
        Properties p = new Properties();
        p.setProperty("input.encoding", "UTF-8");
        p.setProperty("output.encoding", "UTF-8");
        configurer.setVelocityProperties(p);
        configurer.setResourceLoaderPath("/");
        return configurer;
    }

//    @Bean
//    public org.springframework.web.servlet.view.mustache.java.LocalizationMessageInterceptor i18nMessageInterceptor(){
//
//    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Static ressources from both WEB-INF and webjars
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(CACHE_PERIOD);
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCachePeriod(CACHE_PERIOD);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // Serving static files using the Servlet container's default Servlet.
        configurer.enable();
    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        // add your custom formatters
    }
}
