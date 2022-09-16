/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.kaur0627.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arshd
 */
@Entity
@XmlRootElement
public class Sprite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
        private Integer x;
    private Integer y;
    private Integer xSpeed;
    private Integer ySpeed;

    
    public Integer getX(){
        return x;
    }
    
    public void setX(Integer x){
        this.x = x;
    }
    
      public Integer getY(){
        return y;
    }
    
    public void setY(Integer y){
        this.y = y;
    }
    
      public Integer getxSpeed(){
        return xSpeed;
    }
    
    public void setxSpeed(Integer xSpeed){
        this.xSpeed = xSpeed;
    }
    
     public Integer getySpeed(){
        return ySpeed;
    }
    
    public void setySpeed(Integer ySpeed){
        this.ySpeed = ySpeed;
    } 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void move(){
        x+=xSpeed;
        y+=ySpeed;
    }
    
    public void bounceTopWall(){
        ySpeed = - ySpeed;
        
    }
    
     public void bounceBottomWall(){
        ySpeed = - ySpeed;
        
    }

      public void bounceLeftWall(){
        xSpeed = - xSpeed;
        
    }
      
       public void bounceRightWall(){
        xSpeed = - xSpeed;
        
    }
       
       public void updateSprite(Sprite newSprite) 
       {
          if(newSprite.getX() != null)
          {
            this.setX(newSprite.getX());
          }
          
        if(newSprite.getY()!= null)
        {
            this.setY(newSprite.getY());
        }
        
        if(newSprite.getxSpeed()!= null)
        {
            this.setxSpeed(newSprite.getxSpeed());
        }
        
        if(newSprite.getySpeed()!= null)
        {
            this.setySpeed(newSprite.getySpeed());
        }

       }

    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() :0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
        
        if (!(object instanceof Sprite)) {
            return false;
        }
        Sprite other = (Sprite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.kaur0627.entity.Sprite[ id=" + id + " ]";
    }
    
}
