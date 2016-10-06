package com.sgu.infowksporga.business.mapper;

import java.lang.reflect.Field;
import java.util.Date;

import com.sgu.core.framework.bean.PropertyTypeEnum;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.Preference;
import com.sgu.infowksporga.business.entity.enumeration.AttributeValueLocalizationEnum;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;
import com.sgu.infowksporga.business.xml.jaxb.preference.XmlPreference;

/**
 * The Class XmlPreferenceVsEntityPreferenceMapper.
 */
public class XmlPreferenceVsEntityPreferenceMapper {

  /**
   * The Constructor.
   */
  public XmlPreferenceVsEntityPreferenceMapper() {

  }

  public Preference mapToEntity(final XmlPreference xmlPreference, final String currentUser, final Date treatmentDate) {

    final Preference entity = new Preference();

    if (xmlPreference.getId() != null) {
      entity.setId(xmlPreference.getId().intValue());
    }
    entity.setParent(null); //Parent is deducted from the preference dependencies in XML file
    entity.setName(xmlPreference.getName());

    entity.setDescription(xmlPreference.getDescription());
    if (xmlPreference.getCategory() != null) {
      entity.setCategory(xmlPreference.getCategory());
    }
    else {
      entity.setCategory("Default");
    }

    if (xmlPreference.getType() != null) {
      entity.setType(PropertyTypeEnum.getEnumForValue(xmlPreference.getType()));
    }
    entity.setValue(xmlPreference.getValue());
    entity.setConstraints(xmlPreference.getConstraints());

    entity.setUpdatable(xmlPreference.isUpdatable());
    entity.setEnabled(xmlPreference.isEnabled());

    if (xmlPreference.getLocalization() != null) {
      entity.setLocalization(AttributeValueLocalizationEnum.getEnumForValue(xmlPreference.getLocalization()));
    }

    entity.setOwner(xmlPreference.getOwner());
    if (xmlPreference.getPartage() != null) {
      entity.setPartage(PartageEnum.getEnumForValue(xmlPreference.getPartage()));
    }

    entity.setCreationInfo(currentUser, treatmentDate);

    // Manage User entity ${User}
    if ("${User}".equals(entity.getName())) {
      entity.setName(currentUser);
    }

    if ("${User}".equals(entity.getCreatedBy()) || entity.getCreatedBy() == null) {
      entity.setCreatedBy(currentUser);
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
    final Field[] fields = XmlPreference.class.getDeclaredFields();

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
