/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.quests;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.interactable.Door;
import mygame.monsters.Monster;
import mygame.monsters.MonsterManager;
import mygame.monsters.Spider;
import mygame.npcs.Npc;
import mygame.scene.SceneManager;

/**
 *
 * @author Bob
 */
public class FarmerQuest extends Quest {
  
  public FarmerQuest(AppStateManager stateManager){
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
      gui.showAlert(npc.getName(), "Hi there I'm the farmer of MissionDairy");
      gui.delayAlert(npc.getName(), "Something is bothering my cow... Bring you sword and go check it out", 2);
      
      for (int i = 0; i < 3; i++) {
        Node    spiderModel = (Node) stateManager.getApplication().getAssetManager().loadModel("Models/Creatures/spider.j3o");
        Monster spider      = new Spider(spiderModel, stateManager);
        spiderModel.scale(.3f);
        spider.attachChild(spiderModel);
        stateManager.getState(MonsterManager.class).monsterNode.attachChild(spider);
        stateManager.getState(MonsterManager.class).monsterNodeClean();
        spider.startSpot = ((Npc) npc.getParent().getChild("Cow")).model.getWorldTranslation().setY(2);
        spider.phys.warp(spider.startSpot);
        }
      
      ((Door) ((Node) npc.getParent().getParent().getChild("InteractableNode")).getChild("DairyGate")).locked = true;
      
      stateManager.getState(SceneManager.class).makeUnshaded(stateManager.getState(MonsterManager.class).monsterNode); 
      farmerQuest.step = "HelpCow";
      
      }
    
    else if (farmerQuest.step.equals("HelpCow")) {
      gui.showAlert(npc.getName(), "Hurry out to the field! Help my cow");
      }
    
    else if (farmerQuest.step.equals("SavedCow")) {
      gui.showAlert(npc.getName(), "Thanks for saving my cow!");
      farmerQuest.step = "Done";
      }
    
    else if (farmerQuest.step.equals("Done")) {
      gui.showAlert(npc.getName(), "If you ever need meat just bring me a bale of hay!");
      }
      
    }
    
  }
