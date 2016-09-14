package test_webservice.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.spring.SpringResourceFactory;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author predix -
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml",
		"classpath:META-INF/cxf/cxf-servlet.xml" })
public class App {

	@Autowired
	private ApplicationContext ctx;

	/**
	 * @param args
	 *          -
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/**
	 * @param context
	 * @return -
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean(
			ApplicationContext context) {
		return new ServletRegistrationBean(new CXFServlet(), "/services/*"); //$NON-NLS-1$
	}

	/**
	 * @return -
	 */
	@Bean
	public Server jaxRsServer() {
		List<ResourceProvider> resourceProviders = new LinkedList<ResourceProvider>();
		for (String beanName : this.ctx.getBeanDefinitionNames()) {
			if (this.ctx.findAnnotationOnBean(beanName, Path.class) != null) {
				SpringResourceFactory factory = new SpringResourceFactory(
						beanName);
				factory.setApplicationContext(this.ctx);
				resourceProviders.add(factory);
			}
		}
		if (resourceProviders.size() > 0) {
			JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
			factory.setBus(this.ctx.getBean(SpringBus.class));
			factory.setProviders(Arrays.asList(new JacksonJsonProvider()));
			factory.setResourceProviders(resourceProviders);
			return factory.create();
		}
		return null;
	}
}
