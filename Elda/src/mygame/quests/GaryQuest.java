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
public class GaryQuest extends Quest {
    
  public GaryQuest(AppStateManager stateManager){
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
      gui.showAlert(npc.getName(), "Using that shovel was a good idea");
      }
    
    else {
      
      gui.showAlert(npc.getName(), "Objects in the scene can often be interacted with");
        
      }
      
    }
    
  }
