<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing AppUser Items</title>
            <link rel="stylesheet" type="text/css" href="/SpriteArshdeep1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing AppUser Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No AppUser Items Found)<br />" rendered="#{appUser.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{appUser.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{appUser.pagingInfo.firstItem + 1}..#{appUser.pagingInfo.lastItem} of #{appUser.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{appUser.prev}" value="Previous #{appUser.pagingInfo.batchSize}" rendered="#{appUser.pagingInfo.firstItem >= appUser.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{appUser.next}" value="Next #{appUser.pagingInfo.batchSize}" rendered="#{appUser.pagingInfo.lastItem + appUser.pagingInfo.batchSize <= appUser.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{appUser.next}" value="Remaining #{appUser.pagingInfo.itemCount - appUser.pagingInfo.lastItem}"
                                   rendered="#{appUser.pagingInfo.lastItem < appUser.pagingInfo.itemCount && appUser.pagingInfo.lastItem + appUser.pagingInfo.batchSize > appUser.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{appUser.appUserItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Groupname"/>
                            </f:facet>
                            <h:outputText value="#{item.groupname}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Password"/>
                            </f:facet>
                            <h:outputText value="#{item.password}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Userid"/>
                            </f:facet>
                            <h:outputText value="#{item.userid}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{appUser.detailSetup}">
                                <f:param name="jsfcrud.currentAppUser" value="#{jsfcrud_class['com.mycompany.spritearshdeep1.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][appUser.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{appUser.editSetup}">
                                <f:param name="jsfcrud.currentAppUser" value="#{jsfcrud_class['com.mycompany.spritearshdeep1.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][appUser.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{appUser.remove}">
                                <f:param name="jsfcrud.currentAppUser" value="#{jsfcrud_class['com.mycompany.spritearshdeep1.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][appUser.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{appUser.createSetup}" value="New AppUser"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
