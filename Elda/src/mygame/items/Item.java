/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.items;

import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.gui.GuiManager;
import mygame.player.Player;

/**
 *
 * @author Bob
 */
public abstract class Item extends Node {
  
  public String    type;
  public Node      model;
  public int       damage;
  public int       range;
  public Vector3f  equipRotation;
  public Vector3f  equipOffset;
  public String    actionName;
  
  public Item (Node item) {
    model = item;
    type  = item.getName();
    equipOffset   = new Vector3f(-0.8f, 0.0f, -0.7f);
    setName(item.getName());
    }
  
  public abstract void act(AppStateManager stateManager);
  
  public void equip(Player player) {
    player.hand.detachAllChildren();
    player.hand.attachChild(model);
    player.equippedItem = this;
    model.setLocalTranslation(equipOffset);
    }
  
  public void unequip(Player player){
    player.hand.detachAllChildren();
    player.equippedItem = null;
    }
    
  }
