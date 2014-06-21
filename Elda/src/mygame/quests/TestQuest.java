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
public class TestQuest extends Quest {
    
  private Node scene;  
    
  public TestQuest(AppStateManager stateManager){
    super(stateManager);
    name  = "TestQuest";
    scene = stateManager.getState(SceneManager.class).scene;
    } 
    
   @Override
   public void act(){
     
     if (player.questList.getQuest(name) == null) {
       gui.showAlert(npc.getName(), "My field is overrun by monsters!");
       player.questList.add(this);
       step = "Start";
       }
     
     else if (player.questList.getQuest(name).step.equals("Start")) {
       
       Node monsterNode = (Node) scene.getChild("MonsterNode");
       int  monsterCount  = monsterNode.getQuantity();
       
       if (monsterCount != 0) {
         gui.showAlert(npc.getName(), "You still have " + monsterCount + " monsters left to kill");
         }
       
       else {
         gui.showAlert(npc.getName(), "Thanks for killing those monsters!");
         Door testDoor   = (Door) ((Node) scene.getChild("InteractableNode")).getChild("TestDoor");
         testDoor.locked = false;
         testDoor.message = "Door to Bobs test scene locked: " + testDoor.locked;
         step = "Done";
         }
       
       }
     
     else if (player.questList.getQuest(name).step.equals("Done")){
       gui.showAlert(npc.getName(), "Thanks for the help with the monsters!");
       }
     
     }
    
  }
