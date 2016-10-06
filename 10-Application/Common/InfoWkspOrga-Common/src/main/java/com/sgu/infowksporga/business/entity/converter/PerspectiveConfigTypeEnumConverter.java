package com.sgu.infowksporga.business.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sgu.infowksporga.business.entity.enumeration.PerspectiveConfigTypeEnum;

/**
 * The Class PerspectiveConfigTypeEnumConverter.
 */

@Converter
public class PerspectiveConfigTypeEnumConverter implements AttributeConverter<PerspectiveConfigTypeEnum, String> {

  /** {@inheritDoc} */
  @Override
  public String convertToDatabaseColumn(final PerspectiveConfigTypeEnum value) {
    return value.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public PerspectiveConfigTypeEnum convertToEntityAttribute(final String value) {
    return PerspectiveConfigTypeEnum.getEnumForValue(value);
  }
}
