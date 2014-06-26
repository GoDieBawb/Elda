/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.quests;

import com.jme3.app.state.AppStateManager;

/**
 *
 * @author Bob
 */
public class BlackSmithQuest extends Quest {
    
  public BlackSmithQuest(AppStateManager stateManager){
    super(stateManager);
    }
  
  @Override
  public void act(){
    gui.showAlert(npc.getName(), "Hello I'm the Blacksmith");
    }
    
}
