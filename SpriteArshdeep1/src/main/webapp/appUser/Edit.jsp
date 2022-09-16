<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing AppUser</title>
            <link rel="stylesheet" type="text/css" href="/SpriteArshdeep1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing AppUser</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{appUser.appUser.id}" title="Id" />
                    <h:outputText value="Groupname:"/>
                    <h:inputText id="groupname" value="#{appUser.appUser.groupname}" title="Groupname" />
                    <h:outputText value="Password:"/>
                    <h:inputText id="password" value="#{appUser.appUser.password}" title="Password" />
                    <h:outputText value="Userid:"/>
                    <h:inputText id="userid" value="#{appUser.appUser.userid}" title="Userid" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{appUser.edit}" value="Save">
                    <f:param name="jsfcrud.currentAppUser" value="#{jsfcrud_class['com.mycompany.spritearshdeep1.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][appUser.appUser][appUser.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{appUser.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentAppUser" value="#{jsfcrud_class['com.mycompany.spritearshdeep1.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][appUser.appUser][appUser.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{appUser.listSetup}" value="Show All AppUser Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
