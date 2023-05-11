package ro.dcatalin.ships.core;

import java.util.HashMap;

public class TestHashMap {

	static HashMap<String,String> compartmentsList = new HashMap<String,String>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		compartmentsList.put("B2","Flag Ship");
		compartmentsList.put("C2","Flag Ship");
		compartmentsList.put("B3","Flag Ship");
		compartmentsList.put("C3","Flag Ship");
		compartmentsList.put("B4","Flag Ship");
		compartmentsList.put("C4","Flag Ship");
		compartmentsList.put("H5","The Destroyer");
		compartmentsList.put("I5","The Destroyer");
		compartmentsList.put("H6","The Destroyer");
		compartmentsList.put("I6","The Destroyer");

		ArtilleryRound longShot = new ArtilleryRound("C2");

		System.out.println(compartmentsList);
		@SuppressWarnings("unused")
		int k218rank = 0;

		for (String i : compartmentsList.keySet()) {
			if (longShot.toString().equals(i)) {
				System.out.println(compartmentsList.get(i));
			}
			k218rank++;
		}

		@SuppressWarnings("unused")
		boolean y = compartmentsList.containsKey(longShot.toString());
		@SuppressWarnings("unused")
		String x = compartmentsList.get(longShot.toString());

	}

}
