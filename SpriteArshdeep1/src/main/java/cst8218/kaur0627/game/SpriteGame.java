/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.kaur0627.game;

import cst8218.kaur0627.entity.Sprite;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author arshd
 */
@Singleton
public class SpriteGame {
    
     private Integer x_size = 500;
     private Integer y_size = 500;
     
     private List<Sprite> spriteList;
     
     @Inject
     private GameSession session;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     
     private void doBounce (Sprite s)    {
            if (s.getX() < 0 && s.getxSpeed() < 0) {
                s.bounceLeftWall();
            }

             if (s.getY() < 0 && s.getySpeed() < 0) {
                s.bounceTopWall();
            }

              if (s.getX() < x_size && s.getxSpeed() < 0) {
                s.bounceRightWall();
            }

               if (s.getY() < y_size && s.getySpeed() < 0) {
                s.bounceBottomWall();
            }
                    
    }
    
    @PostConstruct
    public void go() {
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    spriteList =session.findAll();
                    
                    for(Sprite s : spriteList ){
                        s.move();
                        doBounce(s);
                    }
           
                  
                   try {
                       Thread.sleep(100);
                   }catch(InterruptedException e) {
                       e.printStackTrace();
                   }
                    
                }
            }
                
            
        }
  
            ).start();

}
}
