package app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import app.Flamingo;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import pkgCore.Action;
import pkgCore.GamePlay;
import pkgCore.Player;
import pkgCore.Table;
import pkgEnum.eAction;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class BlackJackController implements Initializable {
	
	private Flamingo FlamingoGame;
	
	@FXML private BorderPane mainScreen;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void setMainApp(Flamingo FlamingoGame)
	{
		this.FlamingoGame = FlamingoGame;
	}

	@FXML public void btnSitLeave_Click(ActionEvent event)
	{
		Button btn = (Button)event.getSource();
		
		Action act = new Action();
		
		act.setAction(btn.getText()=="Sit" ? eAction.Sit : eAction.Leave);
		Player p = FlamingoGame.getAppPlayer();	
		if (btn.getId() == "btnTop")
		{
			p.setiPlayerPosition(2);
			
		}
		else if (btn.getId() == "btnBottom")
		{
			p.setiPlayerPosition(0);
		}
		
		act.setPlayer(FlamingoGame.getAppPlayer());
		FlamingoGame.SendMessageToClient(act);
		
		//TODO: Implement this.  Create a new 'Action', send the 'Sit' or 'Leave' action to the Hub.
		
	}
	public void HandleTableState(Table t) {

		
		//TODO: Implement this.
	}

	public void HandleGameState(GamePlay gp) {

		//		Coming Soon....!
	}

}
