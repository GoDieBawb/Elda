/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.npcs;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.player.Player;
import mygame.player.PlayerManager;
import mygame.quests.BarryQuest;
import mygame.quests.BillyBobQuest;
import mygame.quests.BlackSmithQuest;
import mygame.quests.CowQuest;
import mygame.quests.FarmerQuest;
import mygame.quests.FrankieQuest;
import mygame.quests.GaryQuest;
import mygame.quests.InvestigatorQuest;
import mygame.quests.JerryQuest;
import mygame.quests.KingQuest;
import mygame.quests.LarryQuest;
import mygame.quests.Quest;
import mygame.quests.TestQuest;
import mygame.quests.WitnessQuest;

/**
 *
 * @author Bob
 */
public class NpcManager extends AbstractAppState {

  private SimpleApplication app;
  private AppStateManager   stateManager;
  private AssetManager      assetManager;
  public  Node              npcNode;
  private Player            player;
  
  @Override
  public void initialize(AppStateManager stateManager, Application app){
    super.initialize(stateManager, app);
    this.app          = (SimpleApplication) app;
    this.assetManager = this.app.getAssetManager();
    this.stateManager = this.app.getStateManager();
    this.player       = stateManager.getState(PlayerManager.class).player;
    
    }
  
  public void initNpcs(Node scene){
   npcNode = (Node) scene.getChild("NpcNode");
   
    System.out.println("Initializing scene: " + scene.getName());
   
   if(scene.getName().equals("StartTown"))
   initStartingTown();
   
   else if (scene.getName().equals("Road"))
   initRoad();
   
   else if (scene.getName().equals("MissionDairy"))
   initDairy();
   
   else if (scene.getName().equals("LostForest"))
   initForest();
   
   else if (scene.getName().equals("EldasCastle"))
   initEldasCastle();
   
   else if (scene.getName().equals("HayFarm"))
   initHayFarm();
   
   else if (scene.getName().equals("CastleInterior"))
   initCastleInterior();   
   
   else if (scene.getName().equals("City"))
   initCity();    
   
   else if (scene.getName().equals("TestScene"))
   initTester();
  
    }
  
  private void initStartingTown(){
    
    
    for (int i = 0; i < npcNode.getQuantity(); i++) {
        
      Node currentNpc = (Node) npcNode.getChild(i);
      
      try {
          
        Npc testNpc = (Npc) currentNpc;
          
        }
      
      catch(ClassCastException e) {
      
      if (currentNpc.getName().equals("Jerry")) {
        Quest jerryQuest = new JerryQuest(stateManager);
        Npc   jerry      = new Npc(jerryQuest, stateManager, currentNpc);
        npcNode.attachChild(jerry);
        }
 
      else if (currentNpc.getName().equals("Gary")) {
        Quest garyQuest = new GaryQuest(stateManager);
        Npc   gary       = new Npc(garyQuest, stateManager, currentNpc);
        npcNode.attachChild(gary);
        }

      else  if (currentNpc.getName().equals("Barry")) {
        Quest barryQuest = new BarryQuest(stateManager);
        Npc   barry      = new Npc(barryQuest, stateManager, currentNpc); 
        npcNode.attachChild(barry);
        }
      
      else if (currentNpc.getName().equals("Larry")){
        Quest larryQuest = new LarryQuest(stateManager);
        Npc   larry      = new Npc(larryQuest, stateManager, currentNpc); 
        npcNode.attachChild(larry); 
        }
      
        }
        
      }
    
    npcNodeCleaner();
      
    }
  
  private void initRoad(){
    
    for (int i = 0; i < npcNode.getQuantity(); i++) {
        
      Node currentNpc = (Node) npcNode.getChild(i);
      
      try {
          
        Npc testNpc = (Npc) currentNpc;
          
        }
      
      catch(ClassCastException e) {
        
        if (currentNpc.getName().equals("Frank")) {
          Quest frankQuest = new FrankieQuest(stateManager);
          Npc   frank      = new Npc(frankQuest, stateManager, currentNpc);
          npcNode.attachChild(frank);
          }
          
        }
      }
   
    npcNodeCleaner();
    
    }
  
  private void initDairy() {
      
    for (int i = 0; i < npcNode.getQuantity(); i++) {
        
      Node currentNpc = (Node) npcNode.getChild(i);
     
      try {
        
        Npc testNpc = (Npc) currentNpc;
        
        }
      
      catch(ClassCastException e) {
        
        if (currentNpc.getName().equals("Farmer")) {
          Quest farmerQuest = new FarmerQuest(stateManager);
          Npc   farmer      = new Npc(farmerQuest, stateManager, currentNpc);
          npcNode.attachChild(farmer);
          }
        
        else if (currentNpc.getName().equals("Cow")) {
          Quest cowQuest = new CowQuest(stateManager);
          Npc   cow      = new Npc(cowQuest, stateManager, currentNpc);
          npcNode.attachChild(cow);
          }
          
        }
      
      }
    
    npcNodeCleaner();
      
    }
  
