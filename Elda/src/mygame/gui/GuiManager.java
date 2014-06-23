package mygame.gui;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.font.BitmapFont.Align;
import com.jme3.font.BitmapFont.VAlign;
import com.jme3.font.LineWrapMode;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import com.jme3.texture.Texture;
import java.util.ArrayList;
import mygame.interaction.InteractionManager;
import mygame.items.Item;
import mygame.player.Player;
import mygame.player.PlayerManager;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.controls.extras.Indicator;
import tonegod.gui.controls.extras.android.Joystick;
import tonegod.gui.controls.scrolling.ScrollPanel;
import tonegod.gui.controls.windows.AlertBox;
import tonegod.gui.core.Element;
import tonegod.gui.core.Screen;
import tonegod.gui.core.layouts.FlowLayout;
import tonegod.gui.effects.Effect;

/**
*
* @author Bob
*/
public class GuiManager extends AbstractAppState {

  private SimpleApplication app;
  private AppStateManager   stateManager;
  private AssetManager      assetManager;
  private AlertBox          alert;
  private Screen            screen;
  private ScrollPanel       invMenu;
  private ButtonAdapter     invButton;
  private boolean           invAttached;
  private Joystick          stick;
  private Player            player;
  public  ButtonAdapter     interactButton;
  private String            delayedMessage;
  private String            delayedTitle;
  private int               alertDelay;
  private long              delayStart;
  private boolean           hasAlert;
  private String            alertTitle;
  private Indicator         healthInd;
  private ArrayList         buttonList;
  private GuiManager        gui;
  
  @Override
  public void initialize(AppStateManager stateManager, Application app){
    super.initialize(stateManager, app);
    this.app          = (SimpleApplication) app;
    this.stateManager = this.app.getStateManager();
    this.assetManager = this.app.getAssetManager();
    player            = stateManager.getState(PlayerManager.class).player;
    alertTitle        = "";
    screen            = new Screen(app, "tonegod/gui/style/atlasdef/style_map.gui.xml");
    buttonList        = new ArrayList();
    gui               = this;
    screen.setUseTextureAtlas(true,"tonegod/gui/style/atlasdef/atlas.png");
    screen.setUseMultiTouch(true);
    this.app.getGuiNode().addControl(screen);
    this.app.getInputManager().setSimulateMouse(true);
    initInteractButton();
    initAlertBox();
    initHealthLevel();
    initInventoryButton();
    initInventoryWindow();
    //initJoyStick();
    }
  
  private void initInteractButton(){
    interactButton = new ButtonAdapter( screen, "InteractButton", new Vector2f(15, 15) ) {
    
    @Override
      public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
        player.swing(this.app.getStateManager());
        }
      };
    
