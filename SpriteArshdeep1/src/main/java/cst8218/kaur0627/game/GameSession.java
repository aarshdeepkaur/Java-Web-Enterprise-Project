/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.kaur0627.game;

import cst8218.kaur0627.entity.Sprite;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arshd
 */
@Stateless
public class GameSession {
    
    
     @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Sprite> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Sprite.class));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public void edit(Sprite entity) {
        getEntityManager().merge(entity);
    }


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