  private void initForest(){

    for (int i = 0; i < npcNode.getQuantity(); i++) {
        
      Node currentNpc = (Node) npcNode.getChild(i);
     
      try {
        
        Npc testNpc = (Npc) currentNpc;
        
        }
      
      catch(ClassCastException e) {
          
        if (currentNpc.getName().equals("Blacksmith")) {
          Quest blacksmithQuest = new BlackSmithQuest(stateManager);
          Npc   farmer          = new Npc(blacksmithQuest, stateManager, currentNpc);
          npcNode.attachChild(farmer);
          }          
          
        }
    
      }
    
    npcNodeCleaner();
      
    }
  
  private void initEldasCastle() {
      
    for (int i = 0; i < npcNode.getQuantity(); i++) {  
      
      Node currentNpc = (Node) npcNode.getChild(i);
     
      try {
        
        Npc testNpc = (Npc) currentNpc;
        
        }
      
      catch(ClassCastException e) {
          
        if (currentNpc.getName().equals("Guard")) {
          Quest blacksmithQuest = new BlackSmithQuest(stateManager);
          Npc   farmer          = new Npc(blacksmithQuest, stateManager, currentNpc);
          npcNode.attachChild(farmer);
          }          
          
        }
      
      }
    
    npcNodeCleaner();    
      
    }
  
  private void initCastleInterior(){
    
    for (int i = 0; i < npcNode.getQuantity(); i++) {  
      
      Node currentNpc = (Node) npcNode.getChild(i);
     
      try {
        
        Npc testNpc = (Npc) currentNpc;
        
        }
      
      catch(ClassCastException e) {
          
        if (currentNpc.getName().equals("King")) {
          Quest kingQuest = new KingQuest(stateManager);
          Npc   king      = new Npc(kingQuest, stateManager, currentNpc);
          npcNode.attachChild(king);
          }
        
        else if (currentNpc.getName().equals("Investigator")) {
          Quest investigatorQuest = new InvestigatorQuest(stateManager);
          Npc   investigator      = new Npc(investigatorQuest, stateManager, currentNpc);
          npcNode.attachChild(investigator);
          }  
          
        }  
      }
    npcNodeCleaner();
    }
  
  private void initCity() {

    for (int i = 0; i < npcNode.getQuantity(); i++) {  
      
      Node currentNpc = (Node) npcNode.getChild(i);
     
      try {
        
        Npc testNpc = (Npc) currentNpc;
        
        }
      
      catch(ClassCastException e) {
          
        if (currentNpc.getName().equals("Witness")) {
          Quest witnessQuest = new WitnessQuest(stateManager);
          Npc   witness      = new Npc(witnessQuest, stateManager, currentNpc);
          npcNode.attachChild(witness);
          }
        }  
      }
    npcNodeCleaner();  
      
    }
  
  private void initHayFarm() {
      
    for (int i = 0; i < npcNode.getQuantity(); i++) {  
      
      Node currentNpc = (Node) npcNode.getChild(i);
     
      try {
        
        Npc testNpc = (Npc) currentNpc;
        
        }
      
      catch(ClassCastException e) {
          
        if (currentNpc.getName().equals("BillyBob")) {
          Quest billyBobQuest  = new BillyBobQuest(stateManager);
          Npc   farmer         = new Npc(billyBobQuest, stateManager, currentNpc);
          npcNode.attachChild(farmer);
          }          
          
        }
      
      }
    
    npcNodeCleaner();      
      
    }
  
  private void npcNodeCleaner() {
      
    for (int i = 0; i < npcNode.getQuantity(); i++) {
      
      Node currentNpc = (Node) npcNode.getChild(i);
      
      try {
        Npc testNpc = (Npc) currentNpc;
        testNpc.model.removeFromParent();
        testNpc.attachChild(testNpc.model);
        }
      
      catch(ClassCastException e) {
        
        currentNpc.removeFromParent();
        
        }
        
      }
    
    for (int i = 0; i < npcNode.getQuantity(); i++) {
      
      Npc currentNpc = (Npc) npcNode.getChild(i);
      currentNpc.attachChild(currentNpc.model);
        
      }
      
    }
  
  //Inits the tester npc bob
  private void initTester(){
    Quest  quest  = new TestQuest(stateManager);
    Npc    tester = new Npc(quest, stateManager, (Node) npcNode.getChild("Bob"));
    }
  
  @Override
  public void update(float tpf) {
      
    //Iterate over list of npcs
    for (int i = 0; i < npcNode.getQuantity(); i++) {
      
      //Gets the current npc
      Npc npc            = (Npc) npcNode.getChild(i);
      Vector3f playerDir = player.model.getWorldTranslation().subtract(npc.model.getWorldTranslation());
       
      //Checks the distance of the npc to the player
      if (npc.model.getWorldTranslation().distance(player.model.getWorldTranslation()) < 5) {
        
        //Makes the npc look at the player within distance
        npc.phys.setViewDirection(playerDir);  
          
        //If the npc has not spoken make him speak, and set his lastSpoke time.
        if (!npc.hasSpoke) {
          npc.quest.act();
          npc.hasSpoke  = true;
          npc.lastSpoke = System.currentTimeMillis() / 1000;
          } 
      
        //Checks to see if the delay has been 5 seconds, to allow the npc to speak again
        else if (System.currentTimeMillis() / 1000 - npc.lastSpoke > 5) {
          npc.hasSpoke = false;
          }
        
        }
      }
    }
  }
