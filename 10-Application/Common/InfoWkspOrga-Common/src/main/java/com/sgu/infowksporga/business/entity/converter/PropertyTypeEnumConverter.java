package com.sgu.infowksporga.business.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sgu.core.framework.bean.PropertyTypeEnum;

/**
 * The Class PropertyTypeEnumConverter.
 */

@Converter
public class PropertyTypeEnumConverter implements AttributeConverter<PropertyTypeEnum, String> {

  /** {@inheritDoc} */
  @Override
  public String convertToDatabaseColumn(final PropertyTypeEnum value) {
    return value.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public PropertyTypeEnum convertToEntityAttribute(final String value) {
    return PropertyTypeEnum.getEnumForValue(value);
  }
}
