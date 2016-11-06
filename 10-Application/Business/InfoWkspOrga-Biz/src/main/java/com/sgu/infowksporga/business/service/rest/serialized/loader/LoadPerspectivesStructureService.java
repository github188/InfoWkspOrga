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
import com.sgu.core.framework.util.UtilCollection;
import com.sgu.core.framework.util.UtilIO;
import com.sgu.core.framework.util.UtilString;
import com.sgu.core.framework.util.UtilXml;
import com.sgu.infowksporga.business.dao.repository.PerspectiveRepository;
import com.sgu.infowksporga.business.dao.repository.PerspectiveWorkspacesRepository;
import com.sgu.infowksporga.business.dao.repository.WorkspaceRepository;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.business.entity.PerspectiveWorkspaces;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.mapper.XmlPerspectiveVsEntityPerspectiveMapper;
import com.sgu.infowksporga.business.mapper.XmlWorkspaceVsEntityWorkspaceMapper;
import com.sgu.infowksporga.business.pivot.perspective.LoadPerspectivesStructureIn;
import com.sgu.infowksporga.business.pivot.perspective.LoadPerspectivesStructureOut;
import com.sgu.infowksporga.business.service.rest.serialized.api.ILoadPerspectivesStructureService;
import com.sgu.infowksporga.business.xml.jaxb.perspective.XmlPerspective;
import com.sgu.infowksporga.business.xml.jaxb.perspective.XmlWorkspace;
import com.sgu.infowksporga.util.OrderManager;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class LoadPerspectiveWorkspacesService.
 * {@link Resource server-localization.properties}
 */
@Service
@Transactional
@Slf4j
public class LoadPerspectivesStructureService extends AbstractLoadXMLStructureService implements ILoadPerspectivesStructureService {

  @Autowired
  private WorkspaceRepository repositoryWorkspace;

  @Autowired
  private PerspectiveRepository repositoryPerspective;

  @Autowired
  private PerspectiveWorkspacesRepository repositoryPerspectiveWorkspaces;

  /** The Constant mapper. */
  private static final XmlWorkspaceVsEntityWorkspaceMapper workspaceMapper = new XmlWorkspaceVsEntityWorkspaceMapper();
  private static final XmlPerspectiveVsEntityPerspectiveMapper perspectiveMapper = new XmlPerspectiveVsEntityPerspectiveMapper();

  //---------------------------------------------------------------------------------------------------------------------------------------
  @GenerateInterface(baseSource = AnnotationConfig.INTERFACE_SERVICE_TARGET_SOURCE_FOLDER, interfacePackage = "com.sgu.infowksporga.business.service.rest.serialized.api",
  interfaceName = "ILoadPerspectivesStructureService", pivotIn = "com.sgu.infowksporga.business.pivot.perspective.LoadPerspectivesStructureIn",
  pivotOut = "com.sgu.infowksporga.business.pivot.perspective.LoadPerspectivesStructureOut")

  @Rest(requestControllerUri = "/perspective", requestServiceUri = "/load/xml/structure", controllerPackage = "com.sgu.infowksporga.web.rest.controller",
  controllerName = "LoadPerspectivesStructureController", method = "RequestMethod.POST",
  produces = "{ GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE }",
  controllerBaseSource = AnnotationConfig.REST_CONTROLLER_TARGET_SOURCE_FOLDER, restServiceMappingTargetClass = AnnotationConfig.REST_REQUEST_MAPPING_TARGET)

  //---------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public LoadPerspectivesStructureOut executeService(final LoadPerspectivesStructureIn in) {
    final LoadPerspectivesStructureOut out = new LoadPerspectivesStructureOut();

    String xmlFilesBaseUrl = in.getPerspectivesConfig().get("perspectives.init.xml.ur.base");

    // By default Decode URL (if it's a local one)
    xmlFilesBaseUrl = UtilString.replaceString(xmlFilesBaseUrl, "${prez.host.port}", in.getPerspectivesConfig().get("prez.host.port"));
    xmlFilesBaseUrl = UtilString.replaceString(xmlFilesBaseUrl, "${biz.host.port}", in.getPerspectivesConfig().get("biz.host.port"));
    xmlFilesBaseUrl = UtilString.replaceString(xmlFilesBaseUrl, "${prez.app.name}", in.getPerspectivesConfig().get("prez.app.name"));
    xmlFilesBaseUrl = UtilString.replaceString(xmlFilesBaseUrl, "${biz.app.name}", in.getPerspectivesConfig().get("biz.app.name"));

    //
    final String xmlFilesName = in.getPerspectivesConfig().get(in.getFilesToLoadKey());
    final String[] xmlFilesNameTab = xmlFilesName.split(";");

    for (final String element : xmlFilesNameTab) {
      // file to analyze
      final String xmlPerspectiveFileUrl = xmlFilesBaseUrl + element;

      // manage Create XML Structure if it not exists in db
      manageDbPerspectiveStructureFromXML(xmlPerspectiveFileUrl, in, out);
    }

