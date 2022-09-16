/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import cst8218.kaur0627.entity.Sprite;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author arshd
 */
@Stateless
@DeclareRoles({"Admin","jsfGroup"})

@Path("cst8218.kaur0627.entity.sprite")
public class SpriteFacadeREST extends AbstractFacade<Sprite> {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    public SpriteFacadeREST() {
        super(Sprite.class);
    }
    


 /*@POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response mycreate(Sprite entity) {
        if(entity.getId() == null){
           super.create(entity); 
            return Response.ok().entity(entity).build();
        }else {
            Sprite newSprite = find(entity.getId());
            if (newSprite == null){
                return Response.status(Response.Status.BAD_REQUEST).build(); 
            }else {
                if(entity.getX() != null) newSprite.setX(entity.getX());
                super.edit(newSprite);
                return Response.ok().build();
                
        
      }
        }     
     //return Response.status(Response.Status.BAD_REQUEST).build();
     
    Hints
    }
    */
    
    /**
     * POST on the root resource (sprite table) 
     * It will create new entity only if there is no older id.
     * @return bad_request
     * @param entity
     */
    @RolesAllowed({"Admin"}) //administration allows to post
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createPost(Sprite entity) {
        if(entity.getId() == null){
            super.create(entity);
            return Response.status(Response.Status.CREATED).entity(entity).build();
        }
        else{
            Sprite newSprite = super.find(entity.getId());
            if(newSprite != null){
                newSprite.updateSprite(entity);
                super.edit(newSprite);
                return Response.noContent().build(); 
            }
           return Response.status(Response.Status.BAD_REQUEST).build();  
        }
    }
    
   /**
     * A Sprite id should be replaced by the Specific id.
     * @param entity
     */
    @RolesAllowed({"Admin","jsfGroup"}) //administration allows to post
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Sprite entity)
    {
        super.edit(entity);
    }
    public Response editPut(@PathParam("id") Long id, Sprite entity) 
    {
        
        if(entity.getId() == null || !entity.getId().equals(id))
        
        {
            return Response.status(Response.Status.BAD_REQUEST).build(); 
        }
        else
        {
            Sprite newSprite = super.find(entity.getId());
            if(newSprite != null)
                {
                    super.edit(entity);
                    return Response.noContent().build(); 
                }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }  
    
   /**
     * @param entity
     */
    @RolesAllowed({"Admin"}) //administration allows to post
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editPost(@PathParam("id") Long id, Sprite entity) 
    {
        if(entity.getId() == null || !entity.getId().equals(id))
        {
            return Response.status(Response.Status.BAD_REQUEST).build(); 
        }
        else
            {
                    Sprite newSprite = super.find(entity.getId());
                    if(newSprite != null)
                    {
                         newSprite.updateSprite(entity);
                         super.edit(newSprite);
                         return Response.noContent().build(); 
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @RolesAllowed({"Admin","jsfGroup"}) //administration allows to post
    @PUT
    @Path("/")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editSpriteOnRoot(Sprite newSprite) 
    {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @RolesAllowed({"Admin","jsfGroup"}) //administration allows to post
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id)
    {
        super.remove(super.find(id));
    }
   
    @RolesAllowed({"Admin","jsfGroup"})  //administration allows to post
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Sprite find(@PathParam("id") Long id)
    {
        return super.find(id);
    }
    @RolesAllowed({"Admin","jsfGroup"}) //administration allows to post
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findAll() 
    {
        return super.findAll();
    }
    @RolesAllowed({"Admin","jsfGroup"}) //administration allows to post
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) 
    {
        return super.findRange(new int[]{from, to});
    }
    @RolesAllowed({"Admin","jsfGroup"}) //administration allows to post
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() 
    {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
    
}
