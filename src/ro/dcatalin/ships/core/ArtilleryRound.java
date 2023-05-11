package ro.dcatalin.ships.core;

public class ArtilleryRound {

	private String columnAddress;

	private String rowAddress;

	public ArtilleryRound() { }

	public ArtilleryRound(String artilleryRound) {
		this.columnAddress = artilleryRound.substring(0,1);
		this.rowAddress = artilleryRound.substring(1,2);
	}

	public String getColumnAddress() {
		return columnAddress;
	}

	public void setColumnAddress(String columnAddress) {
		this.columnAddress = columnAddress;
	}

	public String getRowAddress() {
		return rowAddress;
	}

	public void setRowAddress(String rowAddress) {
		this.rowAddress = rowAddress;
	}

	public boolean isValidAddress() {
		try {
			@SuppressWarnings("unused")
			int rowNbr = Integer.parseInt(this.rowAddress);
		} catch (Exception excp) {
			return false;
		}

		char colAddr = columnAddress.charAt(0);

		switch (colAddr) {
		case 65 :
			return true;
		case 66 :
			return true;
		case 67 :
			return true;
		case 68 :
			return true;
		case 69 :
			return true;
		case 70 :
			return true;
		case 71 :
			return true;
		case 72 :
			return true;
		case 73 :
			return true;
		case 74 :
			return true;
		default :
			return false;
		}
	}

	public boolean isEqual(ArtilleryRound artilleryRound) {
		if (!artilleryRound.isValidAddress()) return false;
		String column = artilleryRound.getColumnAddress();
		String line = artilleryRound.getRowAddress();
		if (! this.columnAddress.equals(column)) return false;
		if (! this.rowAddress.equals(line)) return false;
		return true;
	}

	@Override
	public String toString() {
		return columnAddress + rowAddress;
	}

	
}
