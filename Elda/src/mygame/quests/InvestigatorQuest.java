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
public class InvestigatorQuest extends Quest {
  
  public InvestigatorQuest(AppStateManager stateManager) {
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
    
    if (step.equals("MeetKing")) {
      }
    
    else if (step.equals("SearchRoom")) {
      gui.showAlert(npc.getName(), "Stay out of my way! The King may have asked you to help, but I'm the professional.");  
      }
    
    else if (step.equals("FoundPlan")) {
      gui.showAlert(npc.getName(), "You must inform the king of these plans immediately!");  
      }
    
    else {
      gui.showAlert(npc.getName(), "Please save Princess Elda");  
      }
      
    }
    
  }
