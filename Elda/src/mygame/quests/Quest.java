/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.quests;

import com.jme3.app.state.AppStateManager;
import mygame.gui.GuiManager;
import mygame.npcs.Npc;
import mygame.player.Player;
import mygame.player.PlayerManager;

/**
 *
 * @author Bob
 */
public abstract class Quest {
    
  public  AppStateManager  stateManager;
  public  GuiManager       gui;
  public  Player           player;
  public  String           step;
  public  String           name;
  public  Npc              npc;
  
    
  public Quest(AppStateManager stateManager){
    this.stateManager = stateManager;
    this.player       = stateManager.getState(PlayerManager.class).player;
    this.gui          = stateManager.getState(GuiManager.class);
    }
  
  public abstract void act();
    
}
