/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package mygame.interaction;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector3f;
import mygame.camera.CameraManager;
import mygame.player.Player;
import mygame.player.PlayerManager;

/**
*
* @author Bob
*/
public class InteractionManager extends AbstractAppState implements ActionListener {

  private SimpleApplication app;
  private AppStateManager   stateManager;
  private AssetManager      assetManager;
  private InputManager      inputManager;
  private Player            player;
  private Vector3f walkDirection = new Vector3f();
  private Vector3f camDir = new Vector3f();
  private Vector3f camLeft = new Vector3f();
  public boolean inv = false, left = false, right = false, up = false, down = false, click = false;
  
  @Override
  public void initialize(AppStateManager stateManager, Application app){
    super.initialize(stateManager, app);
    this.app = (SimpleApplication) app;
    this.stateManager = this.app.getStateManager();
    this.assetManager = this.app.getAssetManager();
    this.inputManager = this.app.getInputManager();
    this.player = this.stateManager.getState(PlayerManager.class).player;
    setUpKeys();
    }
  
  //Sets up key listeners for the action listener
  private void setUpKeys(){
    inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
    inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
    inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
    inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
    inputManager.addMapping("Click", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    inputManager.addMapping("Inventory", new KeyTrigger(KeyInput.KEY_E));
    inputManager.addMapping("Space", new KeyTrigger(KeyInput.KEY_SPACE));
    inputManager.addListener(this, "Up");
    inputManager.addListener(this, "Down");
    inputManager.addListener(this, "Left");
    inputManager.addListener(this, "Right");
    inputManager.addListener(this, "Click");
    inputManager.addListener(this, "Inventory");
    inputManager.addListener(this, "Space");
    }

  public void onAction(String binding, boolean isPressed, float tpf) {
    
    if (binding.equals("Inventory")) {
      inv = isPressed;
      if (isPressed){
      inputManager.setCursorVisible(true);
      stateManager.getState(CameraManager.class).cam.setDragToRotate(true);
      }
      else {
      inputManager.setCursorVisible(false);
      stateManager.getState(CameraManager.class).cam.setDragToRotate(false);
      }
    }
      
    if (binding.equals("Space") && !isPressed) {
      
      if (!player.hasSwung)
      player.swing(stateManager);
      
      }

    else if (binding.equals("Left")) {
        
      left = isPressed;

    } else if (binding.equals("Right")) {
  
      right = isPressed;

      }
    else  if (binding.equals("Down")) {
      
      down = isPressed;
    
    } else if (binding.equals("Up")) {
        
      up = isPressed;
      
    }
        
  }
  
  @Override
  public void update(float tpf){
        camDir.set(this.app.getCamera().getDirection()).multLocal(10.0f, 0.0f, 10.0f);
        camLeft.set(this.app.getCamera().getLeft()).multLocal(10.0f);
        walkDirection.set(0, 0, 0);
        
        
        if (up) {
            walkDirection.addLocal(camDir);
            player.run();
        }
        else if (down) {
            walkDirection.addLocal(camDir.negate());
            player.run();
        }
        
        if (left) {
            walkDirection.addLocal(camLeft);
            player.run();
        }
        else if (right) {
            walkDirection.addLocal(camLeft.negate());
            player.run();
            }
        
        if (!up && !down) {
            player.idle();
        }
       
       boolean isAndroid  = "Dalvik".equals(System.getProperty("java.vm.name")); 
       if (!isAndroid)
       player.speedMult = 1;
       
       player.phys.setWalkDirection(walkDirection.mult(player.speedMult));
       player.phys.setViewDirection(camDir);

    }
  
  }
