/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.interactable;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.scene.Node;
import mygame.gui.GuiManager;
import mygame.player.Player;
import mygame.player.PlayerManager;

/**
 *
 * @author Bob
 */
public class InteractableManager extends AbstractAppState {

  private SimpleApplication app;
  private AppStateManager   stateManager;
  private AssetManager      assetManager;
  public  Node              interactableNode;
  private Player            player;
  
  @Override
  public void initialize(AppStateManager stateManager, Application app){
    super.initialize(stateManager, app);
    this.app          = (SimpleApplication) app;
    this.stateManager = this.app.getStateManager();
    this.assetManager = this.app.getAssetManager();
    player            = this.stateManager.getState(PlayerManager.class).player;
    }
  
  public void initInteractables(Node scene) {
      
    interactableNode = (Node) scene.getChild("InteractableNode");
    
    for (int i = 0; i < interactableNode.getQuantity(); i++) {
    
      Node currentInteractable = (Node) interactableNode.getChild(i);
      
      try {
        
        Interactable test =  (Interactable) currentInteractable;
          
        }
      
      catch (ClassCastException e) {
        
        if (currentInteractable.getName().equalsIgnoreCase("Door")) {
          System.out.println("creating dor");
          Interactable door = new Door(currentInteractable, stateManager);
          interactableNode.attachChild(door);

          }
        
        else if (currentInteractable.getName().equalsIgnoreCase("SwordGrave")) {
          Interactable grave = new SwordGrave(currentInteractable);
          interactableNode.attachChild(grave);
          }
          
        }
      
      }
    
    interactNodeClean();
    
    }
  
  private void interactNodeClean() {
      
    for (int i = 0; i < interactableNode.getQuantity(); i++) {
        
      Node currentInteractable = (Node) interactableNode.getChild(i);
      
      try {
        Interactable test =  (Interactable) currentInteractable;
        test.attachChild(test.model);
        }
      
      catch(ClassCastException e ) {
        currentInteractable.removeFromParent();
        }
        
      }
    
      System.out.println(interactableNode.getChildren());
      
    }
  
  @Override
  public void update(float tpf){
    
    CollisionResults results = new CollisionResults();
    interactableNode.collideWith(player.model.getWorldBound(), results);
      
    //Checks if an interactable is hit
    if (results.size() != 0){
      
      GuiManager gui = stateManager.getState(GuiManager.class);
      Interactable hitInteractable = (Interactable) results.getCollision(0).getGeometry().getParent().getParent();
      
      //If the current message box isn't the name of the item send it
      if (!gui.getAlertTitle().equals(hitInteractable.getName())){
        gui.showAlert(hitInteractable.getName(), hitInteractable.message);
        
        }
      
      if (player.interactCheck) {
        hitInteractable.act(stateManager);  
        }  
      
      }
    
      player.interactCheck = false;
      
    }
  
  }