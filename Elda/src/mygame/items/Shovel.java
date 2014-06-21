/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.items;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.player.Player;

/**
 *
 * @author Bob
 */
public class Shovel extends Item {
    
  public Shovel(Node item){
    super(item);
    }
    
  @Override
  public void act(AppStateManager stateManager) {
    }
  
  @Override
  public void equip(Player player){
    super.equip(player);
    model.setLocalScale(15);
    }
  
  @Override
  public void unequip(Player player){
    super.unequip(player);
    }
    
  }
