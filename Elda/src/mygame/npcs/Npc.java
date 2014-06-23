/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.npcs;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.LoopMode;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.scene.Node;
import mygame.player.PlayerManager;
import mygame.quests.Quest;
import mygame.scene.SceneManager;

/**
 *
 * @author Bob
 */
public class Npc extends Node {
    
  public Node    model;
  public Quest   quest;
  public boolean hasSpoke;
  public Long    lastSpoke;
  public BetterCharacterControl phys;
  public AnimControl animControl;
  public AnimChannel armChannel;
  public AnimChannel legChannel;
  
  public Npc(Quest quest, AppStateManager stateManager, Node npc) {
    this.quest  = quest;
    this.phys   = new BetterCharacterControl(.3f, .5f, 100f);
    setName(npc.getName());
    model       = npc;
    this.model.addControl(this.phys);
    stateManager.getState(PlayerManager.class).physics.getPhysicsSpace().add(this.phys);
    initAnimations();
    quest.npc = this;
    }
  
  private void initAnimations(){
    
    if (name.equals("Cow")) {
      animControl = model.getControl(AnimControl.class);
      armChannel  = animControl.createChannel();
      armChannel.addAllBones();
      armChannel.setAnim("EatLow");
      }
    
    else {
      animControl = model.getChild("Person").getControl(AnimControl.class);
      armChannel = animControl.createChannel();
      legChannel = animControl.createChannel();
      armChannel.addFromRootBone("TopSpine");
      legChannel.addFromRootBone("BottomSpine");
    
      armChannel.setAnim("ArmIdle");
      legChannel.setAnim("LegsIdle");
      }
      
    }

  public void swing(AppStateManager stateManager){
    armChannel.setAnim("ArmSwing");
    armChannel.setSpeed(2);
    armChannel.setLoopMode(LoopMode.DontLoop);
    }

  public void run(){
    if (!armChannel.getAnimationName().equals("ArmRun")){
      armChannel.setAnim("ArmRun");
      }
    
    if (!legChannel.getAnimationName().equals("LegRun")){
      legChannel.setAnim("LegRun");
      }
    }
  
  public void idle(){
    
    if (name.equals("Cow")) {
      
      }
    else if (!armChannel.getAnimationName().equals("ArmIdle")){
      armChannel.setAnim("ArmIdle");
      }  
    }
    
  }
