package com.sgu.infowksporga.business.pivot.perspective;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sgu.core.framework.pivot.AbstractOut;
import com.sgu.infowksporga.business.entity.Workspace;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : FindPerspectiveStructureOut class<br>
 */
@Getter
@Setter
public class FindPerspectiveStructureOut extends AbstractOut {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1471221529015L;

  /** The workspaces. */
  private List<Workspace> workspaces;

  /** The current workspace id order. */
  private Map<String, Integer> currentWorkspaceIdOrder = new LinkedHashMap<>();

  /**
   * Constructor<br>
   */
  public FindPerspectiveStructureOut() {
    super();

    currentWorkspaceIdOrder = new LinkedHashMap<>(10);
    workspaces = new ArrayList<>(10);
  }

}
