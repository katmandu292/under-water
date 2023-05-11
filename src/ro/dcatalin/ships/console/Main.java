package ro.dcatalin.ships.console;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ro.dcatalin.ships.core.AppConfig;


public class Main {

	public static ConfigurableApplicationContext context = null;

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.close();

	}

}
