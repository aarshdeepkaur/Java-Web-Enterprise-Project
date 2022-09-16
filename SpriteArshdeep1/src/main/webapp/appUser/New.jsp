<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New AppUser</title>
            <link rel="stylesheet" type="text/css" href="/SpriteArshdeep1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New AppUser</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{appUser.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Groupname:"/>
                    <h:inputText id="groupname" value="#{appUser.appUser.groupname}" title="Groupname" />
                    <h:outputText value="Password:"/>
                    <h:inputText id="password" value="#{appUser.appUser.password}" title="Password" />
                    <h:outputText value="Userid:"/>
                    <h:inputText id="userid" value="#{appUser.appUser.userid}" title="Userid" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{appUser.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{appUser.listSetup}" value="Show All AppUser Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
