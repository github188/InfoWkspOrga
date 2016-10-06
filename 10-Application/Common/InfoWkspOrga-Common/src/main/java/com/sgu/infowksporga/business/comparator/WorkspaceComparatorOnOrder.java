package com.sgu.infowksporga.business.comparator;

import com.sgu.infowksporga.business.entity.Workspace;

public class WorkspaceComparatorOnOrder implements java.util.Comparator<Workspace> {

  /**
   * {@inheritDoc}
   */
  @Override
  public int compare(final Workspace a, final Workspace b) {

    if (a == b) {
      return 0;
    }

    final Number n1 = a.getOrder();
    final Number n2 = b.getOrder();

    if (n1 == null && n2 != null) {
      return -1;
    }

    if (n1 != null && n2 == null) {
      return 1;
    }

    if (n1 == null && n2 == null) {
      return 0;
    }

    if (n1 != null && n2 != null) {
      final double d1 = n1.doubleValue();
      final double d2 = n2.doubleValue();

      if (d1 < d2) {
        return -1;
      }
      else if (d1 > d2) {
        return 1;
      }
      else {
        return 0;
      }
    }
    return 0;
  }
}
