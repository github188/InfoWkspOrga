package com.sgu.infowksporga.jfx.workspace.dlg.mvc;

import java.util.Date;

import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.core.framework.pivot.UserInfo;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.comparator.WorkspaceComparatorOnOrder;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.jfx.component.cbb.CbbOwnerItemVo;
import com.sgu.infowksporga.jfx.project.CbbProjectItemVo;
import com.sgu.infowksporga.jfx.workspace.dlg.mvc.panel.cbb.CbbPartageItemVo;
import com.sgu.infowksporga.jfx.workspace.dlg.mvc.panel.cbb.CbbWorkspaceItemVo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class WorkspaceDlgModel.
 */
@Slf4j
@Setter
@Getter
public class WorkspaceDlgModel extends AGModel<WorkspaceDlgViewFxml, WorkspaceDlgController> {

  /** The Constant comparator. */
  private static final WorkspaceComparatorOnOrder comparator = new WorkspaceComparatorOnOrder();

  //----------------------------------------------------------------------
  // List of the property used to bind screen components with this Model
  // Bindings are initialized by the controller @see{controller.bindComponentsWithPojo()}
  //----------------------------------------------------------------------
  private Workspace workspace = new Workspace();

  // IdentityCard panel Binding
  private StringProperty idProperty = new SimpleStringProperty();
  private StringProperty nameProperty = new SimpleStringProperty();
  private ObjectProperty<CbbOwnerItemVo> ownerProperty = new SimpleObjectProperty<CbbOwnerItemVo>();
  private StringProperty descriptionProperty = new SimpleStringProperty();

  // Reference panel Binding
  private ObjectProperty<CbbWorkspaceItemVo> parentProperty = new SimpleObjectProperty<CbbWorkspaceItemVo>();
  private ObjectProperty<CbbWorkspaceItemVo> masterProperty = new SimpleObjectProperty<CbbWorkspaceItemVo>();
  private ObjectProperty<CbbProjectItemVo> projectProperty = new SimpleObjectProperty<CbbProjectItemVo>();

  // Configuration panel Binding
  private IntegerProperty wkspPositionProperty = new SimpleIntegerProperty();
  private BooleanProperty enableProperty = new SimpleBooleanProperty();
  private BooleanProperty childrenAllowedProperty = new SimpleBooleanProperty();
  private StringProperty baseFolderProperty = new SimpleStringProperty();
  private StringProperty categoryProperty = new SimpleStringProperty();
  private StringProperty tagsProperty = new SimpleStringProperty();
  private StringProperty customerProperty = new SimpleStringProperty();
  private ObjectProperty<CbbPartageItemVo> partageProperty = new SimpleObjectProperty<CbbPartageItemVo>();

  // Style panel Binding
  private BooleanProperty boldProperty = new SimpleBooleanProperty();
  private BooleanProperty italicProperty = new SimpleBooleanProperty();
  private BooleanProperty strikeProperty = new SimpleBooleanProperty();
  private BooleanProperty underlineProperty = new SimpleBooleanProperty();
  private StringProperty iconProperty = new SimpleStringProperty();
  private StringProperty colorProperty = new SimpleStringProperty();
  private StringProperty bgColorProperty = new SimpleStringProperty();

  // Horodate panel Binding
  private StringProperty createdByProperty = new SimpleStringProperty();
  private ObjectProperty<Date> createdDateProperty = new SimpleObjectProperty<Date>();
  private StringProperty lastModifiedByProperty = new SimpleStringProperty();
  private ObjectProperty<Date> lastModifiedDateProperty = new SimpleObjectProperty<Date>();

