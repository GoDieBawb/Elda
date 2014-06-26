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
public class WitnessQuest extends Quest {
  
  public WitnessQuest(AppStateManager stateManager) {
    super(stateManager);
    }
  
  @Override
  public void act() {
    
    Quest eldaQuest = player.questList.getQuest("EldaQuest");
    
    if (eldaQuest ==  null) {
      eldaQuest = new EldaQuest(stateManager);
      player.questList.add(eldaQuest);
      eldaQuest.step = "Start";
      }
    
    String step = eldaQuest.step;
    
    if (step.equals("Start")) {
      gui.showAlert(npc.getName(), "I saw Zeldar kidnappoing Princess Elda! Someone should tell the guard!");
      eldaQuest.step = "ZeldarMissing";
      }
    
    else if (step.equals("ZeldarMissing")) {
      gui.showAlert(npc.getName(), "Tell the guard that Zeldar has taken Princess Elda!");
      }
    
    else {
      gui.showAlert(npc.getName(), "Please save Princess Elda!");
      }
      
    }
    
  }
