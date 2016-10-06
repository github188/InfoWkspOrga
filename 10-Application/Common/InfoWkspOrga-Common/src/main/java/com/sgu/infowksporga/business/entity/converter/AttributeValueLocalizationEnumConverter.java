package com.sgu.infowksporga.business.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sgu.infowksporga.business.entity.enumeration.AttributeValueLocalizationEnum;

/**
 * The Class AttributeValueLocalizationEnumConverter.
 */

@Converter
public class AttributeValueLocalizationEnumConverter implements AttributeConverter<AttributeValueLocalizationEnum, String> {

  /** {@inheritDoc} */
  @Override
  public String convertToDatabaseColumn(final AttributeValueLocalizationEnum value) {
    return value.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public AttributeValueLocalizationEnum convertToEntityAttribute(final String value) {
    return AttributeValueLocalizationEnum.getEnumForValue(value);
  }
}
