/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.quests;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.items.Food;
import mygame.items.Item;
import mygame.monsters.Monster;
import mygame.scene.SceneManager;

/**
 *
 * @author Bob
 */
public class FrankieQuest extends Quest {
    
  public FrankieQuest(AppStateManager stateManager){
    super(stateManager);
    name = "FrankieQuest";
    }
  
  @Override
  public void act(){
    
    Quest frankieQuest  = player.questList.getQuest(name);
    Monster questGoblin = (Monster) ((Node) npc.getParent().getParent().getChild("MonsterNode")).getChild("QuestGoblin");
    System.out.println(step);
    
    if (frankieQuest ==  null) {
      step = "Start";
      player.questList.add(this);
      }
    
    frankieQuest  = player.questList.getQuest(name);
    
    if (questGoblin == null && !frankieQuest.step.equals("Done")) {
      step = "killedGoblin";
      }
  
    if (frankieQuest.step.equals("Done")) {
      gui.showAlert(npc.getName(), "Good work on killing that goblin!");
      if (questGoblin != null)
      questGoblin.removeFromParent();
      }
    
    else if (frankieQuest.step.equals("killedGoblin")){
      gui.showAlert(npc.getName(), "You got hurt in the fight! Eat this food to heal!");
      Node breadModel = (Node) stateManager.getApplication().getAssetManager().loadModel("Models/Bread.j3o");  
      breadModel.setName("Food");
      breadModel.setUserData("Type", "Bread");
      Item bread      = new Food(breadModel);
      stateManager.getState(SceneManager.class).makeUnshaded(breadModel);
      player.inventory.add(bread);
      step = "Done";
      }
    
    else if (frankieQuest.step.equals("killGoblin")) {
      gui.showAlert(npc.getName(), "Make sure your wielding your sword!");
      }
    
    else {

      gui.showAlert(npc.getName(), "There's a goblin in the road!");
      step = "killGoblin";
        
      }
      
    }
    
  }
