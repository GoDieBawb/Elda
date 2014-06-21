/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gui;

import com.jme3.math.Vector2f;
import mygame.items.Item;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.core.ElementManager;

/**
 *
 * @author Bob
 */
public class InventoryButton extends ButtonAdapter {
    
  public Item item;  
    
  public InventoryButton(ElementManager screen, String name, Vector2f size){
    super(screen, name, size);
    }  
    
  }
