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
    
    int targInt = interactableNode.getChildren().size();
    
    for (int i = 0; i < interactableNode.getChildren().size(); i++) {
    
      Node currentInteractable = (Node) interactableNode.getChild(i);
      
      try {
        
        Interactable test =  (Interactable) currentInteractable;
          
        }
      
      catch (ClassCastException e) {
        
        if (currentInteractable.getName().equalsIgnoreCase("Door")) {
          Interactable door = new Door(currentInteractable, stateManager);
          interactableNode.attachChild(door);
          }
        
        else if (currentInteractable.getName().equalsIgnoreCase("Well")) {
          Interactable well = new Well(currentInteractable);
          interactableNode.attachChild(well);
          }
        
        else if (currentInteractable.getName().equalsIgnoreCase("SwordGrave")) {
          Interactable grave = new SwordGrave(currentInteractable);
          interactableNode.attachChild(grave);
          }
        
        else if (currentInteractable.getName().equalsIgnoreCase("HayStack")) {
          Interactable hayStack = new HayStack(currentInteractable);
          interactableNode.attachChild(hayStack);
          }
        
        else if (currentInteractable.getName().equalsIgnoreCase("HayField")) {
          Interactable hayField = new HayField(currentInteractable);
          interactableNode.attachChild(hayField);
          }
        
        else if (currentInteractable.getName().equals("ZeldarsTable")) {
          Interactable zeldarsTable = new ZeldarsTable(currentInteractable);
          interactableNode.attachChild(zeldarsTable);
          }
        
        else if (currentInteractable.getName().equals("ZeldarsWall")) {
          Interactable zeldarsWall = new ZeldarsWall(currentInteractable);
          interactableNode.attachChild(zeldarsWall);
          }
        
        else if (currentInteractable.getName().equals("Rocks")) {
          Interactable rocks = new Rocks(currentInteractable);
          interactableNode.attachChild(rocks);
          }
 
        else if (currentInteractable.getName().equals("BlueprintTable")) {
          Interactable blueprintTable = new BlueprintTable(currentInteractable);
          interactableNode.attachChild(blueprintTable);
          }
        
        else {
          System.out.println("Uh oh " + currentInteractable.getName() + " don't exist");
          
          }
          
        }
      
      }
    
    interactNodeClean(targInt);
    
    }
  
  private void interactNodeClean(int targInt) {
      
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
    
    
    for (int i = 0; i < interactableNode.getQuantity(); i++) {
      Interactable bla = (Interactable) interactableNode.getChild(i);
      bla.attachChild(bla.model);
      }
    
    
      
    }
  
  @Override
  public void update(float tpf){
    
    CollisionResults results = new CollisionResults();
    interactableNode.collideWith(player.model.getWorldBound(), results);
      
    //Checks if an interactable is hit
    if (results.size() != 0){
      
      GuiManager gui = stateManager.getState(GuiManager.class);
      Interactable hitInteractable;
      
      try {
      
      hitInteractable = (Interactable) results.getCollision(0).getGeometry().getParent().getParent();
      
      }
      
      catch(ClassCastException e){
      
      hitInteractable = (Interactable) results.getCollision(0).getGeometry().getParent().getParent().getParent();    
          
      }
      
      
      //If the current message box isn't the name of the item send it
      if (!gui.getAlertTitle().equals(hitInteractable.getName())){
        gui.showAlert(hitInteractable.getName(), hitInteractable.message);
        gui.interactButton.setText(hitInteractable.actionName);
        
        }
      
      if (player.interactCheck) {
        hitInteractable.act(stateManager);  
        }  
      
      }
    
      player.interactCheck = false;
      
    }
  
  }