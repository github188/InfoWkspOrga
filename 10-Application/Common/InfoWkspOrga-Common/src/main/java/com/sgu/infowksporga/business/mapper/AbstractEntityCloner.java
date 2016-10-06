package com.sgu.infowksporga.business.mapper;

import java.lang.reflect.Field;

import com.sgu.core.framework.dao.jpa.entity.AbstractDescribedAuditedEntity;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.xml.jaxb.perspective.XmlWorkspace;

/**
 * The Class AbstractEntityCloner.
 */
public abstract class AbstractEntityCloner<T> {

  /**
   * The Constructor.
   */
  public AbstractEntityCloner() {

  }

  public Object cloneCommonFields(AbstractDescribedAuditedEntity source, AbstractDescribedAuditedEntity clone) {
    // Manage common attributes beetween entites
    clone.setName(source.getName());
    clone.setDescription(source.getDescription());

    clone.setCreatedBy(source.getCreatedBy());
    clone.setCreatedDate(source.getCreatedDate());
    clone.setLastModifiedBy(source.getLastModifiedBy());
    clone.setLastModifiedDate(source.getLastModifiedDate());

    return clone;
  }

  /**
   * Clone without proxy.
   *
   * @param source the source
   * @return the perspective
   */
  public abstract T cloneWithoutProxy(T source);

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