  //------------------------------------------------------------
  /**
   * The Constructor.
   */
  public WorkspaceDlgModel() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

  }

  /**
   * Map model to workspace.
   *
   * @return the workspace
   */
  public Workspace mapModelToWorkspace() {
    final Workspace workspace = new Workspace();

    //----------------------------
    // IdentityCard panel Binding
    //----------------------------
    if (UtilString.isNotBlank(idProperty.getValue())) {
      workspace.setId(idProperty.getValue());
    }
    else {
      workspace.setId(Workspace.I_AM_A_NEW_WORKSPACE);
    }
    workspace.setCategory(categoryProperty.getValue());
    workspace.setTags(tagsProperty.getValue());
    workspace.setName(nameProperty.getValue());
    workspace.setDescription(view().getPnlIdentityCardController().getHtmlEdtDescription().getHtmlText());

    //----------------------------
    // Reference panel Binding
    //----------------------------
    workspace.setParent(parentProperty.getValue().getWorkspace());

    //-----> Master (possible null value)
    if (masterProperty.getValue() != null) {
      workspace.setMaster(masterProperty.getValue().getWorkspace());
    }
    else {
      workspace.setMaster(null);
    }
    //-----> Project (possible null value)
    if (projectProperty.getValue() != null) {
      workspace.setProject(projectProperty.getValue().getProject());
    }
    else {
      workspace.setProject(null);
    }
    workspace.setCustomer(customerProperty.getValue());

    //----------------------------
    // configuration panel Binding
    //----------------------------
    workspace.setOrder(wkspPositionProperty.getValue());
    workspace.setEnabled(enableProperty.getValue());
    workspace.setChildrenWrkspCreationEnabled(childrenAllowedProperty.getValue());
    workspace.setBaseFolder(baseFolderProperty.getValue());
    workspace.setOwner(ownerProperty.getValue().getUserInfo().getLogin());
    workspace.setPartage(partageProperty.getValue().getPartage());

    //----------------------------
    // style Binding
    //----------------------------
    workspace.setBold(boldProperty.getValue());
    workspace.setItalic(italicProperty.getValue());
    workspace.setStrike(strikeProperty.getValue());
    workspace.setUnderline(underlineProperty.getValue());
    workspace.setColor(colorProperty.getValue());
    workspace.setBgColor(bgColorProperty.getValue());
    workspace.setIcon(iconProperty.getValue());

    //----------------------------
    // horodate Binding
    //----------------------------
    workspace.setCreatedBy(createdByProperty.getValue());
    workspace.setLastModifiedBy(lastModifiedByProperty.getValue());

    workspace.setCreatedDate(createdDateProperty.getValue());
    workspace.setLastModifiedDate(lastModifiedDateProperty.getValue());

    return workspace;
  }

  /**
   * Map workspace to model.
   *
   * @return the workspace
   */
  public void mapWorkspaceToModel(final Workspace workspace) {
    //----------------------------
    // IdentityCard panel Binding
    //----------------------------
    idProperty.setValue(workspace.getId());
    nameProperty.setValue(workspace.getName());
    categoryProperty.setValue(workspace.getCategory());
    tagsProperty.setValue(workspace.getTags());
    view().getPnlIdentityCardController().getHtmlEdtDescription().setHtmlText(workspace.getDescription()); // Because binding not worked for HTML Editor

    //----------------------------
    // Reference panel Binding
    //----------------------------
    if (workspace.getParent() != null) {
      parentProperty.setValue(new CbbWorkspaceItemVo(workspace.getParent()));
    }
    else {
      parentProperty.setValue(null);
    }

    if (workspace.getMaster() != null) {
      masterProperty.setValue(new CbbWorkspaceItemVo(workspace.getMaster()));
    }
    else {
      masterProperty.setValue(null);
    }

    if (workspace.getProject() != null) {
      projectProperty.setValue(new CbbProjectItemVo(workspace.getProject()));
    }
    else {
      projectProperty.setValue(null);
    }
    customerProperty.setValue(workspace.getCustomer());

    //----------------------------
    // configuration panel Binding
    //----------------------------
    childrenAllowedProperty.setValue(workspace.isChildrenWrkspCreationEnabled());
    wkspPositionProperty.setValue(workspace.getOrder());
    enableProperty.setValue(workspace.isEnabled());
    baseFolderProperty.setValue(workspace.getBaseFolder());
    ownerProperty.setValue(new CbbOwnerItemVo(new UserInfo(workspace.getOwner())));
    partageProperty.setValue(new CbbPartageItemVo(workspace.getPartage()));

    //----------------------------
    // style Binding
    //----------------------------
    boldProperty.setValue(workspace.isBold());
    italicProperty.setValue(workspace.isItalic());
    strikeProperty.setValue(workspace.isStrike());
    underlineProperty.setValue(workspace.isUnderline());
    colorProperty.setValue(workspace.getColor());
    bgColorProperty.setValue(workspace.getBgColor());
    iconProperty.setValue(workspace.getIcon());

    //----------------------------
    // horodate Binding
    //----------------------------
    createdByProperty.setValue(workspace.getCreatedBy());
    lastModifiedByProperty.setValue(workspace.getLastModifiedBy());

    createdDateProperty.setValue(workspace.getCreatedDate());
    lastModifiedDateProperty.setValue(workspace.getLastModifiedDate());

  }

}
