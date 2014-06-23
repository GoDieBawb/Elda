/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.interactable;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.items.Item;
import mygame.items.Sword;
import mygame.player.Player;
import mygame.player.PlayerManager;
import mygame.quests.Quest;
import mygame.quests.SwordQuest;
import mygame.scene.SceneManager;

/**
 *
 * @author Bob
 */
public class SwordGrave extends Interactable{
    
  public SwordGrave(Node interactable){
    super(interactable);
    setName("Grave");
    message    = "The inscription shows a warrior";
    actionName = "Check";
    }
  
  @Override
  public void act(AppStateManager stateManager){
    
    Player player = stateManager.getState(PlayerManager.class).player;
    
    Quest swordQuest = player.questList.getQuest("SwordQuest");
    
    if (swordQuest == null){
      swordQuest = new SwordQuest(stateManager);
      swordQuest.step = "Start";
      player.questList.add(swordQuest);
      }
    
    if (swordQuest.step.equals("Done")){
      swordQuest.gui.showAlert(getName(), "This grave has been dug up...");
      }

    if (swordQuest.step.equals("hasSword")){
      swordQuest.gui.showAlert(getName(), "This grave has been dug up...");
      }
    
    else if (player.equippedItem !=null) {
        
      if (player.equippedItem.getName().equals("Shovel") ){
          
        swordQuest.gui.showAlert(getName(), "You dig up the grave... and retrieve a sword");
        Node swordModel = (Node) stateManager.getApplication().getAssetManager().loadModel("Models/Sword.j3o");
        swordModel.setName("Sword");
        swordModel.scale(3);
        stateManager.getState(SceneManager.class).makeUnshaded(swordModel);
        Item sword = new Sword(swordModel);
        player.inventory.add(sword);
        swordQuest.gui.updateInventory();
        swordQuest.step = "hasSword";
        
        }
      
      }
    
    else {
      swordQuest.gui.showAlert(getName(), "Looks someone was buried here");
      }
    
    }
    
  }
