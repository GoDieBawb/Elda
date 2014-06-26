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
public class Bucket extends Item {
    
  public String contents;   
    
  public Bucket(Node item){
    super(item);
    actionName = "Use";
    contents   = "Empty";
    }
    
  @Override
  public void act(AppStateManager stateManager) {
    }
  
  @Override
  public void equip(Player player){
    super.equip(player);
    }
  
  @Override
  public void unequip(Player player){
    super.unequip(player);
    }
    
}
