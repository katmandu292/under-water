package ro.dcatalin.ships.core;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ro.dcatalin.ships.console.Console;


@Configuration
@ComponentScan(basePackages = "ro.dcatalin.ships")
public class AppConfig {

	@Bean
	public Console console() {
		return new Console();
	}

	@Bean
	public PlayGame battleField() {
		return new BattleField();
	}

	@Bean
	public InterfShipsFleet fleetRepo() {
		return new FleetRepository();
	}
}
