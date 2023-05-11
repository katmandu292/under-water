package ro.dcatalin.ships.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VolleyResponse implements SalvoResponse {

	private HashMap<String,String> listOfCompartments;

	private List<SubmarineShip> theFleet;

	private List<String> shipsCatalog;

	private int score;

	public VolleyResponse() {
		this.theFleet = new ArrayList<SubmarineShip>();
		shipsCatalog = new ArrayList<String>();
		score = 0;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public boolean isHit(ShellVolley shellVolley) {

		List<String> listOfBlowsString = new ArrayList<String>();
		listOfBlowsString = shellVolley.toListOfString();

		for (String currentShot : listOfBlowsString) {
			if ( listOfCompartments.containsKey(currentShot) ) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void setFleet(HashMap<String, String> fleet) {

		SubmarineShip theFlagShip = new SubmarineShip();
		SubmarineShip theDestroyer = new SubmarineShip();
		SubmarineShip theSubmarine = new SubmarineShip();
		SubmarineShip thePtBoat = new SubmarineShip();

		for (String currentCompartment : fleet.keySet()) {
			String currentShip = fleet.get(currentCompartment);
			switch (currentShip) {
				case "PT Boat":
					thePtBoat.addCompartment(currentCompartment);
					thePtBoat.setShipType(currentShip);
					score++;
					break;
				case "Submarine":
					theSubmarine.addCompartment(currentCompartment);
					theSubmarine.setShipType(currentShip);
					score++;
					break;
				case "Destroyer":
					theDestroyer.addCompartment(currentCompartment);
					theDestroyer.setShipType(currentShip);
					score++;
					break;
				default:
					theFlagShip.addCompartment(currentCompartment);
					theFlagShip.setShipType(currentShip);
					score++;
			}

		}

		this.listOfCompartments = fleet;
//		at this point the overall score is already thirteen
		theFleet.add(thePtBoat);
		theFleet.add(theDestroyer);
		theFleet.add(theSubmarine);
		theFleet.add(theFlagShip);

		this.setShipsCatalog();
	}

	@Override
	public String getHitShipMessage(ShellVolley shellVolley) {
		HashMap<String,Integer> hitMap = new HashMap<String,Integer>();
		String salvoResponse;
		List<Integer> fleetScore = getFleetScore(shellVolley);

		boolean scoredHit = getSalvoScore(fleetScore);

		int shipIndex = 0;
		for (String theShip : shipsCatalog) {
			hitMap.put(theShip, fleetScore.get(shipIndex));
			shipIndex++;
		}

		salvoResponse = getShipResponse(this.theFleet, hitMap);

		for (int vesselNbr = 0; vesselNbr<theFleet.size(); vesselNbr++) {
			SubmarineShip currentShip = theFleet.get(vesselNbr);
			if (currentShip.getFloatingStatus() == 0 ) {
				theFleet.remove(vesselNbr);
				this.removeShip(currentShip.getShipType());
			}
		}

		if ( scoredHit ) return trimEndOfString(salvoResponse);

		return "Nothing. All rounds missed.";
	}

	private String getShipResponse(List<SubmarineShip> theFleet, HashMap<String,Integer> theIndividualResponse) {
    	StringBuilder salvoResponse = new StringBuilder();
    	for (SubmarineShip currentShip : theFleet) {
    		String shipType = currentShip.getShipType();
    		Integer hitsNbr = theIndividualResponse.get(shipType);
    		if (currentShip.getFloatingStatus() == 0 ) {
    			salvoResponse.append(shipType);
    			salvoResponse.append(" is sunken, ");
    		} else if (hitsNbr > 1) {
    			salvoResponse.append(hitsNbr);
    			salvoResponse.append(" hits to the ");
    			salvoResponse.append(shipType);
    			salvoResponse.append(", ");
    		} else if (hitsNbr == 1) {
    			salvoResponse.append("One hit to the ");
    			salvoResponse.append(shipType);
    			salvoResponse.append(", ");
    		}
    		
    	}
    	return salvoResponse.toString();
    }

	public List<Integer> getFleetScore(ShellVolley shellVolley) {

		List<ArtilleryRound> listOfBlowsComplex = shellVolley.toListOfRounds();
		List<String> listOfBlowsString = shellVolley.toListOfString();
		int blowIndex = -1;
		List<Integer> fleetScore = new ArrayList<Integer>();

		for (int vesselNbr = 0; vesselNbr<this.theFleet.size(); vesselNbr++) {
			SubmarineShip tempShip = this.theFleet.get(vesselNbr);
			int shipScore = 0;

			for (blowIndex=0;blowIndex<3;blowIndex++) {
				String theBlow = listOfBlowsString.get(blowIndex);
				String hitShipIdentity = getShipHitName(theBlow);

				if ( hitShipIdentity.equals("Nothing") ) {
					continue;
				}

				ArtilleryRound thisShot = listOfBlowsComplex.get(blowIndex);

				if ( hitShipIdentity.equals(tempShip.getShipType()) ) {
					if ( tempShip.registerHit(thisShot.toString()) ) {
						this.score--;
						shipScore++;
						listOfCompartments.remove(theBlow);
					}
				}
			}

			fleetScore.add(shipScore);
		}

    	return fleetScore;
	}

	private String getShipHitName(String longShot) {
		String blownShipName = null;

		@SuppressWarnings("unused")
		int compartmentIndex = -1;

		for (String currentCompartment : listOfCompartments.keySet()) {
			compartmentIndex++;

			if ( currentCompartment.equalsIgnoreCase(longShot) ) {
				blownShipName = listOfCompartments.get(currentCompartment);
//				listOfCompartments.remove(currentCompartment);
				break;
			}
		}

		if (blownShipName == null) {
			return "Nothing";
		}

		return blownShipName;
	}

    private boolean getSalvoScore(List<Integer> shipScore) {

    	int theScore = 0;
    	for (int iNdex=0; iNdex<shipScore.size(); iNdex++) {
    		theScore = shipScore.get(iNdex) + theScore;
    	}
    	return (theScore>0);
    }

    private String trimEndOfString(String theMessage) {
    	StringBuilder trimmedResponse = new StringBuilder();
    	int stringLength = theMessage.length();
    	int lastCharIndex = stringLength-2;
    	String finish = theMessage.substring(lastCharIndex);
    	trimmedResponse.append(theMessage.substring(0,stringLength-2));

    	if (finish.contentEquals(", ")) {
    		trimmedResponse.append(".");
    		return trimmedResponse.toString();
    	}

    	return theMessage;
    }

    private void setShipsCatalog() {
    	for (SubmarineShip theBoat : this.theFleet) {
    		this.shipsCatalog.add(theBoat.getShipType());
    	}
    }

    private void removeShip(String theShip) {
    	this.shipsCatalog.remove(theShip);
    }
}
