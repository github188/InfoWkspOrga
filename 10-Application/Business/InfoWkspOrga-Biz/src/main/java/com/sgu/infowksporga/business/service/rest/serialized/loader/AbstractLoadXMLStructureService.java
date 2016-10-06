package com.sgu.infowksporga.business.service.rest.serialized.loader;

import java.net.URL;

import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.util.UtilIO;
import com.sgu.infowksporga.business.service.rest.serialized.api.AbstractSerializedService;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class AbstractLoadXMLStructureService.
 * {@link Resource server-localization.properties}
 */
@Slf4j
public abstract class AbstractLoadXMLStructureService extends AbstractSerializedService {

  /**
   * Read xml configuration.
   *
   * @param in the in
   * @return the string
   */
  protected String readXmlConfiguration(final String xmlFileUrl) {
    String xmlContent;
    try {
      // Read File with the given URL
      xmlContent = UtilIO.readFile(new URL(xmlFileUrl));

    } catch (final Exception e) {
      log.error(e.getMessage(), e);
      throw new TechnicalException(e);
    }

    return xmlContent;
  }

}
