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
public class GuardQuest extends Quest {
    
  public GuardQuest(AppStateManager stateManager){
    super(stateManager);
    }
  
  @Override
  public void act(){
    gui.showAlert(npc.getName(), "I'm the castle guard");
    }
    
}