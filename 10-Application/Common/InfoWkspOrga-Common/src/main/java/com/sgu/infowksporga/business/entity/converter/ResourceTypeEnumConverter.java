package com.sgu.infowksporga.business.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sgu.infowksporga.business.entity.enumeration.ResourceTypeEnum;

/**
 * The Class ResourceTypeEnumConverter.
 */

@Converter
public class ResourceTypeEnumConverter implements AttributeConverter<ResourceTypeEnum, String> {

  /** {@inheritDoc} */
  @Override
  public String convertToDatabaseColumn(final ResourceTypeEnum value) {
    return value.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public ResourceTypeEnum convertToEntityAttribute(final String value) {
    return ResourceTypeEnum.getEnumForValue(value);
  }
}
