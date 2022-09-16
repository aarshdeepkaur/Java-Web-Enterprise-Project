/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spritearshdeep1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import com.mycompany.spritearshdeep1.util.JsfUtil;
import com.mycompany.spritearshdeep1.util.PagingInfo;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.ERROR;
import cst8218.kaur0627.entity.AppUser;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

/**
 *
 * @author arshd
 */
public class AppUserController {
    @Resource 
    UserTransaction utx;
    public AppUserController() {
        pagingInfo = new PagingInfo();
        converter = new AppUserConverter();
    }
    private AppUser appUser = null;
    private List<AppUser> appUserItems = null;
    private AppUserFacade jpaController = null;
    private AppUserConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public AppUserFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (AppUserFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "appUserJpa");
        }
        return jpaController;
    }

    public SelectItem[] getAppUserItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getAppUserItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public AppUser getAppUser() {
        if (appUser == null) {
            appUser = (AppUser) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAppUser", converter, null);
        }
        if (appUser == null) {
            appUser = new AppUser();
        }
        return appUser;
    }

    public String listSetup() {
        reset(true);
        return "appUser_list";
    }

    public String createSetup() {
        reset(false);
        appUser = new AppUser();
        return "appUser_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(appUser);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("AppUser was successfully created.");
            } else {
               // JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
          //  JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("appUser_detail");
    }

    public String editSetup() {
        return scalarSetup("appUser_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        appUser = (AppUser) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAppUser", converter, null);
        if (appUser == null) {
            String requestAppUserString = JsfUtil.getRequestParameter("jsfcrud.currentAppUser");
            JsfUtil.addErrorMessage("The appUser with id " + requestAppUserString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String appUserString = converter.getAsString(FacesContext.getCurrentInstance(), null, appUser);
        String currentAppUserString = JsfUtil.getRequestParameter("jsfcrud.currentAppUser");
        if (appUserString == null || appUserString.length() == 0 || !appUserString.equals(currentAppUserString)) {
            String outcome = editSetup();
            if ("appUser_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit appUser. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(appUser);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("AppUser was successfully updated.");
            } else {
           //     JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
          //  JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentAppUser");
        Long id = new Long(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("AppUser was successfully deleted.");
            } else {
           //     JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
          //  JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
       // if((ERROR)){
         //   return relatedControllerOutcome;
       // }
        return listSetup();
    }

    public List<AppUser> getAppUserItems() {
        if (appUserItems == null) {
            getPagingInfo();
            appUserItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return appUserItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "appUser_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "appUser_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        appUser = null;
        appUserItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        AppUser newAppUser = new AppUser();
        String newAppUserString = converter.getAsString(FacesContext.getCurrentInstance(), null, newAppUser);
        String appUserString = converter.getAsString(FacesContext.getCurrentInstance(), null, appUser);
        if (!newAppUserString.equals(appUserString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
