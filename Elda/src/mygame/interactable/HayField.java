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
public class HayField extends Interactable {
    
  public HayField(Node interactable) {
    super(interactable);
    name       = "HayField";
    actionName = "Grab";
    message    = "This is a HayField";
    }
  
  @Override
  public void act(AppStateManager stateManager) {
    stateManager.getState(GuiManager.class).showAlert("HayField", "Probably could use some water");
    }    
    
}
