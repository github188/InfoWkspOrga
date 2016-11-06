package com.sgu.infowksporga.business.service.rest.serialized;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.interfaces.GenerateInterface;
import com.sgu.apt.annotation.rest.Rest;
import com.sgu.core.framework.pivot.UserInfo;
import com.sgu.infowksporga.business.dao.api.IWorkspaceDao;
import com.sgu.infowksporga.business.dao.repository.PerspectiveWorkspacesRepository;
import com.sgu.infowksporga.business.dao.repository.ViewAttributeRepository;
import com.sgu.infowksporga.business.dao.repository.ViewRepository;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceLayoutIn;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceLayoutOut;
import com.sgu.infowksporga.business.service.rest.serialized.api.AbstractSerializedService;
import com.sgu.infowksporga.business.service.rest.serialized.api.ISaveWorkspaceLayoutService;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class Save Workspace And Views Configuration.
 */
@Service
@Transactional
@Slf4j
public class SaveWorkspaceLayoutService extends AbstractSerializedService implements ISaveWorkspaceLayoutService {

  @Autowired
  private IWorkspaceDao workspaceDao;

  @Autowired
  private PerspectiveWorkspacesRepository repositoryPerspectiveWorkspaces;

  @Autowired
  private ViewRepository repositoryView;

  @Autowired
  private ViewAttributeRepository repositoryViewAttribute;

  //---------------------------------------------------------------------------------------------------------------------------------------
  @GenerateInterface(baseSource = AnnotationConfig.INTERFACE_SERVICE_TARGET_SOURCE_FOLDER, interfacePackage = "com.sgu.infowksporga.business.service.rest.serialized.api",
  interfaceName = "ISaveWorkspaceLayoutService", pivotIn = "com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceLayoutIn",
  pivotOut = "com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceLayoutOut")

  @Rest(requestControllerUri = "/workspace", requestServiceUri = "/save/layout", controllerPackage = "com.sgu.infowksporga.web.rest.controller",
  controllerName = "SaveWorkspaceLayoutController", method = "RequestMethod.POST",
  produces = "{ GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE }",
  controllerBaseSource = AnnotationConfig.REST_CONTROLLER_TARGET_SOURCE_FOLDER, restServiceMappingTargetClass = AnnotationConfig.REST_REQUEST_MAPPING_TARGET)

  //---------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public SaveWorkspaceLayoutOut executeService(final SaveWorkspaceLayoutIn in) {
    // Init du workspace de sortie
    final SaveWorkspaceLayoutOut out = new SaveWorkspaceLayoutOut();

    //-------------------------------------------------------------------------------------------
    // Update the layout
    //--------------------------------------------------------------------------------------------
    updateWorkspaceLayout(in.getWorkspaceId(), in.getLayout(), in.getWidth(), in.getHeight(), in.getUserInfo(), in.getTreatmentDate());

    //-------------------------------------------------------------------------------------------
    // Delete all views (with attributes) not in the workspace views list (them deleted by user)
    //--------------------------------------------------------------------------------------------
    removeAllViewsNotReferencedByWorkspace(in.getWorkspaceId(), in.getViews());

    //--------------------------------------------------------------------------------------------
    // Views and Attributes management
    //--------------------------------------------------------------------------------------------
    manageViewsAndAttributes(in.getWorkspaceId(), in.getViews(), in.getUserInfo(), in.getTreatmentDate());

    // Set the view list with all new ids
    out.setViews(in.getViews());

    return out;
  }

  /**
   * Update workspace layout.
   *
   * @param workspaceId the workspace id
   * @param layout the layout
   * @param userInfo the user info
   * @param saveDate the save date
   */
  private void updateWorkspaceLayout(final String workspaceId, final String layout, final Double width, final Double height, final UserInfo userInfo,
  final Date saveDate) {
    workspaceDao.updateWorkspaceLayout(workspaceId, layout, width, height, userInfo.getLogin(), saveDate);
  }

  /**
   * Manage views and attributes.
   *
   * @param workspaceId the workspace id
   * @param views the views
   * @param userInfo the user info
   * @param saveDate the save date
   */
  private void manageViewsAndAttributes(final String workspaceId, final List<View> views, final UserInfo userInfo, final Date saveDate) {
    for (View view : views) {

      if (view.getId() == null) {
        log.debug("View Creation");
        //it's a view creation
        view.setWorkspaceId(workspaceId);
        view.setCreationInfo(userInfo.getLogin(), saveDate);
        view = repositoryView.save(view);
      }
      else {// it's an update
        log.debug("View Update");
        view.setUpdateInfo(userInfo.getLogin(), saveDate);
        repositoryView.save(view);
      }

      /*---------------------------------------------*/
      /* View ATTRIBUTES management */
      /*---------------------------------------------*/
      final Set<ViewAttribute> viewAttributes = view.getAttributes();
      for (ViewAttribute viewAttribute : viewAttributes) {
        if (viewAttribute.getId() == null) {
          log.debug("viewAttribute Creation");
          //it's a view attribute creation
          viewAttribute.setViewId(view.getId());
          viewAttribute.setCreationInfo(userInfo.getLogin(), saveDate);
          viewAttribute = repositoryViewAttribute.save(viewAttribute);
        }
        else {// it's an update
          log.debug("View Update");
          viewAttribute.setUpdateInfo(userInfo.getLogin(), saveDate);
          repositoryViewAttribute.save(viewAttribute);
        }
      }

    }
  }

  /**
   * Removes the all views not referenced by workspace.
   *
   * @param workspaceId the workspace id
   * @param views the views
   */
  private void removeAllViewsNotReferencedByWorkspace(final String workspaceId, final List<View> views) {

    if (views.size() > 0) { // Check this in certain case after creation workspace don't have view yet
      final List<Integer> viewsIdToKeep = new ArrayList<Integer>();

      for (final View view : views) {
        if (view.getId() != null) {
          viewsIdToKeep.add(view.getId());
        }
      }

      List<Integer> viewsIdToDelete = null;
      if (viewsIdToKeep.size() > 0) {
        viewsIdToDelete = repositoryView.findViewsNotInGivenListForWorkspace(viewsIdToKeep, workspaceId);
      }
      else { // On peut supprimer toutes les vues
        viewsIdToDelete = repositoryView.findWorkspaceViews(workspaceId);
      }

      if (viewsIdToDelete != null && viewsIdToDelete.size() > 0) {
        // First remove all attributes for all views to evict reference problem
        repositoryViewAttribute.removeViewsAttributes(viewsIdToDelete);
        // Now remove all not needed Views
        repositoryView.removeViews(viewsIdToDelete);
      }
    }
  }

}
