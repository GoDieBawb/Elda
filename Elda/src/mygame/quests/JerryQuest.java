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
public class JerryQuest extends Quest {
    
  public JerryQuest(AppStateManager stateManager){
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
      gui.showAlert(npc.getName(), "Looks like you've completed your first quest!");
      }
    
    else {
      
      gui.showAlert(npc.getName(), "Npcs want to talk! Make sure you listen to what they say!");
        
      }
      
    }
    
  }
