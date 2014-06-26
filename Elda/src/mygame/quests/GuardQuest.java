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
      
    Quest eldaQuest = player.questList.getQuest("EldaQuest");
    
    if (eldaQuest ==  null) {
      eldaQuest = new EldaQuest(stateManager);
      player.questList.add(eldaQuest);
      eldaQuest.step = "Start";
      }
    
    String step = eldaQuest.step;
    
    if (step.equals("Start")) {
      gui.showAlert(npc.getName(), "No one is allowed inside the Castle. Princess Elda has gone missing!");    
      }
    
    else if (step.equals("ZeldarMissing")) {
      gui.showAlert(npc.getName(), "Zeldar was seen kidnapping Princess Elda? Go inform the king!");  
      eldaQuest.step = "MeetKing";
      }

    else if (step.equals("MeetKing")) {
      gui.showAlert(npc.getName(), "You must inform the king immediately!");    
      }
    
    else {
      gui.showAlert(npc.getName(), "Please save Princess Elda!");  
      }
      
    }
    
}
