/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.interactable;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.gui.GuiManager;

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
    stateManager.getState(GuiManager.class).showAlert("HayStack", "This isn't yours...");
    }
    
  }
