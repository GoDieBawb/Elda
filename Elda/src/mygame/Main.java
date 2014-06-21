package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import mygame.camera.CameraManager;
import mygame.interaction.InteractionManager;
import mygame.items.ItemManager;
import mygame.monsters.MonsterManager;
import mygame.gui.GuiManager;
import mygame.npcs.NpcManager;
import mygame.scene.SceneManager;
import mygame.player.PlayerManager;
import mygame.interactable.InteractableManager;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
    Main app = new Main();
    app.start();
    }

    @Override
    public void simpleInitApp() {
    this.stateManager.attach(new PlayerManager());
    this.stateManager.attach(new ItemManager());
    this.stateManager.attach(new NpcManager());
    this.stateManager.attach(new MonsterManager());
    this.stateManager.attach(new InteractableManager());
    this.stateManager.attach(new SceneManager());
    this.stateManager.attach(new CameraManager());
    this.stateManager.attach(new InteractionManager());
    this.stateManager.attach(new GuiManager());
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
