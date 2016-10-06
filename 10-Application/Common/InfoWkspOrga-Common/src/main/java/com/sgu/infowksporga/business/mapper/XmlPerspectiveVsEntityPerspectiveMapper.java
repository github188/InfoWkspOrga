package com.sgu.infowksporga.business.mapper;

import java.lang.reflect.Field;
import java.util.Date;

import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;
import com.sgu.infowksporga.business.xml.jaxb.perspective.XmlPerspective;
import com.sgu.infowksporga.business.xml.jaxb.perspective.XmlWorkspace;

/**
 * The Class XmlPerspectiveVsEntityPerspectiveMapper.
 */
public class XmlPerspectiveVsEntityPerspectiveMapper {

  /**
   * The Constructor.
   */
  public XmlPerspectiveVsEntityPerspectiveMapper() {

  }

  /**
   * Map to entity perspective.
   *
   * @param xmlPerspective the xml worksapce
   * @param currentUser the current user
   * @param treatmentDate the treatment date
   * @return the entity
   */
  public Perspective mapToEntity(final XmlPerspective xmlPerspective, final String currentUser, final Date treatmentDate) {

    final Perspective entity = new Perspective();

    entity.setId(xmlPerspective.getId());
    entity.setName(xmlPerspective.getName());
    entity.setIcon(xmlPerspective.getIcon());
    entity.setDescription(xmlPerspective.getDescription());

    entity.setOwner(xmlPerspective.getOwner());
    entity.setPartage(PartageEnum.getEnumForValue(xmlPerspective.getPartage()));

    entity.setEnabled(xmlPerspective.isEnabled());
    entity.setOrder(xmlPerspective.getOrder());

    entity.setCreationInfo(currentUser, treatmentDate);

    // Manage User entity ${User}
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
        System.out.println("entity.set" + name + "(xmlWorksapce.is" + name + "());");
      }
      else {
        System.out.println("entity.set" + name + "(xmlWorksapce.get" + name + "());");
      }

    }
  }

}
