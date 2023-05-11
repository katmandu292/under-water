package ro.dcatalin.ships.core;

public interface PlayGame {

	public void initGame();

//	public int setShip(SubmarineShip ship, String shipName);

	public String getSalvoResponse(ShellVolley salvo);

	public String displayAnswer();

	public String getStatus();

	public String toString();

	public int getScore();
}
