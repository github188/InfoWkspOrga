package com.sgu.infowksporga.business.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sgu.infowksporga.business.entity.enumeration.WorkspaceTypeEnum;

/**
 * The Class WorkspaceTypeEnumConverter.
 */

@Converter
public class WorkspaceTypeEnumConverter implements AttributeConverter<WorkspaceTypeEnum, String> {

  /** {@inheritDoc} */
  @Override
  public String convertToDatabaseColumn(final WorkspaceTypeEnum value) {
    return value.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public WorkspaceTypeEnum convertToEntityAttribute(final String value) {
    return WorkspaceTypeEnum.getEnumForValue(value);
  }
}
