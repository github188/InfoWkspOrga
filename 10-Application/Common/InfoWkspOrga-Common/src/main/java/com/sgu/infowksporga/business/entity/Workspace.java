package com.sgu.infowksporga.business.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;
import com.sgu.core.framework.dao.jpa.converter.BooleanByteConverter;
import com.sgu.core.framework.dao.jpa.entity.AbstractDescribedAuditedEntity;
import com.sgu.core.framework.i18n.I18nHelperFwk;
import com.sgu.core.framework.util.UtilGen;
import com.sgu.infowksporga.business.entity.converter.PartageEnumConverter;
import com.sgu.infowksporga.business.entity.converter.WorkspaceTypeEnumConverter;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;
import com.sgu.infowksporga.business.entity.enumeration.WorkspaceTypeEnum;
import com.sgu.infowksporga.business.mapper.EntityWorkspaceCloner;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the workspace database table.
 */
@Entity
@Table(name = "workspace")
@Getter
@Setter

//@formatter:off

//-----------------------------------------------------------------------
// Named Queries
//-----------------------------------------------------------------------
@NamedQueries({
@NamedQuery(name = Workspace.FIND_WORKSPACE_WITH_VIEWS_AND_ATTR, query =
" SELECT w  FROM  "  + 
"     Workspace w   "  + 
/*"     JOIN FETCH View v   "  + 
"       ON w.id = v.workspaceId   "  + 
"     JOIN FETCH ViewAttribute a   "  + 
"       ON a.viewId = v.id   "  + */
"  WHERE w.id = :workspaceId   ")

})

//-----------------------------------------------------------------------
// Entity Graphs
//-----------------------------------------------------------------------
/* This defines an Entity Graph with name workspace-Views-ViewAttributes and specifies that the relationship views should be loaded. 
 * Additionally, it also specifies that the relationship views should load the attributes.
 * 
 * Note that we don’t specify the id attribute in the Entity Graph. 
 * This is because primary keys are always fetched regardless of what’s being specified. 
 * This is also true for version attributes.
 */
@NamedEntityGraphs({
  @NamedEntityGraph(
      name = "workspace-Views-ViewAttributes",
      attributeNodes =  @NamedAttributeNode(value = "views", subgraph = "attributes"),
      subgraphs =  @NamedSubgraph(name = "attributes", attributeNodes = @NamedAttributeNode("attributes")))
})
//@formatter:on

//-----------------------------------------------------------------------
// Class definition
//-----------------------------------------------------------------------
public class Workspace extends AbstractDescribedAuditedEntity<String> {
  private static final long serialVersionUID = 1L;

  /** The Constant FIND_WORKSPACE_WITH_VIEWS_AND_ATTR. */
  public static final String FIND_WORKSPACE_WITH_VIEWS_AND_ATTR = "findWorkspaceWithViewsAndAttr";

  /** The Constant WORKSPACE_WITH_VIEWS_AND_ATTR_GRAPH. */
  public static final String GRAPH_WORKSPACE_WITH_VIEWS_AND_ATTR = "workspace-Views-ViewAttributes";

  @Id
  @GenericGenerator(name = "MySqlIdentifierGenerator", strategy = "com.sgu.core.framework.dao.mysql.MySqlIdentifierGenerator",
  parameters = { @Parameter(name = "tableName", value = "workspace"), @Parameter(name = "idType", value = "String") })
  @GeneratedValue(generator = "MySqlIdentifierGenerator")
  @Access(AccessType.PROPERTY)
  private String id;

  /**
   * Indique que le LAYOUT à utiliser est celui de ce workspace
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "master_id", nullable = true)
  private Workspace master;

  /**
   * Le workspace parent de ce workspace (= rattachement brut)
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id", nullable = true)
  private Workspace parent;

  //bi-directional many-to-one association to Project
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id", nullable = true)
  private Project project;

  /**
   * List of views attached to this workspace
   */
  @OneToMany(mappedBy = "workspaceId", fetch = FetchType.LAZY)
  private List<View> views;

