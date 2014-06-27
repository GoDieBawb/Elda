/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.interactable;

import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.gui.GuiManager;
import mygame.player.Player;
import mygame.player.PlayerManager;
import mygame.scene.SceneManager;

/**
 *
 * @author Bob
 */
public class Rocks extends Interactable {
    
  public Rocks(Node interactable) {
    super(interactable);
    name       = "Rocks";
    message    = "Some strange rocks";
    actionName = "Check";
    }  
  
  @Override
  public void act(AppStateManager stateManager) {
    Player player = stateManager.getState(PlayerManager.class).player;
    
    if (player.equippedItem.getName().equals("Shovel")) {
      
      if (getUserData("Special") != null) {
        stateManager.getState(GuiManager.class).showAlert("Rocks", "You fall into a cave!");  
        Vector3f startSpot = new Vector3f(11, 2, -7);
        String scenePath   = "Scenes/Ruins.j3o"; 
        stateManager.getState(SceneManager.class).initScene(scenePath, startSpot);
        }
      
      else {
        stateManager.getState(GuiManager.class).showAlert("Rocks", "Nothing interesting here...");  
        }
        
      }
    
    else {
      stateManager.getState(GuiManager.class).showAlert("Rocks", "Something might be buried here...");  
      }
    
    }
    
  }
