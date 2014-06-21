/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.monsters;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.player.Player;
import mygame.player.PlayerManager;

/**
 *
 * @author Bob
 */
public abstract class Monster extends Node {
    
  public int      health;
  public int      damage;
  public int      sightRange;
  public int      attackRange;
  public int      attackCooldown;
  public long     lastAttacked;
  public boolean  hasAttacked;
  public boolean  goHome;
  public boolean  isDead;
  public Vector3f startSpot;
  public Long     deathTime;
  public Node model;
  public BetterCharacterControl phys;
  public AnimControl animControl;
  public AnimChannel animChannel;
  
  public Monster(Node monster, AppStateManager stateManager){
    super();
    model            = monster;
    animControl      = model.getControl(AnimControl.class);
    animChannel      = animControl.createChannel();
    phys             = new BetterCharacterControl(.3f, .5f, 100f);
    startSpot        = model.getWorldTranslation().clone();
    setName(monster.getName());
    addControl(phys);
    stateManager.getState(PlayerManager.class).physics.getPhysicsSpace().add(phys);
    phys.warp(startSpot);
    }
  
  public void attack(Player player){}
  
  public void idle(){}
  
  public void run(){}
  
  public abstract void die();
    
  }
