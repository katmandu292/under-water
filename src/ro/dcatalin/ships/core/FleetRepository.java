package ro.dcatalin.ships.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FleetRepository implements InterfShipsFleet{

	private List<ShipsFleet> listOfSquadrons;

	public FleetRepository() {
		listOfSquadrons = new ArrayList<ShipsFleet>();

		ShipsFleet squadronOne = new ShipsFleet();
		squadronOne.addShip(new SubmarineShip("B2-C2-B3-C3-B4-C4"));
		squadronOne.addShip(new SubmarineShip("G2-H2-G3-H3"));
		squadronOne.addShip(new SubmarineShip("G7-H8"));
		squadronOne.addShip(new SubmarineShip("F5"));
		addFleet(squadronOne);

		ShipsFleet squadronTwo = new ShipsFleet();
		squadronTwo.addShip(new SubmarineShip("B6-C6-D6-B7-C7-D7"));
		squadronTwo.addShip(new SubmarineShip("H1-I1-H2-I2"));
		squadronTwo.addShip(new SubmarineShip("C1-B2"));
		squadronTwo.addShip(new SubmarineShip("G8"));
		addFleet(squadronTwo);

		ShipsFleet squadronThree = new ShipsFleet();
		squadronThree.addShip(new SubmarineShip("D2-E2-F2-D3-E3-F3"));
		squadronThree.addShip(new SubmarineShip("B6-C6-B7-C7"));
		squadronThree.addShip(new SubmarineShip("B0-A1"));
		squadronThree.addShip(new SubmarineShip("I6"));
		addFleet(squadronThree);

		ShipsFleet squadronFour = new ShipsFleet();
		squadronFour.addShip(new SubmarineShip("B1"));
		squadronFour.addShip(new SubmarineShip("G1-H1-G2-H2"));
		squadronFour.addShip(new SubmarineShip("B7-C8"));
		squadronFour.addShip(new SubmarineShip("F7-G7-H7-F8-G8-H8"));
		addFleet(squadronFour);

		ShipsFleet squadronFive = new ShipsFleet();
		squadronFive.addShip(new SubmarineShip("D1-E1-F1-D2-E2-F2"));
		squadronFive.addShip(new SubmarineShip("B5"));
		squadronFive.addShip(new SubmarineShip("H4-I5"));
		squadronFive.addShip(new SubmarineShip("F7-G7-F8-G8"));
		addFleet(squadronFive);

		ShipsFleet squadronSix = new ShipsFleet();
		squadronSix.addShip(new SubmarineShip("A0"));
		squadronSix.addShip(new SubmarineShip("H0-I0-H1-I1"));
		squadronSix.addShip(new SubmarineShip("A6-B6-C6-A7-B7-C7"));
		squadronSix.addShip(new SubmarineShip("J6-I7"));
		addFleet(squadronSix);

		ShipsFleet squadronSeven = new ShipsFleet();
		squadronSeven.addShip(new SubmarineShip("I2-J2-I3-J3"));
		squadronSeven.addShip(new SubmarineShip("B3-C4"));
		squadronSeven.addShip(new SubmarineShip("E6"));
		squadronSeven.addShip(new SubmarineShip("G6-H6-G7-H7-G8-H8"));
		addFleet(squadronSeven);

		ShipsFleet squadronEight = new ShipsFleet();
		squadronEight.addShip(new SubmarineShip("G2-H2-G3-H3"));
		squadronEight.addShip(new SubmarineShip("C3-D3-C4-D4-C5-D5"));
		squadronEight.addShip(new SubmarineShip("B7"));
		squadronEight.addShip(new SubmarineShip("F7-E8"));
		addFleet(squadronEight);

		ShipsFleet squadronNine = new ShipsFleet();
		squadronNine.addShip(new SubmarineShip("E2-F3"));
		squadronNine.addShip(new SubmarineShip("B4-C4-B5-C5"));
		squadronNine.addShip(new SubmarineShip("C8"));
		squadronNine.addShip(new SubmarineShip("I7-J7-I8-J8-I9-J9"));
		addFleet(squadronNine);

		ShipsFleet squadronTen = new ShipsFleet();
		squadronTen.addShip(new SubmarineShip("E0-D1"));
		squadronTen.addShip(new SubmarineShip("H3-I3-J3-H4-I4-J4"));
		squadronTen.addShip(new SubmarineShip("B7"));
		squadronTen.addShip(new SubmarineShip("E7-F7-E8-F8"));
		addFleet(squadronTen);

		ShipsFleet squadronEleven = new ShipsFleet();
		squadronEleven.addShip(new SubmarineShip("A4-B5"));
		squadronEleven.addShip(new SubmarineShip("D4-E4-D5-E5-D6-E6"));
		squadronEleven.addShip(new SubmarineShip("H9"));
		squadronEleven.addShip(new SubmarineShip("G1-I1-G2-I2"));
		addFleet(squadronEleven);

		ShipsFleet squadronTwelve = new ShipsFleet();
		squadronTwelve.addShip(new SubmarineShip("A8-B7"));
		squadronTwelve.addShip(new SubmarineShip("E4-E4-G4-E5-F5-G5"));
		squadronTwelve.addShip(new SubmarineShip("D1"));
		squadronTwelve.addShip(new SubmarineShip("G0-H0-G1-H1"));
		addFleet(squadronEleven);

		ShipsFleet squadronThirteen = new ShipsFleet();
		squadronThirteen.addShip(new SubmarineShip("H1-I2"));
		squadronThirteen.addShip(new SubmarineShip("B1-C1-B2-C2-B3-C3"));
		squadronThirteen.addShip(new SubmarineShip("H8"));
		squadronThirteen.addShip(new SubmarineShip("B5-C5-B6-C6"));
		addFleet(squadronThirteen);

		ShipsFleet squadronFourteen = new ShipsFleet();
		squadronFourteen.addShip(new SubmarineShip("D4-E4-F4-D5-E5-F5"));
		squadronFourteen.addShip(new SubmarineShip("D1"));
		squadronFourteen.addShip(new SubmarineShip("G8-H7"));
		squadronFourteen.addShip(new SubmarineShip("A8-B8-A9-B9"));
		addFleet(squadronFourteen);
	}

	public void addFleet(ShipsFleet squadron) {
		if (listOfSquadrons == null) {
			listOfSquadrons = new ArrayList<ShipsFleet>();
			listOfSquadrons.add(squadron);
		} else {
			listOfSquadrons.add(squadron);
		}
	}
	
	@Override
	public Integer howManyItems() {
		return listOfSquadrons.size();
	}

	@Override
	public List<ShipsFleet> getAllFleets() {
		return listOfSquadrons;
	}

	@Override
	public ShipsFleet getFirstFleet() {
		return listOfSquadrons.get(0);
	}

	@Override
	public ShipsFleet getLastFleet() {
		Integer maxIndex = listOfSquadrons.size() - 1;
		return listOfSquadrons.get(maxIndex);
	}

	@Override
	public ShipsFleet getRandomFleet() {
		int nbrOfSquadrons = listOfSquadrons.size() - 1;
		Random rand = new Random();
		int qIndex = rand.nextInt(nbrOfSquadrons);
		return listOfSquadrons.get(qIndex);
	}

	public void setListOFSquasdrons(ArrayList<ShipsFleet> listOFSquasdrons) {
		this.listOfSquadrons = listOFSquasdrons;
	}

	@Override
	public String toString() {
		return "FleetRepository [" + listOfSquadrons.toString() + "]";
	}
}
