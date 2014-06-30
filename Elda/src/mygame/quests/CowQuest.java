/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.quests;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.gui.GuiManager;
import mygame.interactable.Door;
import mygame.items.Bucket;

/**
 *
 * @author Bob
 */
public class CowQuest extends Quest {
    
  public CowQuest(AppStateManager stateManager) {
    super(stateManager);
    name = "FarmerQuest";
    }
  
  @Override
  public void act(){
      
    Quest farmerQuest = player.questList.getQuest(name);
    
    if (farmerQuest == null) {
      farmerQuest      = new FarmerQuest(stateManager);
      farmerQuest.step = "Start";
      player.questList.add(farmerQuest);
      }
    
    if (farmerQuest.step.equals("Start")) {
      gui.showAlert(npc.getName(), "Moo");
      }
    
    else if (farmerQuest.step.equals("HelpCow")) {
     
     Node monsterNode = (Node) npc.getParent().getParent().getChild("MonsterNode");
        
     if (monsterNode.getChildren().isEmpty()) {
       
       gui.showAlert(npc.getName(), "The cow is relieved");
       ((Door) ((Node) npc.getParent().getParent().getChild("InteractableNode")).getChild("DairyGate")).locked = false;
       farmerQuest.step = "SavedCow";  
         
       } else {
         
       gui.showAlert(npc.getName(), "The cow is panicking!");
         
       }
        
      }
    
    else if (farmerQuest.step.equals("Done")) {
      
      if (player.equippedItem.getName().equals("Bucket")) {
          
        Bucket bucket = (Bucket) player.equippedItem;
        
        if (bucket.contents.equalsIgnoreCase("Empty")) {
            
          gui.showAlert("Cow", "You fill your bucket with milk");
          bucket.contents = "Milk";
          
          } else {
          gui.showAlert("Cow", "Your you empty the " + bucket.contents + "and refill with fresh milk");  
          bucket.contents = "Milk";
          }         
        
          
        } else {
        gui.showAlert("Cow", "Moo");  
        }
        
      }
    
    else {
      gui.showAlert(npc.getName(), "Moo");
      }
      
    }
    
  }
