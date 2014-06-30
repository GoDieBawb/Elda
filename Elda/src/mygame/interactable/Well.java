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

/**
 *
 * @author Bob
 */
public class Well extends Interactable {
    
  public Well(Node interactable) {
    super(interactable);
    name       = "Well";
    actionName = "Use";
    message    = "This well looks deep.";
    }
  
  @Override
  public void act(AppStateManager stateManager) {
    
    Player player = stateManager.getState(PlayerManager.class).player;
    
    if (player.equippedItem != null) {
    
      if (player.equippedItem.getName().equals("Bucket") ) {
        
        Bucket bucket = (Bucket) player.equippedItem;  
        
        if (bucket.contents.equalsIgnoreCase("Empty")) {
          stateManager.getState(GuiManager.class).showAlert("Well", "You fill your bucket");
          bucket.contents = "Water";
          }
        
        else {
          stateManager.getState(GuiManager.class).showAlert("Well", "You empty out the " + bucket.contents + " and you fill the bucket with water.");
          bucket.contents = "Water";
          }
        
        }
    
      else  {
          
        stateManager.getState(GuiManager.class).showAlert("Well", "You'll need a bucket");
        
        }
      
      } 
    
      else {
        
      stateManager.getState(GuiManager.class).showAlert("Well", "You'll need a bucket");  
      
      }
  
    }    
    
  }
