package com.sgu.infowksporga.business.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sgu.infowksporga.business.entity.enumeration.PreferenceLevelEnum;

/**
 * The Class AccessRightEnumConverter.
 */

@Converter
public class PreferenceLevelEnumConverter implements AttributeConverter<PreferenceLevelEnum, String> {

  /** {@inheritDoc} */
  @Override
  public String convertToDatabaseColumn(final PreferenceLevelEnum value) {
    return value.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public PreferenceLevelEnum convertToEntityAttribute(final String value) {
    return PreferenceLevelEnum.getEnumForValue(value);
  }
}
