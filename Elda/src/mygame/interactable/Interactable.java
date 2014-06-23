/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.interactable;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;

/**
 *
 * @author Bob
 */
public abstract class Interactable extends Node {
  
  public Node   model;
  public String message;
  public String actionName;
  
  public Interactable(Node interactable){
    
    model = interactable;
    
    }
    
  abstract void act(AppStateManager stateManager);  
    
  }
