package com.sgu.infowksporga.business.mapper;

import java.lang.reflect.Field;
import java.util.Date;

import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.Project;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;
import com.sgu.infowksporga.business.xml.jaxb.perspective.XmlWorkspace;

/**
 * The Class XmlWorkspaceVsEntityWorkspace.
 */
public class XmlWorkspaceVsEntityWorkspaceMapper {

  /**
   * The Constructor.
   */
  public XmlWorkspaceVsEntityWorkspaceMapper() {

  }

  /**
   * Map to entity workspace.
   *
   * @param xmlWorkspace the xml worksapce
   * @return the workspace
   */
  public Workspace mapToEntity(final XmlWorkspace xmlWorkspace, final String currentUser, final Date treatmentDate) {

    final Workspace entity = new Workspace();

    entity.setId(xmlWorkspace.getId());

    Workspace parent = null;
    if (xmlWorkspace.getParentId() != null) {
      parent = new Workspace();
      parent.setId(xmlWorkspace.getParentId());
    }
    entity.setParent(parent);

    Workspace master = null;
    if (xmlWorkspace.getMasterId() != null) {
      master = new Workspace();
      master.setId(xmlWorkspace.getMasterId());
    }
    entity.setMaster(master);

    entity.setChildrenWrkspCreationEnabled(xmlWorkspace.isChildrenWrkspCreationEnabled());

    if (xmlWorkspace.getProjectId() != null) {
      final Project project = new Project();
      project.setId(xmlWorkspace.getProjectId());
      entity.setProject(project);
    }
    entity.setBaseFolder(xmlWorkspace.getBaseFolder());
    entity.setCustomer(xmlWorkspace.getCustomer());

    entity.setName(xmlWorkspace.getName());
    entity.setDescription(xmlWorkspace.getDescription());
    entity.setCategory(xmlWorkspace.getCategory());
    if (UtilString.isBlank(entity.getCategory())) {
      entity.setCategory("Default");
    }
    entity.setTags(xmlWorkspace.getTags());
    entity.setPartage(PartageEnum.getEnumForValue(xmlWorkspace.getPartage()));

    if (xmlWorkspace.getColor() != null) {
      entity.setColor(xmlWorkspace.getColor());
    }
    else {
      entity.setColor("rgb(0,0,0)");
    }
    entity.setBold(xmlWorkspace.isBold());
    entity.setStrike(xmlWorkspace.isStrike());
    entity.setItalic(xmlWorkspace.isItalic());
    entity.setUnderline(xmlWorkspace.isUnderline());
    entity.setIcon(xmlWorkspace.getIcon());

    entity.setEnabled(xmlWorkspace.isEnabled());
    entity.setOwner(xmlWorkspace.getOwner());

    entity.setCreationInfo(currentUser, treatmentDate);

    // Manage User workspace identifier ${User}
    if ("${User}".equals(entity.getId())) {
      entity.setId(currentUser);
    }
    if ("${User}".equals(entity.getName())) {
      entity.setName(currentUser);
    }

    if ("${User}".equals(entity.getOwner()) || entity.getOwner() == null) {
      entity.setOwner(currentUser);
    }

    return entity;
  }

  /**
   * The main method.
   *
   * @param strings the strings
   */
  public static void main(final String... strings) {
    final Field[] fields = XmlWorkspace.class.getDeclaredFields();

    for (final Field field : fields) {
      String name = field.getName();
      name = UtilString.capitalize(name);
      final boolean isBoolean = field.getType().toString().contains("Boolean") ? true : false;

      if (isBoolean) {
        System.out.println("workspace.set" + name + "(xmlWorksapce.is" + name + "());");
      }
      else {
        System.out.println("workspace.set" + name + "(xmlWorksapce.get" + name + "());");
      }

    }
  }

}
