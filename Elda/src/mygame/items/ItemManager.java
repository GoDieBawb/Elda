/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.items;

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
public class ItemManager extends AbstractAppState {

  private SimpleApplication app;
  private AppStateManager   stateManager;
  private AssetManager      assetManager;
  private Node              itemNode;
  private Player            player;
  
  @Override
  public void initialize(AppStateManager stateManager, Application app){
    super.initialize(stateManager, app);
    this.app          = (SimpleApplication) app;
    this.stateManager = this.app.getStateManager();
    this.assetManager = this.app.getAssetManager();
    player            = stateManager.getState(PlayerManager.class).player;
    }
  
  public void initItems(Node scene) {
     
    //Gets the item Node from the scene  
    itemNode = (Node) scene.getChild("ItemNode");  
    
    int targetInt = itemNode.getQuantity();
            
    //Iterate over the item node to get items in the scene
    for (int i = 0; i < itemNode.getQuantity(); i++){
      
      //Make the current item a node  
      Node currentItem = (Node) itemNode.getChild(i);
      
      //Checks to see if the item is already an item
      try {
        
        //Attempts to cast the current node it an item
        Item itemCheck = (Item) currentItem;
          
        }
      
      //If not an item make it an item
      
      catch (ClassCastException e) { 
      
        if (currentItem.getName().equals("Sword")){
          
          Item sword = new Sword(currentItem);
          itemNode.attachChild(sword);
          
          }
        
        else if (currentItem.getName().equals("Food")) {
            
          Item food = new Food(currentItem);
          itemNode.attachChild(food);
          
          }
        
        else if (currentItem.getName().equals("Shovel")){
          Item shovel = new Shovel(currentItem);
          itemNode.attachChild(shovel);
          }
      
        }
      
      }
    
    //Clean up the item Node
    itemNodeClean(targetInt);
    
    }
  
  private void itemNodeClean(int targetInt){
    
    //Iterates over the item Node  
    for (int i = 0; i < itemNode.getQuantity(); i++){
      
      //Makes a current Item
      Item currentItem;
      
      //If it is an item attach its model
      try {
        currentItem = (Item) itemNode.getChild(i);
        currentItem.model.removeFromParent();
        currentItem.attachChild(currentItem.model);
        }
      
      //If its a node detach it from the item node and wait to be attached to its parent item
      catch (ClassCastException e) {
        //itemNode.getChild(i).removeFromParent();
        }
        
      }
    
    if (itemNode.getQuantity() != targetInt){
      itemNodeClean(targetInt);
      System.out.println("Recleaning Item Node");
      }
    
    else {
      System.out.println("Exiting item cleaner");
      }
        
    }
  
  @Override
  public void update(float tpf){
    
    //Checks to see if the player is colliding with any items
    CollisionResults results = new CollisionResults();
    itemNode.collideWith(player.model.getWorldBound(), results);
    
    //Checks if an item is hit
    if (results.size() != 0){
      GuiManager gui = stateManager.getState(GuiManager.class);
      Item hitItem = (Item) results.getCollision(0).getGeometry().getParent().getParent();
      
      //If the current message box isn't the name of the item send it
      if (!gui.getAlertTitle().equals(hitItem.getName())){
        gui.showAlert(hitItem.getName(), "This is a " + hitItem.getName() + ". Interact to pick it up");
        }
      
      //Checks if the player wants to pick up the hit item
      if (player.itemCheck){
        player.inventory.add(hitItem);
        hitItem.removeFromParent();
        gui.updateInventory();
        gui.showAlert("Item", "Picked up " + hitItem.getName());
        }
      
      }
    
    player.itemCheck = false;
    
    }
  
  }
