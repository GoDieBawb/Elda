/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.monsters;

import com.jme3.animation.LoopMode;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import mygame.player.Player;

/**
 *
 * @author Bob
 */
public class Spider extends Monster {
  
  public Spider(Node monster, AppStateManager stateManager){
    super(monster, stateManager);
    setName("Spider");
    animChannel.setAnim("Walk_2");
    sightRange     = 10;
    attackRange    = 1;
    attackCooldown = 1;
    damage         = 3;
    health         = 3;
    } 
  
  @Override
  public void attack(Player player){
    hasAttacked  = true;
    lastAttacked = System.currentTimeMillis() / 1000;
    animChannel.setAnim("Strike");
    animChannel.setLoopMode(LoopMode.DontLoop);
    player.health = player.health - damage;
    };
  
  @Override
  public void idle(){
    if (!animChannel.getAnimationName().equals("Walk_2"))
    animChannel.setAnim("Walk_2");
    };
  
  @Override
  public void run(){
    if (!animChannel.getAnimationName().equals("Run"))
    animChannel.setAnim("Run");
    };
  
  @Override
  public void die(){
    deathTime = System.currentTimeMillis() / 1000;
    isDead    = true;
    phys.setWalkDirection(startSpot.subtract(startSpot));
    animControl.clearChannels();
    }
    
}
