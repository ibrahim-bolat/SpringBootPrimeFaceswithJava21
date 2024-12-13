package org.example.config;

import org.jboss.weld.environment.servlet.EnhancedListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import com.sun.faces.config.FacesInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JSFConfiguration {

    @Bean
    public ServletContextInitializer initializer(FacesInitializer facesInitializer, EnhancedListener cdiInitializer) {
        return servletContext -> {
            //bu ayar set edilmezse convertDateTime br gün önce tarih alıyor.
            servletContext.setInitParameter("jakarta.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE", Boolean.TRUE.toString());
            //xhtml kullanmadan doğrudan sayfa ismi ile ulaşılabiliyor url e
            servletContext.setInitParameter("jakarta.faces.AUTOMATIC_EXTENSIONLESS_MAPPING", "true");
            //primefaces set theme
            servletContext.setInitParameter("primefaces.THEME", "nova-light");

            // Faces servleti başlatılıyor
            facesInitializer.onStartup(null, servletContext);

            // CDI container başlatılıyor
            cdiInitializer.onStartup(null, servletContext);
        };
    }

    @Bean
    public FacesInitializer facesInitializer() {
        return new FacesInitializer();
    }

    @Bean
    public EnhancedListener cdiInitializer() {
        return new EnhancedListener();
    }
}