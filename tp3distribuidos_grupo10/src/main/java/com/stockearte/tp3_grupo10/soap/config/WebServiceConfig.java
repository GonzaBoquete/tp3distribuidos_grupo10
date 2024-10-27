package com.stockearte.tp3_grupo10.soap.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	@Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

	@Bean(name = "catalogo")
    public DefaultWsdl11Definition catalogoWsdl11Definition(@Qualifier("catalogosSchema") XsdSchema catalogoSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CatalogoPort");
        wsdl11Definition.setLocationUri("/ws/catalogo");
        wsdl11Definition.setTargetNamespace("http://www.stockearte.com/tp3_grupo10/soap/interfaces");
        wsdl11Definition.setSchema(catalogoSchema);
        return wsdl11Definition;
    }
	
	@Bean(name = "filtro")
    public DefaultWsdl11Definition filtroWsdl11Definition(@Qualifier("filtrosSchema") XsdSchema filtroSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("FiltroPort");
        wsdl11Definition.setLocationUri("/ws/filtro");
        wsdl11Definition.setTargetNamespace("http://www.stockearte.com/tp3_grupo10/soap/interfaces");
        wsdl11Definition.setSchema(filtroSchema);
        return wsdl11Definition;
    }
	
	@Bean(name = "ordenDeCompra")
    public DefaultWsdl11Definition ordenDeCompraWsdl11Definition(@Qualifier("ordenesDeCompraSchema") XsdSchema ordenDeCompraSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("OrdenDeCompraPort");
        wsdl11Definition.setLocationUri("/ws/ordenDeCompra");
        wsdl11Definition.setTargetNamespace("http://www.stockearte.com/tp3_grupo10/soap/interfaces");
        wsdl11Definition.setSchema(ordenDeCompraSchema);
        return wsdl11Definition;
    }
	
    @Bean(name = "producto")
    public DefaultWsdl11Definition productoWsdl11Definition(@Qualifier("productosSchema") XsdSchema productosSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ProductoPort");
        wsdl11Definition.setLocationUri("/ws/producto");
        wsdl11Definition.setTargetNamespace("http://www.stockearte.com/tp3_grupo10/soap/interfaces");
        wsdl11Definition.setSchema(productosSchema);
        return wsdl11Definition;
    }

    @Bean(name = "tienda")
    public DefaultWsdl11Definition tiendaWsdl11Definition(@Qualifier("tiendasSchema") XsdSchema tiendasSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("TiendaPort");
        wsdl11Definition.setLocationUri("/ws/tienda");
        wsdl11Definition.setTargetNamespace("http://www.stockearte.com/tp3_grupo10/soap/interfaces");
        wsdl11Definition.setSchema(tiendasSchema);
        return wsdl11Definition;
    }
    
    @Bean(name = "usuario")
    public DefaultWsdl11Definition usuarioWsdl11Definition(@Qualifier("usuariosSchema") XsdSchema usuariosSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UsuarioPort");
        wsdl11Definition.setLocationUri("/ws/usuario");
        wsdl11Definition.setTargetNamespace("http://www.stockearte.com/tp3_grupo10/soap/interfaces");
        wsdl11Definition.setSchema(usuariosSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema catalogosSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/catalogo.xsd"));
    }
    
    @Bean
    public XsdSchema filtrosSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/filtro.xsd"));
    }
    
    @Bean
    public XsdSchema ordenesDeCompraSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/ordenDeCompra.xsd"));
    }
    
    @Bean
    public XsdSchema productosSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/producto.xsd"));
    }

    @Bean
    public XsdSchema tiendasSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/tienda.xsd"));
    }
    
    @Bean
    public XsdSchema usuariosSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/usuario.xsd"));
    }
    
}