package com.sgu.infowksporga.business.service.rest.serialized.loader;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.apt.annotation.interfaces.GenerateInterface;
import com.sgu.apt.annotation.rest.Rest;
import com.sgu.core.framework.pivot.AbstractOut.ReturnCode;
import com.sgu.core.framework.pivot.Message;
import com.sgu.core.framework.util.UtilIO;
import com.sgu.core.framework.util.UtilString;
import com.sgu.core.framework.util.UtilXml;
import com.sgu.infowksporga.business.dao.repository.PreferenceRepository;
import com.sgu.infowksporga.business.entity.Preference;
import com.sgu.infowksporga.business.mapper.XmlPreferenceVsEntityPreferenceMapper;
import com.sgu.infowksporga.business.pivot.preference.LoadPreferencesStructureIn;
import com.sgu.infowksporga.business.pivot.preference.LoadPreferencesStructureOut;
import com.sgu.infowksporga.business.service.rest.serialized.api.ILoadPreferencesStructureService;
import com.sgu.infowksporga.business.xml.jaxb.preference.XmlPreference;
import com.sgu.infowksporga.util.OrderManager;

import ca.odell.glazedlists.impl.rbp.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class LoadPreferencesPreferencesService.
 * {@link Resource server-localization.properties}
 */
@Service
@Transactional
@Slf4j
public class LoadPreferencesStructureService extends AbstractLoadXMLStructureService implements ILoadPreferencesStructureService {

  /** The repository preferences. */
  @Autowired
  private PreferenceRepository repositoryPreference;

  /** The Constant mapper. */
  private static final XmlPreferenceVsEntityPreferenceMapper preferenceMapper = new XmlPreferenceVsEntityPreferenceMapper();

  //---------------------------------------------------------------------------------------------------------------------------------------
  @GenerateInterface(baseSource = AnnotationConfig.INTERFACE_SERVICE_TARGET_SOURCE_FOLDER, interfacePackage = "com.sgu.infowksporga.business.service.rest.serialized.api",
  interfaceName = "ILoadPreferencesStructureService", pivotIn = "com.sgu.infowksporga.business.pivot.preference.LoadPreferencesStructureIn",
  pivotOut = "com.sgu.infowksporga.business.pivot.preference.LoadPreferencesStructureOut")

  @Rest(requestControllerUri = "/preference", requestServiceUri = "/load/xml/structure", controllerPackage = "com.sgu.infowksporga.web.rest.controller",
  controllerName = "LoadPreferencesStructureController", method = "RequestMethod.POST",
  produces = "{ GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE }",
  controllerBaseSource = AnnotationConfig.REST_CONTROLLER_TARGET_SOURCE_FOLDER, restServiceMappingTargetClass = AnnotationConfig.REST_REQUEST_MAPPING_TARGET)

  //---------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public LoadPreferencesStructureOut executeService(final LoadPreferencesStructureIn in) {
    final LoadPreferencesStructureOut out = new LoadPreferencesStructureOut();

    String xmlFilesBaseUrl = in.getPreferencesConfig().get("preferences.init.xml.ur.base");

    // By default Decode URL (if it's a local one)
    xmlFilesBaseUrl = UtilString.replaceString(xmlFilesBaseUrl, "${prez.host.port}", in.getPreferencesConfig().get("prez.host.port"));
    xmlFilesBaseUrl = UtilString.replaceString(xmlFilesBaseUrl, "${biz.host.port}", in.getPreferencesConfig().get("biz.host.port"));
    xmlFilesBaseUrl = UtilString.replaceString(xmlFilesBaseUrl, "${prez.app.name}", in.getPreferencesConfig().get("prez.app.name"));
    xmlFilesBaseUrl = UtilString.replaceString(xmlFilesBaseUrl, "${biz.app.name}", in.getPreferencesConfig().get("biz.app.name"));

    final String xmlFileName = in.getPreferencesConfig().get(in.getFileToLoadKey());

    // file to analyze
    final String xmlPreferencesFileUrl = xmlFilesBaseUrl + xmlFileName;

    // manage Create XML Structure if it not exists in db
    manageDbPreferencesStructureFromXML(xmlPreferencesFileUrl, in, out);

    return out;

  }

  /**
   * Manage db persistence structure from xml.
   * XML Files is used only at initialization time
   * Other characteristic are managed by Properties screen dialog properties
   *
   * @param in the in
   * @param out the out
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-biz",
  properties = { // label create
                @I18nProperty(key = "load.preferences.message.error.xml.content.bad",
                value = "Le contenu du fichier XML '{0}' n''est pas conforme. Veuillez le corriger."), // Force /n 
  })
  private void manageDbPreferencesStructureFromXML(final String xmlPreferenceFileUrl, final LoadPreferencesStructureIn in, final LoadPreferencesStructureOut out) {
    // Read Xml Configuration from URL or directly in String
    final String strConfig = readXmlConfiguration(xmlPreferenceFileUrl);

    // Convert String config to XML
    XmlPreference xmlPreference = null;
    try {
      xmlPreference = (XmlPreference) UtilXml.jaxbXmlToObject(UtilIO.toInputStream(strConfig, "UTF-8"), XmlPreference.class);

    } catch (final Exception e) {
      log.error(e.getMessage(), e);
      out.setReturnCode(ReturnCode.BUSINESS_ERROR);
      out.addError(new Message("load.preferences.message.error.xml.content.bad", xmlPreferenceFileUrl));
    }

    if (out.getReturnCode().equals(ReturnCode.OK)) {
      // Order is managed by Workspace XML organization order if it is not specified by tag 'order'
      final OrderManager startPreferenceOrder = new OrderManager(0);

      // Create All Preferences default from xml structure
      createDatabaseStructureFromXmlRecursively(xmlPreference, null, in.getUserLogin(), startPreferenceOrder, in.getTreatmentDate());
    }
  }

  /**
   * Creates the database structure from xml recursively.
   *
   * @param xmlPreference the xml preference
   * @param dbPreferenceParent the db preference parent
   * @param currentUser the current user
   * @param orderManager the order manager
   * @param treatmentDate the treatment date
   */
  private void createDatabaseStructureFromXmlRecursively(final XmlPreference xmlPreference, final Preference dbPreferenceParent, final String currentUser,
  final OrderManager orderManager, final Date treatmentDate) {

    final Integer xmlPreferenceId = xmlPreference.getId();

    Preference dbPreference = repositoryPreference.findOne(xmlPreferenceId);

    // it's a CREATION
    if (dbPreference == null) {

      dbPreference = preferenceMapper.mapToEntity(xmlPreference, currentUser, treatmentDate);
      dbPreference.setOrder(orderManager.getNextOrder());

      Preference parent = null;
      if (dbPreferenceParent != null) {
        parent = new Preference();
        parent.setId(dbPreferenceParent.getId());
      }
      dbPreference.setParent(parent);

      dbPreference = repositoryPreference.save(dbPreference);
    }

    // We treat the children node if any
    final List<XmlPreference> xmlPreferenceChildren = xmlPreference.getXmlPreference();
    for (final XmlPreference xmlPreferenceChild : xmlPreferenceChildren) {
      createDatabaseStructureFromXmlRecursively(xmlPreferenceChild, dbPreference, currentUser, orderManager, treatmentDate);
    }

  }

}
