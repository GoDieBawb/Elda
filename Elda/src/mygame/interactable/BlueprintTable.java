/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.interactable;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.gui.GuiManager;
import mygame.player.Player;
import mygame.player.PlayerManager;
import mygame.quests.EldaQuest;
import mygame.quests.Quest;

/**
 *
 * @author Bob
 */
public class BlueprintTable extends Interactable {
    
  public BlueprintTable(Node interactable){
    super(interactable);
    name       = "BlueprintTable";
    message    = "An ancient table";
    actionName = "Check";
    }
  
  @Override
  public void act(AppStateManager stateManager) {
    
    Player player = stateManager.getState(PlayerManager.class).player;
    
    Quest eldaQuest = player.questList.getQuest("EldaQuest");
    
      System.out.println("Blueprint table acting");
    
    if (eldaQuest ==  null) {
      eldaQuest = new EldaQuest(stateManager);
      player.questList.add(eldaQuest);
      eldaQuest.step = "Start";
      System.out.println("Creating Elda Quest");
      }
    
      System.out.println(eldaQuest.step);
    
    if (eldaQuest.step.equals("FindBluePrints")) {
      stateManager.getState(GuiManager.class).showAlert("BlueprintTable", "Among the ancient documents you find what seem to be blueprints...");    
      eldaQuest.step = "HasBluePrints";
      }
    
    else {
      stateManager.getState(GuiManager.class).showAlert("BlueprintTable", "A bunch of strange ancient documents");  
      }
      
    }
    
  }
