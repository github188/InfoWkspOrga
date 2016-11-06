package com.sgu.infowksporga.business.mapper;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.collection.spi.PersistentCollection;

import com.sgu.core.framework.util.UtilHibernate;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.Project;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.xml.jaxb.perspective.XmlWorkspace;

/**
 * The Class EntityWorkspaceCloner.
 */
public class EntityWorkspaceCloner extends AbstractEntityCloner<Workspace> {

  /** The Constant instance. */
  public static final EntityWorkspaceCloner instance = new EntityWorkspaceCloner();

  /**
   * The Constructor.
   */
  private EntityWorkspaceCloner() {
    super();
  }

  /**
   * Clone without proxy.
   *
   * @param this the this
   * @return the perspective
   */
  @Override
  public com.sgu.infowksporga.business.entity.Workspace cloneWithoutProxy(final Workspace source) {

    final Workspace clone = new Workspace();

    // Clone des données communes
    super.cloneCommonFields(source, clone);

    // Manage specific attributes of this current class
    clone.setId(source.getId());
    clone.setChildrenWrkspCreationEnabled(source.isChildrenWrkspCreationEnabled());
    clone.setBaseFolder(source.getBaseFolder());
    clone.setCustomer(source.getCustomer());
    clone.setCategory(source.getCategory());
    clone.setTags(source.getTags());
    clone.setColor(source.getColor());
    clone.setBgColor(source.getBgColor());
    clone.setBold(source.isBold());
    clone.setStrike(source.isStrike());
    clone.setItalic(source.isItalic());
    clone.setUnderline(source.isUnderline());
    clone.setIcon(source.getIcon());
    clone.setEnabled(source.isEnabled());
    //clone.setOrder(source.getOrder());
    clone.setLayout(source.getLayout());
    clone.setOwner(source.getOwner());
    clone.setPartage(source.getPartage());
    clone.setHeight(source.getHeight());
    clone.setWidth(source.getWidth());

    // Manage classes potentially proxified by hibernate
    if (source.getParent() != null) {
      clone.setParent(new Workspace(source.getParent().getId()));
    }

    if (source.getMaster() != null) {
      clone.setMaster(new Workspace(source.getMaster().getId()));
    }

    if (source.getViews() != null & source.getViews() instanceof PersistentCollection) {
      clone.setViews((List<View>) UtilHibernate.unproxyPersistentCollection((PersistentCollection) source.getViews()));
    }

    if (source.getProject() != null) {
      clone.setProject(new Project(source.getProject().getId()));
    }

    return clone;
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
        System.out.println("entity.set" + name + "(xmlWorksapce.is" + name + "());");
      }
      else {
        System.out.println("entity.set" + name + "(xmlWorksapce.get" + name + "());");
      }

    }
  }

}
