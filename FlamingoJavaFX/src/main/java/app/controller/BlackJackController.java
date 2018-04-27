package app.controller;

import java.net.URL;
import java.util.List;
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
import javafx.scene.control.*;

public class BlackJackController implements Initializable {

	private Flamingo FlamingoGame;
	@FXML private Label lblTopName;
	@FXML private Label lblBottomName;
	@FXML private Button btnTop;
	@FXML private Button btnBottom;
	

	@FXML
	private BorderPane mainScreen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMainApp(Flamingo FlamingoGame) {
		this.FlamingoGame = FlamingoGame;
	}

	@FXML
	public void btnSitLeave_Click(ActionEvent event) {
		Button btn = (Button) event.getSource();

		Action act = new Action();

		act.setAction(btn.getText().equals("Sit")  ? eAction.Sit : eAction.Leave);
		Player p = FlamingoGame.getAppPlayer();
		
		if (btn.getId().equals("btnTop")) {
			p.setiPlayerPosition(2);

		} else if (btn.getId().equals("btnBottom")) {
			p.setiPlayerPosition(0);
		}

		act.setPlayer(FlamingoGame.getAppPlayer());
		FlamingoGame.SendMessageToClient(act);

	}

	public void HandleTableState(Table t) {

		for(Player p : t.GetTablePlayers()) {
			if(p.getiPlayerPosition() == 2) {
				if(p.getiPokerClientID() == FlamingoGame.getAppPlayer().getiPokerClientID()) {
					btnTop.setText("Leave");
					lblTopName.setText(p.getPlayerName());
					btnBottom.setVisible(false);
				}
				else {
					btnTop.setVisible(false);
					lblTopName.setText(p.getPlayerName());
					//test
				}
			}
			else if(p.getiPlayerPosition() == 0) {
				if(p.getiPokerClientID() == FlamingoGame.getAppPlayer().getiPokerClientID()) {
					btnBottom.setText("Leave");
					lblBottomName.setText(p.getPlayerName());
					btnTop.setVisible(false);
				}
				else {
					btnBottom.setVisible(false);
					lblBottomName.setText(p.getPlayerName());
				}
			}
		}
		checkSeating(t);
	}
		
		

	public void checkSeating(Table t) {
		if(t.GetTablePlayers().size() == 0) {
			btnBottom.setText("Sit");
			lblBottomName.setText("");
			btnBottom.setVisible(true);
			btnTop.setText("Sit");
			lblTopName.setText("");
			btnTop.setVisible(true);
		}
		else if(t.GetTablePlayers().size() == 1 && t.GetTablePlayers().get(0).getiPlayerPosition() != 2) {
			btnTop.setText("Sit");
			lblTopName.setText("");
			
		}
		else if(t.GetTablePlayers().size() == 1 && t.GetTablePlayers().get(0).getiPlayerPosition() != 0) {
			btnBottom.setText("Sit");
			lblBottomName.setText("");
			
		}

	}
	public void HandleGameState(GamePlay gp) {

		// Coming Soon....!
	}

}
