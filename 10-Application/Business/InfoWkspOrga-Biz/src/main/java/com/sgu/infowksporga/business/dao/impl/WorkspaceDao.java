package com.sgu.infowksporga.business.dao.impl;

import java.util.Date;

import javax.persistence.Query;

import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.infowksporga.business.dao.api.IWorkspaceDao;
import com.sgu.infowksporga.business.dao.repository.WorkspaceRepository;
import com.sgu.infowksporga.business.entity.Workspace;

/**
 * The Class WorkspaceDao.
 */
@Repository
public class WorkspaceDao extends AbstractInfoWkspOrgaDao implements IWorkspaceDao {

  @Autowired
  private WorkspaceRepository repository;

  /**
   * The Constructor.
   */
  public WorkspaceDao() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public Workspace findWorkspaceWithViewsAndAttr(final String workspaceId) {

    final Query query = buildGraphRequest(Workspace.FIND_WORKSPACE_WITH_VIEWS_AND_ATTR, "javax.persistence.loadgraph", Workspace.GRAPH_WORKSPACE_WITH_VIEWS_AND_ATTR);
    query.setParameter("workspaceId", workspaceId);

    final Workspace workspaceResult = (Workspace) query.getSingleResult();

    return workspaceResult;
  }

  /**
   * List workspaces.
   *
   * @param hint the hint
   * @param graphName the graph name
   * @return the list< workspace>
   */
  private Query buildGraphRequest(final String query, final String hint, final String graphName) {
    return repository.getEntityManager().createNamedQuery(query).setHint(hint, repository.getEntityManager().getEntityGraph(graphName));
  }

  /** {@inheritDoc} */
  @Override
  public void updateWorkspaceLayout(final String workspaceId, final String layout, final Double width, final Double height, final String userLogin, final Date saveDate) {
    final HibernateEntityManager em = (HibernateEntityManager) repository.getEntityManager();
    final org.hibernate.Query query = em.getSession().getNamedQuery(Workspace.UPDATE_LAYOUT);
    query.setParameter("workspaceId", workspaceId);
    query.setParameter("layout", layout.getBytes());
    query.setParameter("width", width);
    query.setParameter("height", height);
    query.setParameter("userLogin", userLogin);
    query.setParameter("saveDate", saveDate);

    final int result = query.executeUpdate();
    if (result != 1) {
      throw new TechnicalException("This layout update should modify exactly 1 row it impact effectively : " + result + " row.");
    }

  }

  /** {@inheritDoc} */
  @Override
  public void updateWorkspaceProperties(final Workspace workspace, final String userLogin, final Date saveDate) {
    final HibernateEntityManager em = (HibernateEntityManager) repository.getEntityManager();
    final org.hibernate.Query query = em.getSession().getNamedQuery(Workspace.UPDATE_PROPERTIES);

    query.setParameter("master_id", workspace.getMaster() != null ? workspace.getMaster().getId() : null);
    query.setParameter("parent_id", workspace.getParent().getId() != null ? workspace.getParent().getId() : null);
    query.setParameter("project_id", workspace.getProject() != null ? workspace.getProject().getId() : null);

    query.setParameter("children_wrksp_creation_enabled", workspace.isChildrenWrkspCreationEnabled());
    query.setParameter("base_folder", workspace.getBaseFolder());
    query.setParameter("customer", workspace.getCustomer());
    query.setParameter("name", workspace.getName());
    query.setParameter("description", workspace.getDescription());
    query.setParameter("category", workspace.getCategory());
    query.setParameter("tags", workspace.getTags());
    query.setParameter("bg_color", workspace.getBgColor());
    query.setParameter("color", workspace.getColor());
    query.setParameter("bold", workspace.isBold());
    query.setParameter("strike", workspace.isStrike());
    query.setParameter("italic", workspace.isItalic());
    query.setParameter("underline", workspace.isUnderline());
    query.setParameter("icon", workspace.getIcon());
    query.setParameter("enabled", workspace.isEnabled());
    query.setParameter("owner", workspace.getOwner());
    query.setParameter("partage", workspace.getPartage().getValue());

    query.setParameter("last_modified_by", userLogin);
    query.setParameter("last_modified_date", saveDate);

    query.setParameter("workspaceId", workspace.getId());

    final int result = query.executeUpdate();
    if (result != 1) {
      throw new TechnicalException("This layout update should modify exactly 1 row it impact effectively : " + result + " row.");
    }

  }

}
