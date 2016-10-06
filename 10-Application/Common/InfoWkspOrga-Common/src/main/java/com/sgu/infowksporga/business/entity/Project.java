package com.sgu.infowksporga.business.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.sgu.core.framework.dao.jpa.entity.AbstractDescribedAuditedEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the project database table.
 */
@Entity
@Table(name = "project")
@Getter
@Setter
public class Project extends AbstractDescribedAuditedEntity<Integer> {
  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "MySqlIdentifierGenerator", strategy = "com.sgu.core.framework.dao.mysql.MySqlIdentifierGenerator",
  parameters = { @Parameter(name = "tableName", value = "project") })
  @GeneratedValue(generator = "MySqlIdentifierGenerator")
  @Access(AccessType.PROPERTY)
  private Integer id;

  @Column(nullable = false)
  private byte enable;

  @Column(nullable = false, length = 45)
  private String groupe;

  //bi-directional many-to-one association to ProjectConfiguration
  @OneToMany(mappedBy = "project")
  private List<ProjectConfiguration> projectConfigurations;

  //bi-directional many-to-one association to Workspace
  @OneToMany(mappedBy = "project")
  private List<Workspace> workspaces;

  /**
   * The Constructor.
   */
  public Project() {
  }

  /**
   * The Constructor.
   *
   * @param id the id
   */
  public Project(Integer id) {
    this.id = id;
  }

  /** {@inheritDoc} */
  @Override
  public Integer getId() {
    return id;
  }

  /** {@inheritDoc} */
  @Override
  public void setId(final Integer id) {
    this.id = id;
  }

  /**
   * Adds the project configuration.
   *
   * @param projectConfiguration the project configuration
   */
  public void addProjectConfiguration(final ProjectConfiguration projectConfiguration) {
    if (projectConfigurations == null) {
      projectConfigurations = new ArrayList<ProjectConfiguration>(5);
    }
    projectConfigurations.add(projectConfiguration);
  }

  /**
   * Removes the project configuration.
   *
   * @param projectConfiguration the project configuration
   */
  public void removeProjectConfiguration(final ProjectConfiguration projectConfiguration) {
    if (getProjectConfigurations() != null) {
      getProjectConfigurations().remove(projectConfiguration);
    }
  }

  /**
   * Adds the workspace.
   *
   * @param workspace the workspace
   * @return the workspace
   */
  public Workspace addWorkspace(final Workspace workspace) {
    if (workspaces == null) {
      workspaces = new ArrayList<Workspace>(5);
    }

    workspaces.add(workspace);
    workspace.setProject(this);

    return workspace;
  }

  /**
   * Removes the workspace.
   *
   * @param workspace the workspace
   * @return the workspace
   */
  public Workspace removeWorkspace(final Workspace workspace) {
    getWorkspaces().remove(workspace);
    workspace.setProject(null);

    return workspace;
  }

}