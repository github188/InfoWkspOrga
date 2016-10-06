package com.sgu.infowksporga.business.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sgu.infowksporga.business.entity.enumeration.AccessRightEnum;

/**
 * The Class AccessRightEnumConverter.
 */

@Converter
public class AccessRightEnumConverter implements AttributeConverter<AccessRightEnum, String> {

  /** {@inheritDoc} */
  @Override
  public String convertToDatabaseColumn(final AccessRightEnum value) {
    return value.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public AccessRightEnum convertToEntityAttribute(final String value) {
    return AccessRightEnum.getEnumForValue(value);
  }
}
