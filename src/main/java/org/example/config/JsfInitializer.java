package org.example.config;

import org.jboss.weld.environment.servlet.EnhancedListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import com.sun.faces.config.FacesInitializer;

public class JsfInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        //bu ayar set edilmezse convertDateTime br gün önce tarih alıyor.
        context.setInitParameter("jakarta.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE", Boolean.TRUE.toString());
        //xhtml kullanmadan doğrudan sayfa ismi ile ulaşılabiliyor url e
        context.setInitParameter("jakarta.faces.AUTOMATIC_EXTENSIONLESS_MAPPING", "true");
        //primefaces set theme
        context.setInitParameter("primefaces.THEME", "nova-light");

        //weld cdi container ı başlatıyor
        EnhancedListener cdiInitializer = new EnhancedListener();
        cdiInitializer.onStartup(null, context);

        //faces servleti başlatıyor
        ServletContainerInitializer facesInitializer = new FacesInitializer();
        facesInitializer.onStartup(null, context);
    }
}