    return out;

  }

  /**
   * Manage db persistence structure from xml.
   * XML Files is used only at initialization time
   * Other caracteristics are managed by Perspective screen dialog properties
   *
   * @param in the in
   * @param out the out
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-biz",
  properties = { // label create
                @I18nProperty(key = "load.workspaces.tree.model.message.error.xml.content.bad",
                value = "Le contenu du fichier XML '{0}' n''est pas conforme.Veuillez le corriger."), // Force /n 
  })
  private void manageDbPerspectiveStructureFromXML(final String xmlPerspectiveFileUrl, final LoadPerspectivesStructureIn in, final LoadPerspectivesStructureOut out) {
    // Read Xml Configuration from URL or directly in String
    final String strConfig = readXmlConfiguration(xmlPerspectiveFileUrl);

    // Convert String config to XML
    XmlPerspective xmlPerspective = null;
    try {
      xmlPerspective = (XmlPerspective) UtilXml.jaxbXmlToObject(UtilIO.toInputStream(strConfig, "UTF-8"), XmlPerspective.class);

    } catch (final Exception e) {
      log.error(e.getMessage(), e);
      out.setReturnCode(ReturnCode.BUSINESS_ERROR);
      out.addError(new Message("load.workspaces.tree.model.message.error.xml.content.bad", xmlPerspectiveFileUrl));
    }

    if (out.getReturnCode().equals(ReturnCode.OK)) {
      // Create perspective if it doesn't already exists
      Perspective dbPerspective = repositoryPerspective.findOne(xmlPerspective.getId());
      if (dbPerspective == null) {
        dbPerspective = perspectiveMapper.mapToEntity(xmlPerspective, in.getUserLogin(), in.getTreatmentDate());
        repositoryPerspective.save(dbPerspective);
      }

      // Order is managed by Workspace XML organization order if it is not specified by tag 'order'
      final OrderManager startWorkspaceOrder = new OrderManager(0);
      // Create All Workspaces default from xml structure
      createDatabaseStructureFromXmlRecursively(xmlPerspective.getId(), xmlPerspective.getXmlWorkspace(), null, in.getUserLogin(), startWorkspaceOrder,
                                                in.getTreatmentDate());
    }
  }

  /**
   * /!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\
   * Creates the database structure from xml recursively.
   * /!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\
   *
   * @param xmlWorkspace the xml workspace
   * @param currentUser the current user
   * @return the workspace
   */
  private void createDatabaseStructureFromXmlRecursively(final Integer perspectiveId, final XmlWorkspace xmlWorkspace, final Workspace dbWorkspaceParent,
  final String currentUser, final OrderManager orderManager, final Date treatmentDate) {

    String xmlWorkspaceId = xmlWorkspace.getId();
    if ("${User}".equals(xmlWorkspaceId)) {
      // replace by the current user
      xmlWorkspaceId = currentUser;
      xmlWorkspace.setId(xmlWorkspaceId);
    }

    Workspace dbWorkspace = repositoryWorkspace.findOne(xmlWorkspaceId);
    // it's a CREATION
    if (dbWorkspace == null) {
      dbWorkspace = workspaceMapper.mapToEntity(xmlWorkspace, currentUser, treatmentDate);

      Workspace parent = null;
      if (dbWorkspaceParent != null) {
        parent = new Workspace();
        parent.setId(dbWorkspaceParent.getId());
      }
      dbWorkspace.setParent(parent);

      // Création en base de données
      dbWorkspace = repositoryWorkspace.save(dbWorkspace);

    }

    ManageLinkBetweenPerspectiveAndWorkspace(perspectiveId, dbWorkspace, dbWorkspaceParent, currentUser, treatmentDate, orderManager);

    // We treat the children node if any
    final List<XmlWorkspace> xmlWorkspaceChildren = xmlWorkspace.getXmlWorkspace();
    for (final XmlWorkspace xmlWorkspaceChild : xmlWorkspaceChildren) {
      createDatabaseStructureFromXmlRecursively(perspectiveId, xmlWorkspaceChild, dbWorkspace, currentUser, orderManager, treatmentDate);
    }

  }

  /**
   * Manage link between perspective and workspace.
   *
   * @param perspectiveId the perspective id
   * @param xmlWorkspace the xml workspace
   * @param xmlWorkspaceParent the xml workspace parent
   * @param currentUser the current user
   * @param orderManager the order manager
   * @param treatmentDate the treatment date
   * @param dbWorkspace the db workspace
   */
  private void ManageLinkBetweenPerspectiveAndWorkspace(final Integer perspectiveId, final Workspace dbWorkspace, final Workspace dbWorkspaceParent,
  final String currentUser, final Date treatmentDate, final OrderManager orderManager) {

    // Check if Link already exists
    final List<PerspectiveWorkspaces> result = repositoryPerspectiveWorkspaces.findPerspectiveWorkspaceLink(perspectiveId, dbWorkspace.getId());

    if (UtilCollection.isEmpty(result)) {
      // ---------------------------------------------------
      // Create the link between Perspective and Workspace
      // ---------------------------------------------------
      PerspectiveWorkspaces ppcWkspLink = new PerspectiveWorkspaces();
      ppcWkspLink.setPerspectiveId(perspectiveId);
      ppcWkspLink.setWorkspaceId(dbWorkspace.getId());
      ppcWkspLink.setWorkspaceOrder(orderManager.getNextOrder());

      ppcWkspLink.setCreationInfo(currentUser, treatmentDate);
      ppcWkspLink = repositoryPerspectiveWorkspaces.save(ppcWkspLink);

    }
  }

}
