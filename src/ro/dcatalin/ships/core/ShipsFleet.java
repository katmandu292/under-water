package ro.dcatalin.ships.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
//port java.util.Spliterator;


public class ShipsFleet {

	private HashMap<String,String> listOfShips;

	private SubmarineShip thePtBoat;

	private SubmarineShip theDestroyer;

	private SubmarineShip theFlagShip;

	private SubmarineShip thetheBattleSub;

	private Integer overallScore;

	public ShipsFleet() {
		setListOfShips(new HashMap<String,String>());
		this.overallScore = 0;
	}

	public HashMap<String,String> getListOfShips() {
		return listOfShips;
	}

	public SubmarineShip getThePtBoat() {
		return thePtBoat;
	}

	public SubmarineShip getTheDestroyer() {
		return theDestroyer;
	}

	public SubmarineShip getTheFlagShip() {
		return theFlagShip;
	}

	public SubmarineShip getThetheBattleSub() {
		return thetheBattleSub;
	}

	public Integer getOverallScore() {
		return overallScore;
	}

	public void setListOfShips(HashMap<String,String> listOfShips) {
		this.listOfShips = listOfShips;
	}

	public void setThePtBoat(SubmarineShip thePtBoat) {
		this.thePtBoat = thePtBoat;
		addShip(thePtBoat);
	}

	public void setTheDestroyer(SubmarineShip theDestroyer) {
		this.theDestroyer = theDestroyer;
		addShip(theDestroyer);
	}

	public void setTheFlagShip(SubmarineShip theFlagShip) {
		this.theFlagShip = theFlagShip;
		addShip(theFlagShip);
	}

	public void setThetheBattleSub(SubmarineShip thetheBattleSub) {
		this.thetheBattleSub = thetheBattleSub;
		addShip(thetheBattleSub);
	}

	public void addShip(SubmarineShip vessel) {

		switch (vessel.getShipType()) {
		 case "FlagShip":
			 this.theFlagShip = new SubmarineShip(vessel.toString());
			 mapShip(vessel.getCompartmentList(), vessel.getShipType());
			 break;
		 case "Destroyer":
			 this.theDestroyer = new SubmarineShip(vessel.toString());
			 mapShip(vessel.getCompartmentList(), vessel.getShipType());
			 break;
		 case "Submarine":
			 this.thetheBattleSub = new SubmarineShip(vessel.toString());
			 mapShip(vessel.getCompartmentList(), vessel.getShipType());
			 break;
		 default:
			 this.thePtBoat = new SubmarineShip(vessel.toString());
			 mapShip(vessel.getCompartmentList(), vessel.getShipType());
		}
	}

	public void removeShip(SubmarineShip vessel, String shipType) {
		switch (vessel.getShipType()) {
		  case "FlagShip":
			  clearShip(vessel.getCompartmentList(), vessel.getShipType());
			  this.theFlagShip = new SubmarineShip();
			  break;
		  case "Destroyer":
			  clearShip(vessel.getCompartmentList(), vessel.getShipType());
			  this.theDestroyer = new SubmarineShip();
			  break;
		  case "Submarine":
			  clearShip(vessel.getCompartmentList(), vessel.getShipType());
			  this.thetheBattleSub = new SubmarineShip();
			  break;
		  default:
			  clearShip(vessel.getCompartmentList(), vessel.getShipType());
			  this.thePtBoat = new SubmarineShip();
		}
	}

	public void mapShip(List<String> compartmentList, String shipType) {
//		this.listOfShips.forEach((k,v) -> System.out.println("key: " + k + " value:" + v));
		Iterator<String> compartments = compartmentList.iterator();
		while (compartments.hasNext()) {
			this.listOfShips.put(compartments.next(),shipType);
			this.overallScore++;
		}
	}

	public void clearShip(List<String> inCompartmentList, String shipType) {
//		Spliterator sit = this.listOfShips.entrySet().spliterator();
		for (String crtCompartment : inCompartmentList) {
			if (listOfShips.containsKey(crtCompartment)) {
				this.listOfShips.remove(crtCompartment);
				this.overallScore--;
			}
		}

	}

	@Override
	public String toString() {
		return "ShipsFleet [" + listOfShips.toString() + "]";
	}

}
