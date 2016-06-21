package com.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.template.TemplateLocation;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.MimeType;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.context.SpringContextUtils;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Thymeleaf3.
 * Customizing org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration 
 */
@Configuration
@EnableConfigurationProperties(ThymeleafProperties.class)
@ConditionalOnClass({SpringTemplateEngine.class, SpringContextUtils.class})
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class Thymeleaf3AutoConfiguration {

    private static final Log logger = LogFactory.getLog(Thymeleaf3AutoConfiguration.class);

    @Autowired
    private ThymeleafProperties properties;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void checkTemplateLocationExists() {
        boolean checkTemplateLocation = this.properties.isCheckTemplateLocation();
        if (checkTemplateLocation) {
            TemplateLocation location = new TemplateLocation(
                    this.properties.getPrefix());
            if (!location.exists(this.applicationContext)) {
                logger.warn("Cannot find template location: " + location
                        + " (please add some templates or check "
                        + "your Thymeleaf configuration)");
            }
        }
    }

    @Autowired(required = false)
    private final Collection<IDialect> dialects = Collections.emptySet();

    @Autowired
    private final Collection<ITemplateResolver> templateResolvers =
            Collections.emptySet();

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        for (ITemplateResolver templateResolver : this.templateResolvers) {
            engine.setTemplateResolver(templateResolver);
        }
        for (IDialect dialect : this.dialects) {
            engine.addDialect(dialect);
        }
        return engine;
    }

    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setPrefix(this.properties.getPrefix());
        resolver.setSuffix(this.properties.getSuffix());
        resolver.setTemplateMode(this.properties.getMode());
        if (this.properties.getEncoding() != null) {
            resolver.setCharacterEncoding(this.properties.getEncoding().name());
        }
        resolver.setCacheable(this.properties.isCache());
        Integer order = this.properties.getTemplateResolverOrder();
        if (order != null) {
            resolver.setOrder(order);
        }
        return resolver;
    }

    @Bean
    @ConditionalOnMissingBean(name = "thymeleafViewResolver")
    @ConditionalOnProperty(name = "spring.thymeleaf3.enabled", matchIfMissing = true)
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(this.templateEngine());
        resolver.setCharacterEncoding(this.properties.getEncoding().name());
        resolver.setContentType(appendCharset(this.properties.getContentType(),
                resolver.getCharacterEncoding()));
        resolver.setExcludedViewNames(this.properties.getExcludedViewNames());
        resolver.setViewNames(this.properties.getViewNames());
        resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 5);
        return resolver;
    }

    private String appendCharset(MimeType type, String charset) {
        if (type.getCharSet() != null) {
            return type.toString();
        }
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("charset", charset);
        parameters.putAll(type.getParameters());
        return new MimeType(type, parameters).toString();
    }

}