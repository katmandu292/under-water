package ro.dcatalin.ships.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubmarineShip {

	private List<String> compartmentList;

	private int floatingStatus;

	private String shipType;

	public SubmarineShip() {
		compartmentList = new ArrayList<String>();
		shipType = "undefined";
		floatingStatus = 0;
	}

	public SubmarineShip(String shipDefinition) {
		compartmentList = new ArrayList<String>();
		shipType = "undefined";
		floatingStatus = 0;
		int maxIndex = 0;
		switch (shipDefinition.length()) {
			case 5:
				maxIndex = 4;
				shipType = "Submarine";
				break;
			case 11:
				maxIndex = 10;
				shipType = "Destroyer";
				break;
			case 17:
				maxIndex = 16;
				shipType = "FlagShip";
				break;
			default:
				maxIndex = 2;
				shipType = "PT Boat";
		}

		for (int crtCharIndex=0; crtCharIndex<maxIndex;crtCharIndex=3+crtCharIndex) {
			StringBuilder crtLetter = new StringBuilder();
			crtLetter.append(shipDefinition.charAt(crtCharIndex));
			crtLetter.append(shipDefinition.charAt(1+crtCharIndex));
			String crtCompartment = crtLetter.toString();
			addCompartment(crtCompartment);
//			floatingStatus++;
		}
//		setCompartmentList(compartmentList);
	}

	public List<String> getCompartmentList() {
		return compartmentList;
	}

	public void setCompartmentList(List<String> compartmentList2) {
		Iterator<String> thisCompartmentList = this.compartmentList.iterator();
		Iterator<String> newCompartmentList = compartmentList2.iterator();
//		while (thisCompartmentList.hasNext()) {
//			String currentCompartment = thisCompartmentList.next();
//			this.compartmentList.remove(currentCompartment);
//			floatingStatus--;
//		}
		while (newCompartmentList.hasNext()) {
			String currentCompartment = thisCompartmentList.next();
			this.compartmentList.add(currentCompartment);
			floatingStatus++;
		}
	}

	public void addCompartment(String compartmentCoordinates) {
		if (compartmentCoordinates != null) {
			compartmentList.add(compartmentCoordinates);
			floatingStatus++;
		}
	}

	public String getCompartment(String compartment) {
		Iterator<String> thisCompartmentList = this.compartmentList.iterator();
		while (thisCompartmentList.hasNext()) {
			 String crtCompartment = thisCompartmentList.next();
			if (crtCompartment.equals(compartment)) {
				return crtCompartment;
			}
		}
		return null;
	}

	public boolean registerHit(String artilleryRound) {
		String blowAddress = getCompartment(artilleryRound);
		if (blowAddress == null) return false;
		floatingStatus--;
		return true;
	}

	public int getFloatingStatus() {
		return floatingStatus;
	}

	public void setFloatingStatus(int floatingStatus) {
		this.floatingStatus = floatingStatus;
	}

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	@Override
	public String toString() {
		StringBuilder thisShip = new StringBuilder();
		Iterator<String> compartments = this.compartmentList.iterator();
		int shipSize = this.compartmentList.size();
		int currentCompartment = 0;
		while (compartments.hasNext()) {
			thisShip.append(compartments.next().toString());
			currentCompartment++;
			if (currentCompartment == shipSize) break;
			thisShip.append("-");
		}

//		thisShip.append(compartments.next().toString());
		
		return thisShip.toString();
	}

	
}
