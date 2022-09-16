package com.mycompany.spritearshdeep1;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application.
 * @author Juneau
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

@ApplicationPath("resources")
public class JAXRSConfiguration extends Application {
    
}
