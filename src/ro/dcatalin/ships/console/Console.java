package ro.dcatalin.ships.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import ro.dcatalin.ships.core.PlayGame;
import ro.dcatalin.ships.core.ShellVolley;

@Component
public class Console {

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	HashMap<Integer,String> shootingHistory = new HashMap<Integer,String>();
	HashMap<Integer,String> resultsHistory = new HashMap<Integer,String>();

	@Autowired
	private PlayGame currentTurf;

	@EventListener(ContextRefreshedEvent.class)
    public void start() throws IOException {
		String currentSalvo;
		String currentState;
		String feedBack;
		int blowsNbr = 0;
		ShellVolley shellVolley;
		currentTurf.initGame();
		currentState = currentTurf.getStatus();
		System.out.println("The Game is On. Show me what you can !\n");
		System.out.print(currentTurf.displayAnswer());

		while (blowsNbr < 21 ) {
			if (currentState.equalsIgnoreCase("Game over")) break;
			if (currentTurf.getScore() == 0) break;
			System.out.print("Send your best shot: ");
			currentSalvo = reader.readLine();
			blowsNbr++;
			shellVolley = new ShellVolley(currentSalvo);

			if ( shellVolley.isWellFormed() ) {
				feedBack = currentTurf.getSalvoResponse(shellVolley);
			} else {
				feedBack = "\nYou provided an invalid code";
			}

			System.out.println("\n\n\nVolley " + shellVolley.toString() + " resulted in " + feedBack);
			shootingHistory.put(blowsNbr,currentSalvo);
			resultsHistory.put(blowsNbr, feedBack);
			currentState = currentTurf.getStatus();
			System.out.print(currentTurf.displayAnswer());

			if (currentTurf.getScore() == 0) {
				break;
			}

			System.out.println(currentState);
		}

		if (currentTurf.getScore() == 0) {
			System.out.print("All ships sunken. You've won.");
			System.out.print(currentTurf.toString());
		} else {
			System.out.print("You've reached out maximum shots. You lose.");
		}
	}
}
