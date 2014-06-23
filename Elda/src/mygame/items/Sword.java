/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.items;

import com.jme3.app.state.AppStateManager;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.monsters.Monster;
import mygame.monsters.MonsterManager;
import mygame.player.Player;
import mygame.player.PlayerManager;

/**
 *
 * @author Bob
 */
public class Sword extends Item {
  
  public Sword(Node item) {
    super(item);
    damage = 5;
    range  = 1;
    equipRotation = new Vector3f(0, -90, 0);
    actionName = "Attack";
    }
  
  //Because this is a sword it acts like a sword and swings
  @Override
  public void act(AppStateManager stateManager) {
    
    Node monsterNode         = stateManager.getState(MonsterManager.class).monsterNode;
    CollisionResults results = new CollisionResults();
    Player           player  = stateManager.getState(PlayerManager.class).player;
    monsterNode.collideWith(player.model.getWorldBound(), results);
    
    if (results.size() > 0) {
      
      Monster hitMonster = (Monster) results.getCollision(0).getGeometry().getParent().getParent();
        
      if (results.getCollision(0).getDistance() < range) {
        
        hitMonster.health = hitMonster.health - damage;
        System.out.println("Youve hit " + hitMonster);
      
        }
      
      }
      
    }
  
  @Override
  public void equip(Player player) {
    super.equip(player);
    model.setLocalScale(3);
    model.rotate(equipRotation.x, equipRotation.y, equipRotation.z);
    }

  }
