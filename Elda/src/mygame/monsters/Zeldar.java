/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.monsters;

import com.jme3.animation.LoopMode;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.gui.GuiManager;
import mygame.player.Player;

/**
 *
 * @author Bob
 */
public class Zeldar extends Monster {
  
  private AppStateManager stateManager;  
  
  public Zeldar(Node monster, AppStateManager stateManager) {
    super(monster, stateManager);
    this.stateManager   = stateManager;
    sightRange          = 10;
    attackRange         = 3;
    attackCooldown      = 2;
    damage              = 3;
    health              = 100;
    }
  
  @Override
  public void attack(Player player){
    animChannel.setAnim("ArmSwing");
    animChannel.setLoopMode(LoopMode.DontLoop);
    hasAttacked   = true;
    lastAttacked  = System.currentTimeMillis() / 1000;
    player.health = player.health -  damage;
    }
  
  @Override 
  public void run(){
    if (!animChannel.getAnimationName().equals("LegsRun"))  
    animChannel.setAnim("LegsRun");
    }

  @Override
  public void idle(){
    if (!animChannel.getAnimationName().equals("ArmsIdle"))
    animChannel.setAnim("ArmsIdle");
    }
  
  @Override 
  public void die() {
      
    deathTime = System.currentTimeMillis() / 1000;
    isDead    = true;
    animControl.clearChannels();
    this.getParent().getParent().getParent().detachAllChildren();
    stateManager.getState(GuiManager.class).showAlert("The End", "Thanks for playing! A better alternate ending will be coming soon! And thanks for supporting independent game developers!");
    
    }
    
  }
