/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.interactable;

import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.gui.GuiManager;
import mygame.scene.SceneManager;

/**
 *
 * @author Bob
 */
public class Door extends Interactable {
  
  public String       scenePath;
  public SceneManager sceneManager;
  public Vector3f     startSpot;
  public boolean      locked;
  private GuiManager  gui;
    
  public Door(Node interactable, AppStateManager stateManager) {
    super(interactable);
    sceneManager = stateManager.getState(SceneManager.class);
    gui          = stateManager.getState(GuiManager.class);
    setName(String.valueOf(interactable.getUserData("Name")));
    actionName   = "Open";
    assignScene(stateManager);
    }
  
  private void assignScene(AppStateManager stateManager) {
    
    if (name.equalsIgnoreCase("TestDoor")){
      scenePath     = "Scenes/TestScene.j3o";
      startSpot     = new Vector3f(0,0,0);
      locked        = true;
      message       = "Door to Bobs test scene locked: " + locked;
      setName(name);
      }
    
    else if (name.equalsIgnoreCase("StartGate")) {
      scenePath     = "Scenes/Road.j3o";
      startSpot     = new Vector3f(-50,0,-50);
      locked        = true;
      message       = "Gate to the road Locked: " + locked;
      setName(name);
      }

    else if (name.equalsIgnoreCase("StartingTown")) {
      scenePath     = "Scenes/StartingTown.j3o";
      startSpot     = new Vector3f(-45,0,-45);
      locked        = false;
      message       = "Gate to Starting Town";
      setName(name);
      }
    
    else if (name.equalsIgnoreCase("MissionDairy")) {
      scenePath     = "Scenes/MissionDairy.j3o";
      startSpot     = new Vector3f(55, 2 ,-35);
      locked        = false;
      message       = "Gate to Mission Dairy";
      setName(name);
      }
    
    else if (name.equalsIgnoreCase("DairyGate")) {
      scenePath     = "Scenes/Road.j3o";  
      startSpot     = new Vector3f(-10, 2 ,-50);
      locked        = false;
      message       = "Gate to Road";
      setName(name);
      }

    else if (name.equalsIgnoreCase("AbuDesert")) {
      scenePath     = "Scenes/AbuDesert.j3o";  
      startSpot     = new Vector3f(-12, 2 ,-25);
      locked        = false;
      message       = "Gate to Abu Desert";
      setName(name);
      }

    else if (name.equalsIgnoreCase("DesertGate")) {
      scenePath     = "Scenes/Road.j3o";  
      startSpot     = new Vector3f(-52, 3 ,6);
      locked        = false;
      message       = "Gate to Road";
      setName(name);
      }
    
    else if (name.equalsIgnoreCase("LostForest")) {
      scenePath     = "Scenes/LostForest.j3o";  
      startSpot     = new Vector3f(-21, 3 , 55);
      locked        = false;
      message       = "Gate to Lost Forest";
      setName(name);
      }

    else if (name.equalsIgnoreCase("ForestGate")) {
      scenePath     = "Scenes/Road.j3o";  
      startSpot     = new Vector3f(-52, 3 ,52);
      locked        = false;
      message       = "Gate to Road";
      setName(name);
      }

    else if (name.equalsIgnoreCase("HayFarm")) {
      scenePath     = "Scenes/HayFarm.j3o";
      message       = "Gate to Hay Farm";
      startSpot     = new Vector3f(-26, 3 , -3);
      }

    else if (name.equalsIgnoreCase("FarmGate")) {
      scenePath     = "Scenes/Road.j3o";
      message       = "Gate to Hay Farm";
      startSpot     = new Vector3f(-25, 1 , 51);
      }

    else if (name.equalsIgnoreCase("ZeldarsCave")) {
      scenePath     = "Scenes/Cave.j3o";
      message       = "Door to Zeldar's Cave";
      startSpot     = new Vector3f(-34, 1 ,50);
      }

    else if (name.equalsIgnoreCase("CaveDoor")) {
      scenePath     = "Scenes/Road.j3o";
      message       = "Door to Road";
      startSpot     = new Vector3f(45, 2 , 58);
      }

    else if (name.equalsIgnoreCase("Castle")) {
      scenePath     = "Scenes/City.j3o";
      message       = "Gate to City";
      startSpot     = new Vector3f(-1, 3 , 21);
      }

    else if (name.equalsIgnoreCase("CastleGate")) {
      scenePath     = "Scenes/Road.j3o";
      message       = "Gate to Road";
      startSpot     = new Vector3f(51, 3 , -53);
      }
    
    else if (name.equalsIgnoreCase("EldasCastle")) {
      scenePath     = "Scenes/EldasCastle.j3o";
      message       = "Gate to Elda's Castle";
      startSpot     = new Vector3f(-2, 3 , 25);
      }

    else if (name.equalsIgnoreCase("EldasGate")) {
      scenePath     = "Scenes/City.j3o";
      message       = "Gate to City";
      startSpot     = new Vector3f(-3, 1 , -25);
      }

    else if (name.equalsIgnoreCase("GuardGate")) {
      locked        = true;
      message       = "This gate looks well guarded";
      }

    else if (name.equalsIgnoreCase("CastleDoor")) {
      scenePath     = "Scenes/CastleInterior.j3o";
      message       = "Door to castle's interior";
      startSpot     = new Vector3f(-4, 1 , 29);
      }

    else if (name.equalsIgnoreCase("CourtYard")) {
      scenePath     = "Scenes/EldasCastle.j3o";
      message       = "Door to Castle Courtyard";
      startSpot     = new Vector3f(-3, 1 , 11);
      }
    
    else {
      scenePath = null;
      }
    
    }
  
  @Override
  public void act(AppStateManager stateManager){
      
    if (locked)
    gui.showAlert(name, "This seems to be locked");
    else
    sceneManager.initScene(scenePath, startSpot);
    }
    
  }
