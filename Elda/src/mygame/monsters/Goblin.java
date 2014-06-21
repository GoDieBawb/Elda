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
public class Goblin extends Monster {
    
  public Goblin(Node monster, AppStateManager stateManager){
    super(monster, stateManager);
    setName("Goblin");
    animChannel.setAnim("idleA");
    sightRange     = 7;
    attackRange    = 1;
    attackCooldown = 5;
    damage         = 5;
    health         = 10;
    
    if (model.getUserData("questGoblin")) {
      System.out.println("Quest Goblin");
      setName("QuestGoblin");
      
      } else {
      System.out.println("Not Goblin");
      }
    
    } 
  
  @Override
  public void attack(Player player){
    hasAttacked   = true;
    lastAttacked  = System.currentTimeMillis() / 1000;
    animChannel.getControl();
    animChannel.setLoopMode(LoopMode.DontLoop);
    player.health = player.health -  damage;
    };
  
  @Override
  public void idle(){
    if (!animChannel.getAnimationName().equals("idleA"))
    animChannel.setAnim("idleA");
    };
  
  @Override
  public void run(){
    if (!animChannel.getAnimationName().equals("run"))
    animChannel.setAnim("run");
    };

  @Override
  public void die(){
    deathTime = System.currentTimeMillis() / 1000;
    isDead    = true;
    phys.setWalkDirection(startSpot.subtract(startSpot));
    animControl.clearChannels();
    }
    
}
