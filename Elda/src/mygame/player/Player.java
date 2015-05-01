/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.player;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.LoopMode;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.asset.AssetNotFoundException;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.system.JmeSystem;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mygame.AndroidManager;
import mygame.Main;
import mygame.gui.GuiManager;
import mygame.items.Item;
import mygame.quests.QuestList;
import mygame.scene.SceneManager;

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
  public boolean     hayPerm;
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
  
  public void saveScore(QuestList questList , AppStateManager stateManager) {
    
    String filePath;  
      
    try {  
      filePath         = stateManager.getState(AndroidManager.class).filePath;
      }
    
    catch (NullPointerException e){
      filePath = JmeSystem.getStorageFolder().toString();
      }
    
    BinaryExporter exporter = BinaryExporter.getInstance();
    Node score              = new Node();
    score.setUserData("Name", "Hope");
    score.setUserData("Quests", questList);
    File file               = new File(filePath + "/eldaScore.j3o");
    
    System.out.println("Saving Quests");
    
    try {
        
      exporter.save(score, file);  
      System.out.println("Quests saved to: " + filePath);
        
      }
    
    catch (IOException e) {
        
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error: Failed to save game!", e);  
        System.out.println("Failure");
      }
    
      System.out.println("Quest save completion");
    
    }
  
  public QuestList loadQuests(AppStateManager stateManager) {
      
    String filePath;  
      
    try {  
      filePath         = stateManager.getState(AndroidManager.class).filePath;
      }
    
    catch (NullPointerException e){
      filePath = JmeSystem.getStorageFolder().toString();
      }
    
     AssetManager assetManager = stateManager.getApplication().getAssetManager();
     
     assetManager.registerLocator(filePath, FileLocator.class);
     
     Node       newNode;
     QuestList  loadedQuests;
     
     try {
       newNode        = (Node) assetManager.loadModel("eldaScore.j3o");
       loadedQuests   = newNode.getUserData("Quests");
       }
     
     catch (AssetNotFoundException ex) {
       loadedQuests = new QuestList(this);    
       saveScore(loadedQuests, stateManager);  
       }
     
     catch (IllegalArgumentException e) {
       loadedQuests = new QuestList(this);    
       saveScore(loadedQuests, stateManager);  
       }
     
     
     System.out.println("You've loaded: " + loadedQuests);
     return loadedQuests;
     }

  public void die(AppStateManager stateManager) {
    
    health = 20;
    stateManager.getState(SceneManager.class).initScene("Scenes/StartingTown.j3o", new Vector3f(28, 1 , -36));
    stateManager.getState(GuiManager.class).delayAlert("Death", "You wake up in your house", 5);  
    
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
