package com.sgu.infowksporga.business.dao.impl;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
  public Workspace findWorkspaceWithViewsAndAttr(String workspaceId) {

    Query query = buildGraphRequest(Workspace.FIND_WORKSPACE_WITH_VIEWS_AND_ATTR, "javax.persistence.loadgraph",
                                    Workspace.GRAPH_WORKSPACE_WITH_VIEWS_AND_ATTR);
    query.setParameter("workspaceId", workspaceId);

    Workspace workspaceResult = (Workspace) query.getSingleResult();

    return workspaceResult;
  }

  /**
   * List workspaces.
   *
   * @param hint the hint
   * @param graphName the graph name
   * @return the list< workspace>
   */
  private Query buildGraphRequest(String query, String hint, String graphName) {
    return repository.getEntityManager().createNamedQuery(query).setHint(hint, repository.getEntityManager().getEntityGraph(graphName));
  }

}
