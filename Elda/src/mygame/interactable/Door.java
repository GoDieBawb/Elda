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
    assignScene(this);
    }
  
  private void assignScene(Door door) {
    
      
    if (name.equalsIgnoreCase("TestDoor")){
      scenePath     = "Scenes/TestScene.j3o";
      startSpot     = new Vector3f(0,0,0);
      locked        = true;
      door.message  = "Door to Bobs test scene locked: " + locked;
      setName(name);
      }
    
    else if (name.equalsIgnoreCase("StartGate")) {
      scenePath     = "Scenes/Road.j3o";
      startSpot     = new Vector3f(0,0,0);
      locked        = true;
      door.message  = "Gate to the road: " + locked;
      setName(name);
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
