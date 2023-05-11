package ro.dcatalin.ships.core;

import java.util.HashMap;

public interface SalvoResponse {

	public void setFleet(HashMap<String,String> fleet);

	public String getHitShipMessage(ShellVolley shellVolley);

	public boolean isHit(ShellVolley shellVolley);

	public int getScore();
}
