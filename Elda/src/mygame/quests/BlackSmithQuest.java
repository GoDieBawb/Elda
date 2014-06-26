/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.quests;

import com.jme3.app.state.AppStateManager;
import mygame.items.Food;

/**
 *
 * @author Bob
 */
public class BlackSmithQuest extends Quest {
    
  public BlackSmithQuest(AppStateManager stateManager){
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
      gui.showAlert(npc.getName(), "You have no business here have a nice day");    
      }
    
    else if (step.equals("FindSmith")) {
      gui.showAlert(npc.getName(), "So... The King needs my help. First I need some supplies. I need some food. Bring me meat");  
      eldaQuest.step = "GetMeat";
      }
    
    else if (step.equals("GetMeat")) {
      
      if (player.inventory.getItem("Food") !=  null) {
        
        Food meat = (Food) player.inventory.getItem("Food");
        
        if (meat.type.equalsIgnoreCase("Meat")) {
            
          gui.showAlert(npc.getName(), "That's what I needed");  
          eldaQuest.step = "FindBluePrints";  
          
          } else {
          gui.showAlert(npc.getName(), "I don't want bread... I want meat");    
          
          }
        
        } else {
        gui.showAlert(npc.getName(), "Bring me meat... From the Farmer and I'll help you");  
        
        }  
        
      }
    
    else if (step.equals("FindBluePrints")) {
      gui.showAlert(npc.getName(), "In the desert, there's an ancient cavern. There are some blueprints for a weapon... I need them");
      }
    
    else if (step.equals("HasBluePrints")) {
      gui.showAlert(npc.getName(), "I can't believe you found the blue prints");
      eldaQuest.step = "HasCannon";
      }
    
    else if (step.equals("HasCannon")) {
      gui.showAlert(npc.getName(), "I don't want bread... I want meat");
      }
    
    else {
      gui.showAlert(npc.getName(), "You have no business here... Leave");
      }
    
    }
    
  }
