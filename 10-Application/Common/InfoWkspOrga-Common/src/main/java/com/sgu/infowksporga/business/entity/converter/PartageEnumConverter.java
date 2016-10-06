package com.sgu.infowksporga.business.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;

/**
 * The Class PartageEnumConverter.
 */

@Converter
public class PartageEnumConverter implements AttributeConverter<PartageEnum, String> {

  /** {@inheritDoc} */
  @Override
  public String convertToDatabaseColumn(final PartageEnum value) {
    return value.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public PartageEnum convertToEntityAttribute(final String value) {
    return PartageEnum.getEnumForValue(value);
  }
}
