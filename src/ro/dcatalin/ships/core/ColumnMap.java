package ro.dcatalin.ships.core;

public class ColumnMap {

	private char[] addrKey = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };

	public ColumnMap() { }

	public int getColumnIndex(char columnLetter) {
		for (int iNdex = 0; iNdex<19; iNdex++) {
			if (addrKey[iNdex]==columnLetter) return iNdex;
		}
		return 0;
	}

	public char getColumn(int index) {
		return addrKey[index];
	}
}
