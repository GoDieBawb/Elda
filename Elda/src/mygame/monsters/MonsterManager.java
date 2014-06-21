/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.monsters;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.player.Player;
import mygame.player.PlayerManager;

/**
 *
 * @author Bob
 */
public class MonsterManager extends AbstractAppState {

  private SimpleApplication app;
  private AppStateManager   stateManager;
  private AssetManager      assetManager;
  private Player            player;
  public  Node              monsterNode;
  
  @Override
  public void initialize(AppStateManager stateManager, Application app){
    super.initialize(stateManager, app);
    this.app          = (SimpleApplication) app;
    this.stateManager = this.app.getStateManager();
    this.assetManager = this.app.getAssetManager();
    player            = stateManager.getState(PlayerManager.class).player;
    
    }
  
  //Inits all monsters in the monster node
  public void initMonsters(Node scene){
    
    setEnabled(false);
      
    //Gets the monster node from the scene  
    monsterNode = (Node) scene.getChild("MonsterNode");
    
    int bla = monsterNode.getQuantity();
    
    for (int i = 0; i < bla; i++) {
        
      Node currentMonster = (Node) monsterNode.getChild(i);
      
      //If its already a monster do nothing
      try {
        
        //Tests if the monster can be cast
        Monster testMonster = (Monster) currentMonster;
          
        }
      
      //If not a monster make it a monster
      catch (ClassCastException e) {
        
        //If its a goblin make it a goblin type monster
        if (currentMonster.getName().equalsIgnoreCase("Goblin")) {
          Monster goblin = new Goblin(currentMonster, stateManager);
          monsterNode.attachChild(goblin);
          System.out.println("Goblin Created");
          }
      
        //If its a spider make it a spider type monster
        if (currentMonster.getName().equalsIgnoreCase("Spider")) {
            
          Monster spider = new Spider(currentMonster, stateManager);
          monsterNode.attachChild(spider);
          System.out.println("Spider created");
          
          }
        
        }
      
      }
    
    //Clean the monsterNode of nonMonsters
    monsterNodeClean();
    
    
    setEnabled(true);
    
    }
  
  //Removes nodes and leaves only monsters
  private void monsterNodeClean(){
   
    //Iterates over the monster node  
    for (int i = 0; i < monsterNode.getQuantity(); i++) {
      
      //Creates a monster
      Monster currentMonster;
      
      //If the monster is a real monster attach its model
      try {
        
        currentMonster = (Monster) monsterNode.getChild(i);
        currentMonster.attachChild(currentMonster.model);
        currentMonster.model.setLocalTranslation(new Vector3f(0,0,0));
          
        }
      
      //If it is not a monster it is a model, remove it and wait to be attached
      catch(ClassCastException e) {
          
        monsterNode.getChild(i).removeFromParent();
          
        }
        
      }  
    
    for (int i = 0; i < monsterNode.getQuantity(); i++) {
      Monster currentMonster = (Monster) monsterNode.getChild(i);
      currentMonster.attachChild(currentMonster.model);
      currentMonster.model.setLocalTranslation(new Vector3f(0,0,0));
      }
      
    }
  
  //Checks the distance between the monster and the starting point
  private void startDistanceChecker(Monster monster){ 
      
    float    distance = monster.startSpot.distance(monster.model.getWorldTranslation());
    Vector3f homeDir  = monster.startSpot.subtract(monster.getWorldTranslation());
    
    //If the distance is greater than 15 go back
    if (distance > 15) {
      monster.goHome = true;
      monster.phys.setWalkDirection(homeDir);
      monster.phys.setViewDirection(homeDir);
      }
    
    //Continue walking
    else if (monster.goHome && distance > 3) {
      monster.phys.setWalkDirection(homeDir);
      }
   
    //If less than three stop going home
    else {
      monster.goHome = false;
      }
    
    }
  
  //Checks the distance between the monster and the player
  private void distanceChecker(Monster monster) {
    
    Vector3f playerDir = player.model.getWorldTranslation().subtract(monster.model.getWorldTranslation());
    float    distance  = monster.model.getWorldTranslation().distance(player.model.getWorldTranslation());
    
    //Checks to see if the monster can attack
    if (distance < monster.attackRange){
      if (!monster.hasAttacked)
      monster.attack(player);
      }
    
    //Checks if the player is within seeing distance
    else if (distance < monster.sightRange) { 
      monster.phys.setViewDirection(playerDir);
      monster.phys.setWalkDirection(playerDir);
      monster.run();
      }
    
    //If the monster is back home start idling and stop walking
    else {
      monster.idle();
      monster.phys.setWalkDirection(new Vector3f(0, 0, 0));
      }
    
    }
  
  //Checks to see if the monsters attack has cooled down
  private void cooldownChecker(Monster monster){
    if (monster.hasAttacked)
    if (System.currentTimeMillis() / 1000 - monster.lastAttacked > monster.attackCooldown)
    monster.hasAttacked = false;
    }
  
  private void deathChecker(Monster monster){
    
    //If the monsters health is or less kill it
    if (monster.health <= 0 && !monster.isDead) {
      monster.die();
      }
    
    //If the monster died 3 seconds ago then remove it
    if (monster.isDead)
    if (System.currentTimeMillis() / 1000 - monster.deathTime > 3) {
      monster.removeFromParent();
      }
    
    }
  
  @Override 
  public void update(float tpf){
      
    for (int i = 0; i < monsterNode.getQuantity(); i++){
      
      Monster monster = (Monster)  monsterNode.getChild(i);
        
        //Makes sure the monster isnt dead  
        if (!monster.isDead) {
         
        //Makes sure the monster hasnt been told to go home    
        if (!monster.goHome)
          distanceChecker(monster);
        
          cooldownChecker(monster);
        
          startDistanceChecker(monster);
          
          }
        
        deathChecker(monster);
        
      }
      
    }
  
  }
