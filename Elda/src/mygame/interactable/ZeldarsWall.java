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
import mygame.scene.SceneManager;

/**
 *
 * @author Bob
 */
public class ZeldarsWall extends Interactable {
  
  public ZeldarsWall(Node interactable) {
    super(interactable);
    name       = "Strange Wall";
    actionName = "Check";
    message    = "This wall is strange";
    }
  
  @Override
  public void act(AppStateManager stateManager) {
    Player player = stateManager.getState(PlayerManager.class).player;
    removeFromParent();
    stateManager.getState(GuiManager.class).showAlert(name, "The wall opens!");
    stateManager.getState(SceneManager.class).addPhys();
    }
    
  }
