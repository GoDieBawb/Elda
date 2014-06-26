/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.interactable;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.gui.GuiManager;
import mygame.items.Bucket;
import mygame.player.Player;
import mygame.player.PlayerManager;
import mygame.quests.FarmerQuest;
import mygame.quests.Quest;

/**
 *
 * @author Bob
 */
public class HayField extends Interactable {
    
  public HayField(Node interactable) {
    super(interactable);
    name       = "HayField";
    actionName = "Grab";
    message    = "This is a HayField";
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
        
      if (player.equippedItem.getName().equals("Bucket")) {
        
        Bucket bucket = (Bucket) player.equippedItem;
        
        if (bucket.contents.equalsIgnoreCase("Water")) {
            
          stateManager.getState(GuiManager.class).showAlert("HayField", "You water the fields");
          player.hayPerm  = true;
          bucket.contents = "Empty";
          
          }
        
        else {
            
          stateManager.getState(GuiManager.class).showAlert("HayField", "Your bucket needs water");
          
          }
          
        } else {
          
        stateManager.getState(GuiManager.class).showAlert("HayField", "Looks like it could use some water");
          
        }
        
      }
      
    else {
        
      stateManager.getState(GuiManager.class).showAlert("HayField", "Looks like hay is growing here");
      
      }
    
    }    
    
}
