package ro.dcatalin.ships.core;

import java.util.ArrayList;
import java.util.HashMap;
//port java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


public class BattleField implements PlayGame {

	@Autowired
	private InterfShipsFleet fleetRepo;

	private HashMap<String,String> gameBoard;

	private SalvoResponse responseGenerator;

	private int score;

	public BattleField() {

		gameBoard = new HashMap<String,String>();

//		listOfCompartments = new HashMap<ArtilleryRound,String>();

		for (int colAddr=0; colAddr<10; colAddr++) {
			String blankLine = "..........";
			String rowAddress = String.valueOf(colAddr); 
			gameBoard.put(rowAddress, blankLine);
		}

		score = 0;
	}

	@Override
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void initGame() {
//		List<SubmarineShip> theFleet = new ArrayList<SubmarineShip>();
		responseGenerator = new VolleyResponse();
		
//		List<String> shipsCatalog = new ArrayList<String>(Arrays.asList("FlagShip", "Destroyer", "Submarine", "PT Boat"));
		ShipsFleet k215operationsFleet = fleetRepo.getRandomFleet();
		HashMap<String, String> listOfCompartmentStrings = k215operationsFleet.getListOfShips();
		responseGenerator.setFleet(listOfCompartmentStrings);

		for (String crtCompartment : listOfCompartmentStrings.keySet()) {
			this.setShipCompartment(new ArtilleryRound(crtCompartment));
		}

		score = responseGenerator.getScore();

	}

/*	private int setShip(SubmarineShip ship, String shipName) {
//		HashMap<ArtilleryRound,String> listOfCompartments = new HashMap<ArtilleryRound,String>();
		int initialScore = this.score;
    	Iterator<String> shipCompartments = ship.getCompartmentList().iterator();
    	while ( shipCompartments.hasNext() ) {
    		String crtCompartmentString = shipCompartments.next();
    		ArtilleryRound crtCompartment = new ArtilleryRound(crtCompartmentString);
    		setShipCompartment(crtCompartment);
//    		listOfCompartments.put(crtCompartment,shipName);
    	}
    	return this.score - initialScore;
    } */
 
    private String setSpot(String gridLine, String colAddress, String putChar) {
    	StringBuilder currentStripe = new StringBuilder();
    	ColumnMap columnsMap = new ColumnMap();
    	int colAddrNbr = columnsMap.getColumnIndex(colAddress.charAt(0));
    	for (int place = 0; place < 10; place++) {
    		if (place == colAddrNbr) {
    			currentStripe.append(putChar);
    		} else {
    			currentStripe.append(gridLine.charAt(place));
    		}
    	}
    	return currentStripe.toString();
    }

      private void setShipCompartment(ArtilleryRound compartment) {
    	ColumnMap columnMap = new ColumnMap();
    	String newGridRow = null;
    	String lineAddress = compartment.getRowAddress();
    	String colAddrString = compartment.getColumnAddress();
    	int colAddrNumber = columnMap.getColumnIndex(colAddrString.charAt(0));
    	String focusedLine = gameBoard.get(lineAddress);
    	String nextCompartment = showSpot(colAddrNumber, focusedLine);
    	if (nextCompartment.equals(".")) {
    		newGridRow = setSpot(focusedLine,colAddrString,"o");
    		gameBoard.replace(lineAddress, newGridRow);
    		
    		this.score++;
    	}
    }

    @Override
    public String getSalvoResponse(ShellVolley shellVolley) {

    	String finalMessage;
    	String markerType = getMarkedChar(shellVolley);

    	hitGrid(shellVolley.getFirstStrike(),markerType);
    	hitGrid(shellVolley.getSecndStrike(),markerType);
    	hitGrid(shellVolley.getLastStrike(),markerType);

    	finalMessage = responseGenerator.getHitShipMessage(shellVolley);
    	this.setScore(responseGenerator.getScore());

    	return finalMessage;
    }

    private String getMarkedLine(int colAddress, String explosionMarker, String line) {
    	StringBuilder newLine = new StringBuilder();
    	char spot = line.charAt(colAddress);

    	for (int positionInLine=0; positionInLine<10; positionInLine++) {
    		if (positionInLine != colAddress) {
    			newLine.append(line.charAt(positionInLine));
    		} else {
    			if (spot == 'o') {
    				newLine.append(explosionMarker);
    			} else {
    				newLine.append(explosionMarker);
    			}
    		}
    	}

    	return newLine.toString();
    }