    interactButton.setDimensions(screen.getWidth()/8, screen.getHeight()/10);
    interactButton.setPosition(screen.getWidth() / 1.1f - interactButton.getHeight(), screen.getHeight() / 1.1f - interactButton.getHeight());
    //interactButton.setFont("Interface/Fonts/Impact.fnt");
    interactButton.setText("Check");
    screen.addElement(interactButton);
    
    }
  
  private void initHealthLevel(){
    healthInd = new Indicator(
      screen,
      "Health Ind",
      new Vector2f(15,22),
      Indicator.Orientation.HORIZONTAL
      ) {

    @Override
      public void onChange(float currentValue, float currentPercentage) {
        }
      };
    
    healthInd.setMaxValue(20);
    healthInd.setCurrentValue(0);
    healthInd.setIndicatorColor(ColorRGBA.Red);
    healthInd.setText("Health Level");
    healthInd.setTextWrap(LineWrapMode.NoWrap);
    healthInd.setTextVAlign(VAlign.Center);
    healthInd.setTextAlign(Align.Center);
    healthInd.setFont("Interface/Fonts/Impact.fnt");
    healthInd.setBaseImage(screen.getStyle("Window").getString("defaultImg"));
    healthInd.setIndicatorPadding(new Vector4f(7,7,7,7));
    healthInd.setDimensions(150, 30);
    screen.addElement(healthInd);
    healthInd.setPosition(screen.getWidth() - healthInd.getWidth(), screen.getHeight() - healthInd.getHeight());
    
    }
  
  private void initAlertBox(){
    alert = new AlertBox(screen, Vector2f.ZERO) {
    @Override
    public void onButtonOkPressed(MouseButtonEvent evt, boolean toggled) {
      hideWithEffect();
      }
    };
    
    alert.setButtonOkText("Ok");
    alert.setLockToParentBounds(true);
    alert.setClippingLayer(alert);
    alert.setMinDimensions(new Vector2f(150,180));
    alert.setIsResizable(true);
    screen.addElement(alert);
    alert.hide();
    }
  
  public void showAlert(String speaker, String text){
    alert.showWithEffect();
    alert.setWindowTitle(speaker);
    alert.setMsg(text);
    alertTitle = speaker;
    alert.setPosition(0,0);
    }
  
  public String getAlertTitle(){
    return alertTitle;
    }
  
  public void delayAlert(String speaker, String text, int delay){
    hasAlert = true;
    delayStart = System.currentTimeMillis() / 1000;
    alertDelay = delay;
    delayedTitle = speaker;
    delayedMessage = text;
    }  

  private void initJoyStick(){
    stick = new Joystick(screen, Vector2f.ZERO, (int)(screen.getWidth()/6)) {
    
    @Override
    public void onUpdate(float tpf, float deltaX, float deltaY) {
        float dzVal = .2f; // Dead zone threshold
            if (deltaX < -dzVal) {
                stateManager.getState(InteractionManager.class).up = false;
                stateManager.getState(InteractionManager.class).down = false;
                stateManager.getState(InteractionManager.class).left = true;
                stateManager.getState(InteractionManager.class).right = false;
            } else if (deltaX > dzVal) {
                stateManager.getState(InteractionManager.class).up = false;
                stateManager.getState(InteractionManager.class).down = false;
                stateManager.getState(InteractionManager.class).left = false;
                stateManager.getState(InteractionManager.class).right = true;
            } else if (deltaY < -dzVal) {
                stateManager.getState(InteractionManager.class).left = false;
                stateManager.getState(InteractionManager.class).right = false;
                stateManager.getState(InteractionManager.class).down = true;
                stateManager.getState(InteractionManager.class).up = false;
            } else if (deltaY > dzVal) {
                stateManager.getState(InteractionManager.class).left = false;
                stateManager.getState(InteractionManager.class).right = false;
                stateManager.getState(InteractionManager.class).down = false;
                stateManager.getState(InteractionManager.class).up = true;
            } else {
                stateManager.getState(InteractionManager.class).left = false;
                stateManager.getState(InteractionManager.class).right = false;
                stateManager.getState(InteractionManager.class).up = false;
                stateManager.getState(InteractionManager.class).down = false;
            }
            
          player.speedMult = FastMath.abs(deltaY);
          }
        };
    
      // getGUIRegion returns region info “x=0|y=0|w=50|h=50″, etc
      TextureKey key = new TextureKey("Textures/barrel/D.png", false);
      Texture tex = assetManager.loadTexture(key);
      stick.setTextureAtlasImage(tex, "x=20|y=20|w=120|h=35");
      stick.getThumb().setTextureAtlasImage(tex, "x=20|y=20|w=120|h=35");
      screen.addElement(stick, true);
      stick.setPosition(screen.getWidth()/10 - stick.getWidth()/2, screen.getHeight() / 10f - interactButton.getHeight()/5);
     
      // Add some fancy effects
      Effect fxIn = new Effect(Effect.EffectType.FadeIn, Effect.EffectEvent.Show,.5f);
      stick.addEffect(fxIn);
      Effect fxOut = new Effect(Effect.EffectType.FadeOut, Effect.EffectEvent.Hide,.5f);
      stick.addEffect(fxOut);
      stick.show();
      
      }
  
  private void updateHud(){
    healthInd.setCurrentValue(player.health);
    }
  
  private void initInventoryButton(){
    invButton = new ButtonAdapter( screen, "invButton", new Vector2f(15, 15) ) {
    
    @Override
      public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
        showInventory();
        }
      };
    
    invButton.setDimensions(screen.getWidth()/8, screen.getHeight()/10);
    invButton.setPosition(0, 0);
    //invButton.setFont("Interface/Fonts/Impact.fnt");
    invButton.setText("Inventory");
    screen.addElement(invButton);
    }
  
  private void initInventoryWindow(){
    invMenu = new ScrollPanel(screen, Vector2f.ZERO, new Vector2f(150,300));
    FlowLayout layout = new FlowLayout(screen, "margins 0 0 0 0","pad 0 0 0 0");
    invMenu.getScrollableArea().setLayout(layout);
    invMenu.setText("Inventory");
    screen.addElement(invMenu);
    invMenu.hide();
    }
  
  private void showInventory(){
    
    if (invAttached){
      invMenu.hide();
      invAttached = false;
      }
    
    else {
        
      invAttached = true;
      invMenu.show();
      
      updateInventory();
      
      }
    
    }
  
  public void updateInventory() {
    if (invAttached) {

      for (int i  = 0; i < buttonList.size(); i++){
        Element currentElement = (Element) buttonList.get(i);
        invMenu.removeScrollableContent(currentElement);
        }
      
      buttonList = new ArrayList();
      
      for (int i = 0; i < player.inventory.size(); i++) {
       
        String a                      = "Button Number " + String.valueOf(i);
        final Item      currentItem   = (Item) player.inventory.get(i);     
        InventoryButton currentButton = new InventoryButton(screen, a ,new Vector2f(12,12)) {
          
          @Override
          public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
            
            if (player.equippedItem == currentItem) {
              item.unequip(player);
              interactButton.setText("Check");
              }
            
            else {
              item.equip(player);
              interactButton.setText(item.actionName);
              }
            
            updateInventory();
            
            }     
          
          };
        
        buttonList.add(currentButton);
        invMenu.addScrollableContent(currentButton);
        currentButton.item =    currentItem;
        currentButton.setText(currentItem.getName());
        currentButton.setDimensions(invMenu.getWidth()/2, invMenu.getWidth()/2);
        if (currentButton.item == player.equippedItem)
        currentButton.getMaterial().setColor("Color", ColorRGBA.Red);
        else
        currentButton.getMaterial().setColor("Color", ColorRGBA.LightGray);
        
        }
      
    invMenu.getScrollableArea().layoutChildren();
    invMenu.reshape();
    invMenu.setPosition(0, screen.getHeight()- (invMenu.getHeight() + invButton.getHeight())/**screen.getHeight()/2 - invMenu.getHeight()/2**/);
      
      } 
      
    }
  
  @Override
  public void update(float tpf){
    
    updateHud();
      
    if (hasAlert)
    if (System.currentTimeMillis()/1000 - delayStart > alertDelay){
      showAlert(delayedTitle, delayedMessage);
      hasAlert = false;
      }
    }
  
  }