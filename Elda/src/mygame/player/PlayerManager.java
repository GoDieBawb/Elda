/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.player;

import com.jme3.animation.AnimControl;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.quests.QuestList;

/**
 *
 * @author Bob
 */
public class PlayerManager extends AbstractAppState {

  private SimpleApplication app;
  private AppStateManager   stateManager;
  private AssetManager      assetManager;
  public  BulletAppState    physics;
  public  Player            player;
  
  @Override
  public void initialize(AppStateManager stateManager, Application app){
    super.initialize(stateManager, app);
    this.app          = (SimpleApplication) app;
    this.stateManager = this.app.getStateManager();
    this.assetManager = this.app.getAssetManager();
    physics           = new BulletAppState();
    stateManager.attach(physics);
    initPlayer();
    }
  
  private void initPlayer() {
    player             = new Player();
    player.model       = (Node) assetManager.loadModel("Models/Person/Person.j3o");
    player.phys        = new BetterCharacterControl(.3f, .5f, 100f);
    player.animControl = player.model.getChild("Person").getControl(AnimControl.class);
    
    player.armChannel  = player.animControl.createChannel();
    player.legChannel  = player.animControl.createChannel();
    
    player.questList   = new QuestList(player);
    player.inventory   = new Inventory(player);
    
    Node bla           = (Node) player.model.getChild(0);
    player.hand        = (Node) bla.getChild("Hand");
    
    player.health      = 20;
    
    player.armChannel.addFromRootBone("TopSpine");
    player.legChannel.addFromRootBone("BottomSpine");
    
    player.armChannel.setAnim("ArmIdle");
    player.legChannel.setAnim("LegsIdle");
    
    player.attachChild(player.model);
    player.addControl(player.phys);
    physics.getPhysicsSpace().add(player.phys);
    
    player.phys.setGravity(new Vector3f(0, -50, 0));
    
    player.scale(.3f);
    this.app.getRootNode().attachChild(player);
    
    }
    
  
  @Override
  public void update(float tpf){
      
    if (player.hasSwung) {
      
      if (System.currentTimeMillis()/1000 - player.lastSwung > 1) {
        player.hasSwung = false;
        }
      
      }
    
    if (player.getLocalTranslation().y < -5 ) {
      player.die(stateManager);
      }
    
    if (player.health <= 0)
    player.die(stateManager);

    }
  
  }