    private void hitGrid(ArtilleryRound longShot, String explosionMarker) {
		ColumnMap columnMap = new ColumnMap();
//		prepares a line of the grid with X for explosion positions
		String newLine = null;
		String lineRank = longShot.getRowAddress();
		String colPosition = longShot.getColumnAddress();
		int shotAtCol = columnMap.getColumnIndex(colPosition.charAt(0));
		newLine = getMarkedLine(shotAtCol,explosionMarker,gameBoard.get(lineRank));
		gameBoard.replace(lineRank, newLine.toString());
	}

	private String showSpot(int colAddress, String row) {
		int upperLimit = 1 + colAddress;
		return row.substring(colAddress,upperLimit);
	}

	private String getMarkedChar(ShellVolley salvo) {

//		Iterator<ArtilleryRound> listOfShots = salvo.getVolley().iterator();
//		while (listOfShots.hasNext()) {
//			ArtilleryRound currentBlow = listOfShots.next();
//			if (listOfShips.containsKey(currentBlow)) {
//				return "x";
//			}
//		}

		if ( responseGenerator.isHit(salvo) ) {
			return "x";
		}

//		for (ArtilleryRound currentCompartment : listOfCompartments.keySet()) {
//			Iterator<ArtilleryRound> listOfShots = salvo.getVolley().iterator();
//			while (listOfShots.hasNext()) {
//				ArtilleryRound currentBlow = listOfShots.next();
//				if (currentBlow.isEqual(currentCompartment))
//					return "x";
//			}
//		}
		return " ";
	}


	@Override
	public String getStatus() {
		if (this.score == 0) {
			return "Game Over";
		}
		return "Still Playing";
	}


	private String maskLine(String currentLine, int lineRank) {
    	StringBuilder finalLine = new StringBuilder();

    	for (int colAddr = 0; colAddr<10; colAddr++) {
    		char crtTile = currentLine.charAt(colAddr);

    		if (crtTile == 'o') {
    			finalLine.append('.');
    		} else {
    			finalLine.append(currentLine.charAt(colAddr));
    		}
    		finalLine.append(" ");
    	}

    	return finalLine.toString().substring(0,19);
    }

	private String lineToString(String currentLine, int lineRank) {
		StringBuilder finalLine = new StringBuilder();

		for (int colAddr = 0; colAddr<10; colAddr++) {
			finalLine.append(currentLine.charAt(colAddr));
			finalLine.append(" ");
		}

		return finalLine.toString().substring(0,19);
	}

	@Override
    public String displayAnswer() {

    	StringBuilder crtBoard = new StringBuilder();
    	int lineRank = 0;

    	crtBoard.append("\n\n\n");
    	crtBoard.append("     A B C D E F G H I J");
    	crtBoard.append("\n");
    	crtBoard.append("     - - - - - - - - - -");
    	crtBoard.append("\n");

    	for (String rowAddr : gameBoard.keySet()) {
    		crtBoard.append(" ");
    		crtBoard.append(rowAddr);
    		crtBoard.append(" | ");
    		crtBoard.append(maskLine(gameBoard.get(rowAddr),lineRank));
    		crtBoard.append("\n");
    		lineRank++;
    	}

    	crtBoard.append("\n");
    	return crtBoard.toString();
    }

	@Override
    public String toString() {

    	StringBuilder crtBoard = new StringBuilder();
    	int lineRank = 0;

    	crtBoard.append("\n\n\n");
    	crtBoard.append("     A B C D E F G H I J");
    	crtBoard.append("\n");
    	crtBoard.append("     - - - - - - - - - -");
    	crtBoard.append("\n");

    	for (String rowAddr : gameBoard.keySet()) {
    		crtBoard.append(" ");
    		crtBoard.append(rowAddr);
    		crtBoard.append(" | ");
    		crtBoard.append(lineToString(gameBoard.get(rowAddr),lineRank));
    		crtBoard.append("\n");
    		lineRank++;
    	}

    	crtBoard.append("\n");
    	return crtBoard.toString();
    }

}
