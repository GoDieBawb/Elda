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
public class BarryQuest extends Quest {
    
  public BarryQuest(AppStateManager stateManager){
    super(stateManager);
    name = "SwordQuest";
    }
  
  @Override
  public void act(){
    
    Quest swordQuest = player.questList.getQuest(name);
    
    if (swordQuest ==  null) {
      swordQuest = new SwordQuest(stateManager);
      swordQuest.step = "Start";
      player.questList.add(swordQuest);
      }
    
    if (swordQuest.step.equals("Done")) {
      gui.showAlert(npc.getName(), "I heard you found a sword in the grave");
      }
    
    else {
      
      gui.showAlert(npc.getName(), "There are often items you can pick up and put in your inventory");
        
      }
      
    }
    
  }
