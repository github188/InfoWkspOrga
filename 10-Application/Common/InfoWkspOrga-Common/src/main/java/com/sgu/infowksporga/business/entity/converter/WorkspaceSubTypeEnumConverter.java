package com.sgu.infowksporga.business.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sgu.infowksporga.business.entity.enumeration.WorkspaceSubTypeEnum;

/**
 * The Class WorkspaceTypeEnumConverter.
 */

@Converter
public class WorkspaceSubTypeEnumConverter implements AttributeConverter<WorkspaceSubTypeEnum, String> {

  /** {@inheritDoc} */
  @Override
  public String convertToDatabaseColumn(final WorkspaceSubTypeEnum value) {
    return value.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public WorkspaceSubTypeEnum convertToEntityAttribute(final String value) {
    return WorkspaceSubTypeEnum.getEnumForValue(value);
  }
}
