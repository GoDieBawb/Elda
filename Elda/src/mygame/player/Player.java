/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.player;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.LoopMode;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.scene.Node;
import mygame.items.Item;
import mygame.quests.QuestList;

/**
 *
 * @author Bob
 */
public class Player extends Node {
    
  public BetterCharacterControl phys;
  public Node        model;
  public boolean     hasSwung;
  public Long        lastSwung;
  public AnimControl animControl;
  public AnimChannel armChannel;
  public AnimChannel legChannel;
  public QuestList   questList;
  public int         health;
  public float       speedMult;
  public boolean     itemCheck;
  public boolean     interactCheck;
  public Inventory   inventory;
  public Item        equippedItem;
  public Node        hand;
  
  public void swing(AppStateManager stateManager){
    lastSwung     = System.currentTimeMillis() / 1000;
    interactCheck = true;
    hasSwung      = true;
    itemCheck     = true;
    armChannel.setAnim("ArmSwing");
    armChannel.setSpeed(2);
    armChannel.setLoopMode(LoopMode.DontLoop);
    
    if (equippedItem != null)
    equippedItem.act(stateManager);
    
    }
  
  public void getItemInHand(){
    
    }

  public void run(){
      
    if (!armChannel.getAnimationName().equals("ArmRun") && !hasSwung){
      armChannel.setAnim("ArmRun");
      }
    
    if (!legChannel.getAnimationName().equals("LegRun")){
      legChannel.setAnim("LegRun");
      }
    
    }
  
  public void idle(){

    if (!armChannel.getAnimationName().equals("ArmIdle") && !hasSwung){
      armChannel.setAnim("ArmIdle");
      }
    
    if (!legChannel.getAnimationName().equals("LegsIdle")){
      legChannel.setAnim("LegsIdle");
      }
    
    }
    
  }
