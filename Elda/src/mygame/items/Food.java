/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.items;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.gui.GuiManager;
import mygame.player.Player;
import mygame.player.PlayerManager;

/**
 *
 * @author Bob
 */
public class Food extends Item {
    
  private int healAmount;
    
  public Food(Node item) {
    super(item);
    assignSpecs(this);
    }
  
  private void assignSpecs(Item item){
    
    type = this.model.getUserData("Type");  
      
    if (this.type.equals("Bread")) {
      healAmount = 5;
      }
    
    else {
      healAmount = 3;
      }
    
    }
  
  @Override
  public void act(AppStateManager stateManager){
    Player player = stateManager.getState(PlayerManager.class).player;
    player.health = player.health + healAmount;
    player.hand.detachAllChildren();
    player.inventory.remove(player.inventory.getItem(this.getName()));
    stateManager.getState(GuiManager.class).updateInventory();
    }
  
  @Override
  public void equip(Player player){
    super.equip(player);
    }
    
  }
