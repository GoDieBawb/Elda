/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.player;

import java.util.ArrayList;
import mygame.items.Item;

/**
 *
 * @author Bob
 */
public class Inventory extends ArrayList {
  
  private Player player;
    
  public Inventory(Player player){
    this.player = player;
    }
  
  public Item getItem(String item){
    
    for (int i = 0; i < player.inventory.size(); i++){
      Item currentItem = (Item) player.inventory.get(i);
      
      if (currentItem.getName().equals(item)){
        return currentItem;
        }
          
      }  
      
    return null;  
    }
    
  }
