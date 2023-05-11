package ro.dcatalin.ships.core;

import java.util.List;

public interface InterfShipsFleet {

	public List<ShipsFleet> getAllFleets();

	public ShipsFleet getFirstFleet();

	public ShipsFleet getLastFleet();

	public ShipsFleet getRandomFleet();

	public Integer howManyItems();

}
