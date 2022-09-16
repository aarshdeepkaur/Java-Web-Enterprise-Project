package com.mycompany.spritearshdeep1.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@DatabaseIdentityStoreDefinition(
   dataSourceLookup = "${'java:comp/DefaultDataSource'}",
   callerQuery = "#{'select password from app.appuser where userId = ?'}",
   groupsQuery = "select groupname from app.appuser where userId = ?",
   hashAlgorithm = PasswordHash.class,
   priority = 10
)


/*
@FormAuthenticationMechanismDefinition(
  loginToContinue = @LoginToContinue(
    loginPage = "/index.xhtml", //which page gets shown to user to get Uname and password
    errorPage = "/index.xhtml"))
*/
@BasicAuthenticationMechanismDefinition
@ApplicationScoped
@Named
@Path("javaee8")
public class JavaEE8Resource {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}
