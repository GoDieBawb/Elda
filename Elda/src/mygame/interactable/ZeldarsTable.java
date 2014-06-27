/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.interactable;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.gui.GuiManager;
import mygame.player.Player;
import mygame.player.PlayerManager;

/**
 *
 * @author Bob
 */
public class ZeldarsTable extends Interactable {
    
  public ZeldarsTable(Node interactable) {
    super(interactable);
    name       = "Zeldars Table";
    actionName = "Check";
    message    = "This table is covered with papers... Evil looking papers";
    }
  
  @Override
  public void act(AppStateManager stateManager) {
      
    Player player = stateManager.getState(PlayerManager.class).player;
    stateManager.getState(GuiManager.class).showAlert(name, "This table shows well laid plans for Zeldar to kidnap the princess...");
    
    if (player.questList.getQuest("EldaQuest").step.equalsIgnoreCase("SearchRoom"))
    player.questList.getQuest("EldaQuest").step = "FoundPlan";
    
    }    
    
}
