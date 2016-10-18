package com.sgu.infowksporga.business.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sgu.infowksporga.business.entity.enumeration.DockPosEnum;

/**
 * The Class DockPosEnumConverter.
 */

@Converter
public class DockPosEnumConverter implements AttributeConverter<DockPosEnum, String> {

  /** {@inheritDoc} */
  @Override
  public String convertToDatabaseColumn(final DockPosEnum value) {
    return value.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public DockPosEnum convertToEntityAttribute(final String value) {
    return DockPosEnum.getEnumForValue(value);
  }
}
