package com.sgu.infowksporga.business.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class ViewsDto.
 * Store all Workspace views configuration
 */
@Getter
@Setter
public class WorkspacesViewsDto extends AbstractDto implements Serializable {

  /** The attribute serialVersionUID. */
  private static final long serialVersionUID = 8342483985143844193L;

  /**
   * The workspace views.
   * <view name, View with Attributes>
   */
  private Map<String, View> workspaceViewsByName;

  /**
   * The workspace master views.
   * <view name, View with Attributes>
   */
  private Map<String, View> workspaceMasterViewsByName;

  /**
   * The workspace views.
   * <view Id, View with Attributes>
   */
  private Map<Integer, View> workspaceViewsById;

  /**
   * The workspace master views.
   * <view Id, View with Attributes>
   */
  private Map<Integer, View> workspaceMasterViewsById;

  /**
   * Constructor<br>.
   */
  public WorkspacesViewsDto() {
    super();
    workspaceViewsByName = new HashMap<String, View>(10);
    workspaceMasterViewsByName = new HashMap<String, View>(10);
    workspaceViewsById = new HashMap<Integer, View>(10);
    workspaceMasterViewsById = new HashMap<Integer, View>(10);
  }

  /**
   * Adds the view.
   *
   * @param viewName the view name
   * @param view the view
   */
  public void addView(View view) {
    workspaceViewsByName.put(view.getName(), view);
    if (view.getId() != null) {
      workspaceViewsById.put(view.getId(), view);
    }
  }

  /**
   * Adds the master view.
   *
   * @param viewName the view name
   * @param view the view
   */
  public void addMasterView(View view) {
    workspaceMasterViewsByName.put(view.getName(), view);
    if (view.getId() != null) {
      workspaceMasterViewsById.put(view.getId(), view);
    }
  }

  /**
   * Gets the preferred value.
   *
   * @return the preferred value
   */
  public View getPreferredView(String viewName) {
    if (workspaceViewsByName.get(viewName) != null) {
      return workspaceViewsByName.get(viewName);
    }

    return workspaceMasterViewsByName.get(viewName);
  }

  /**
   * Checks if is master view.
   *
   * @param viewName the view name
   * @return true, if checks if is master view
   */
  public boolean isMasterView(String viewName) {
    if (workspaceViewsByName.get(viewName) != null) {
      return false;
    }
    else if (workspaceMasterViewsByName.get(viewName) != null) {
      return true;
    }
    else {
      throw new TechnicalException();
    }
  }

  /**
   * Checks if is master view.
   *
   * @param viewId the view id
   * @return true, if checks if is master view
   */
  public boolean isMasterView(Integer viewId) {
    if (workspaceViewsById.get(viewId) != null) {
      return false;
    }
    else if (workspaceMasterViewsById.get(viewId) != null) {
      return true;
    }
    else {
      throw new TechnicalException("View with Id '" + viewId + "' n''existe pas");
    }
  }

  /**
   * Gets the view by id.
   *
   * @param viewId the view id
   * @return the view by id
   */
  public View getViewById(Integer viewId) {
    if (workspaceViewsById.get(viewId) != null) {
      return workspaceViewsByName.get(viewId);
    }

    return workspaceMasterViewsByName.get(viewId);
  }

  /**
   * set the attribute.
   *
   * @param viewName the view name
   * @param attributeName the config attribute
   * @param attributeValue the config value
   */
  public void setAttribute(String viewName, String attributeName, String attributeValue) {
    View view = workspaceViewsByName.get(viewName);
    if (view == null) {
      throw new TechnicalException("View named '" + viewName + "' must exists in workspace views list");
    }

    ViewAttribute viewAttribute = view.getAttributesAsMap().get(attributeName);
    if (viewAttribute == null) {
      viewAttribute = new ViewAttribute();
      viewAttribute.setName(attributeName);
      view.getAttributes().add(viewAttribute);
    }

    viewAttribute.setValue(attributeValue);
  }

}