/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.quests;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.interactable.Door;
import mygame.scene.SceneManager;

/**
 *
 * @author Bob
 */
public class LarryQuest extends Quest {
  
    
  public LarryQuest(AppStateManager stateManager){
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
    
    if(swordQuest.step.equals("Done")){
      gui.showAlert(npc.getName(), "Be careful... The road is a dangerous place");
      }
    
    else if (swordQuest.step.equals("hasSword")) {
      gui.showAlert(npc.getName(), "Is that... a sword?! Looks like I can let you through");
      Door startGate = (Door) ((Node) stateManager.getState(SceneManager.class).scene.getChild("InteractableNode")).getChild("StartGate");
      startGate.locked = false;
      startGate.message  = "Gate to the road locked: " + startGate.locked;
      swordQuest.step = "Done";
      }
    
    else {
      
      gui.showAlert(npc.getName(), "The road is much too unsafe without a weapon... I can't let you through");
        
      }
      
    }
    
  }
