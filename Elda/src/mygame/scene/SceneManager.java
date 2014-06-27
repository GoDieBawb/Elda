/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.scene;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import mygame.interactable.InteractableManager;
import mygame.items.ItemManager;
import mygame.monsters.MonsterManager;
import mygame.npcs.NpcManager;
import mygame.player.Player;
import mygame.player.PlayerManager;

/**
 *
 * @author Bob
 */
public class SceneManager extends AbstractAppState {

  private SimpleApplication   app;
  private AppStateManager     stateManager;
  private AssetManager        assetManager;
  public  Node                scene;
  private BulletAppState      physics;
  private Player              player;
  private NpcManager          npcManager;
  private ItemManager         itemManager;
  private MonsterManager      monsterManager;
  private InteractableManager interactableManager;
  private Node                rootNode;
  
  @Override
  public void initialize(AppStateManager stateManager, Application app){
    super.initialize(stateManager, app);
    this.app            = (SimpleApplication) app;
    this.stateManager   = this.app.getStateManager();
    this.assetManager   = this.app.getAssetManager();
    npcManager          = this.stateManager.getState(NpcManager.class);
    itemManager         = this.stateManager.getState(ItemManager.class);
    monsterManager      = this.stateManager.getState(MonsterManager.class);
    interactableManager = this.stateManager.getState(InteractableManager.class);
    player              = this.stateManager.getState(PlayerManager.class).player;
    rootNode            = this.app.getRootNode();
    scene               = new Node();
    physics             = this.stateManager.getState(PlayerManager.class).physics;
    initScene("Scenes/StartingTown.j3o", new Vector3f(28, 1 , -36));
    //initScene("Scenes/AbuDesert.j3o", new Vector3f(0, 2 ,0));
    }
  
  public void initScene(String scenePath, Vector3f startSpot){

    rootNode.detachChild(scene);
    physics.getPhysicsSpace().removeAll(scene);

    player.phys.warp(startSpot);

    scene = (Node) assetManager.loadModel(scenePath);
    addPhys();  
    
    rootNode.attachChild(scene);
    
    npcManager.initNpcs(scene);
    itemManager.initItems(scene);
    monsterManager.initMonsters(scene);
    interactableManager.initInteractables(scene);
    
    makeUnshaded(app.getRootNode());  
    
    }
  
  public void addPhys() {
    physics.getPhysicsSpace().removeAll(scene);
    
    RigidBodyControl phys  = new RigidBodyControl(0f);
    RigidBodyControl phys1 = new RigidBodyControl(0f);
    
    scene.getChild("SceneNode").removeControl(RigidBodyControl.class);
    scene.getChild("InteractableNode").removeControl(RigidBodyControl.class);
    scene.getChild("SceneNode").addControl(phys);
    scene.getChild("InteractableNode").addControl(phys1);
    
    physics.getPhysicsSpace().add(phys);
    physics.getPhysicsSpace().add(phys1);      
    }

  public void makeUnshaded(Node node) {
      
    SceneGraphVisitor sgv = new SceneGraphVisitor() {
 
    public void visit(Spatial spatial) {
 
      if (spatial instanceof Geometry) {
        
        Geometry geom = (Geometry) spatial;
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        if (geom.getMaterial().getTextureParam("DiffuseMap") != null) {
          mat.setTexture("ColorMap", geom.getMaterial().getTextureParam("DiffuseMap").getTextureValue());
          geom.setMaterial(mat);
          }
       
        }
      
      }
    
    };
    
  node.depthFirstTraversal(sgv);
    
  }
  
}
