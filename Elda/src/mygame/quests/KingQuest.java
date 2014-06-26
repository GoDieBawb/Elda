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
public class KingQuest extends Quest {
    
  public KingQuest(AppStateManager stateManager){
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
      gui.showAlert(npc.getName(), "You shouldn't be in here");
      }
    
    else if (step.equals("MeetKing")) {
      gui.showAlert(npc.getName(), "Zeldar was seen taking Princess Elda?");
      eldaQuest.step = "SearchRoom";
      }
    
    else if (step.equals("SearchRoom")) {
      gui.showAlert(npc.getName(), "Go assist the investigator... You'll find him in Zeldar's Room");
      }
    
    else if (step.equals("FoundPlan")) {
      gui.showAlert(npc.getName(), "So... He took her to the caves...");
      eldaQuest.step = "FindSmtih";
      }
    
    else if (step.equals("FindSmith")) {
      gui.showAlert(npc.getName(), "The Blacksmith in the lost woods can only make a weapon strong enought to beat Zeldar");   
      }
    
    else {
      gui.showAlert(npc.getName(), "Save Princess Elda!");  
      }
      
    }
    
}
