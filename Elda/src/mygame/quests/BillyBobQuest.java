/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.quests;

import com.jme3.app.state.AppStateManager;
import mygame.items.Bucket;

/**
 *
 * @author Bob
 */
public class BillyBobQuest extends Quest{
    
  public BillyBobQuest(AppStateManager stateManager){
    super(stateManager);
    }
  
  @Override
  public void act(){
    
    Quest hayQuest = player.questList.getQuest("HayQuest");
    
    if (hayQuest == null) {
      hayQuest      = new HayQuest(stateManager);
      hayQuest.step = "Start";
      player.questList.add(hayQuest);
      }
    
    if (hayQuest.step.equals("Start")) {
      gui.showAlert(npc.getName(), "The road has been blocked by monsters and I can't get my milk...");
      hayQuest.step = "GetMilk";
      }
    
    else if (hayQuest.step.equals("GetMilk")) {
      
    if (player.equippedItem != null) {
    
      if (player.equippedItem.getName().equals("Bucket") ) {
        
        Bucket bucket = (Bucket) player.equippedItem;  
        
        if (bucket.contents.equalsIgnoreCase("Milk")) {
            
          gui.showAlert(npc.getName(), "That's exactly what I needed... If you need Hay just water my fields");
          hayQuest.step = "Done";
          
        } else {
            
          gui.showAlert(npc.getName(), "I need you to bring me some milk from the farmer");
          
          }
        
        } else {
        gui.showAlert(npc.getName(), "I need you to bring me some milk from the farmer");    
        }
      
      } else {
      gui.showAlert(npc.getName(), "I need you to bring me some milk from the farmer");  
      }
    
    } else if (hayQuest.step.equals("Done")) { 
        
      gui.showAlert(npc.getName(), "If you ever need hay just water my fields and grab a bale");  
    
      } 
    
    } 
  
  }