  /**
   * Indique si ce workspace peut avoir des enfants
   */
  @Column(name = "children_wrksp_creation_enabled", nullable = false)
  @Convert(converter = BooleanByteConverter.class)
  private boolean childrenWrkspCreationEnabled = true;

  /**
   * contient le repertoire de base pour les vues de type FileExplorer, utile dans le cadre d''1 workspace projet',
   */
  @Column(name = "base_folder", nullable = true, length = 300)
  private String baseFolder;

  @Column(length = 100)
  private String customer;

  /**
   * code de regroupement d''une même famille de workspaces
   */
  @Length(max = 50)
  @NotBlank
  @NotNull
  private String category;

  @Column(nullable = true, length = 500)
  private String tags;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Convert(converter = WorkspaceTypeEnumConverter.class)
  private WorkspaceTypeEnum type = WorkspaceTypeEnum.CLASSEUR;

  @Column(nullable = false, length = 13)
  private String color = "0,0,0";

  @Column(nullable = false)
  @Convert(converter = BooleanByteConverter.class)
  private boolean bold;

  @Column(nullable = false)
  @Convert(converter = BooleanByteConverter.class)
  private boolean strike;

  @Column(nullable = false)
  @Convert(converter = BooleanByteConverter.class)
  private boolean italic;

  @Column(nullable = false)
  @Convert(converter = BooleanByteConverter.class)
  private boolean underline;

  @Column(length = 300)
  private String icon;

  @Column(nullable = false)
  @Convert(converter = BooleanByteConverter.class)
  private boolean enabled = true;

  /**
   * Contient le layout du workspace, utilisé pour le reconstruire à l''identique lors de la sauvegarde
   */
  @Lob
  private byte[] layout;

  @Column(nullable = false, length = 45)
  private String owner;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Convert(converter = PartageEnumConverter.class)
  private PartageEnum partage = PartageEnum.PUBLIC;

  /**
   * Store the workspace order in a specific perspective
   * This info is stored in table perspective_workspaces.workspaceOrder
   */
  @Transient
  private transient Integer order;

  /** {@inheritDoc} */
  @Override
  public String getId() {
    return id;
  }

  /** {@inheritDoc} */
  @Override
  public void setId(final String id) {
    this.id = id;
  }

  /**
   * The Constructor.
   */
  public Workspace() {
    views = new ArrayList<>(5);
  }

  /**
   * The Constructor.
   */
  public Workspace(String id) {
    this();
    this.id = id;
  }

  /**
   * Gets the master.
   *
   * @return the master
   * 
   *         <pre>
   *         public Workspace getMaster() {
   *           return master;
   *         }
   *         </pre>
   */

  /**
   * Adds the workspaceView attached to the workspace.
   *
   * @param workspaceView the workspace view
   */
  public void addView(final View view) {
    if (views == null) {
      views = new ArrayList<>(5);
    }
    views.add(view);
  }

  /**
   * Removes the workspaceView attached to the workspace.
   *
   * @param workspaceView the workspace view
   */
  public void removeView(final View view) {
    if (views != null) {
      views.remove(view);
    }
  }

  /**
   * Clone without proxy.
   *
   * @return the workspace
   */
  public Workspace cloneWithoutProxy() {
    Workspace result = EntityWorkspaceCloner.instance.cloneWithoutProxy(this);
    return result;
  }

  @Override
  public String toString() {
    return I18nHelperFwk.getMessage(getName());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), id);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Workspace) {
      if (!super.equals(object))
        return false;
      Workspace that = (Workspace) object;
      return Objects.equal(this.id, that.id);
    }
    return false;
  }

  /**
   * The main method for generation purpose
   *
   * @param strings the strings
   */
  public static void main(final String... strings) {
    UtilGen.buildCloneMethodFor(Workspace.class);
  }
}