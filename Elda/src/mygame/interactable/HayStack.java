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
import mygame.quests.FarmerQuest;
import mygame.quests.Quest;

/**
 *
 * @author Bob
 */
public class HayStack extends Interactable {
    
  public HayStack(Node interactable) {
    super(interactable);
    name       = "HayStack";
    actionName = "Grab";
    message    = "Looks like a haystack";
    }
  
  @Override
  public void act(AppStateManager stateManager) {
    
    Player player = stateManager.getState(PlayerManager.class).player;
    
    Quest hayQuest = player.questList.getQuest("HayQuest");
    
    if (hayQuest == null) {
      hayQuest      = new FarmerQuest(stateManager);
      hayQuest.step = "Start";
      player.questList.add(hayQuest);
      }
    
    if (hayQuest.step.equals("Done")) {
    
      if (player.hayPerm) {
        stateManager.getState(GuiManager.class).showAlert("HayStack", "You take a bale of hay");  
        player.hayPerm = false;
        }
    
      else {
          
        stateManager.getState(GuiManager.class).showAlert("HayStack", "You're supposed to water the fields first");  
        
        }
      
    } else {
        
      stateManager.getState(GuiManager.class).showAlert("HayStack", "This isn't yours...");  
      
      }
    
    }
    
  }
