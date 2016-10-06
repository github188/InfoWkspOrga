package com.sgu.infowksporga.business.service.rest.serialized.api;

import com.sgu.core.framework.pivot.AbstractIn;
import com.sgu.core.framework.pivot.AbstractOut;

/**
 * The Interface ISerializedBusinessService.
 *
 * @param <IN> the generic type
 * @param <OUT> the generic type
 */
public interface ISerializedBusinessService<IN extends AbstractIn, OUT extends AbstractOut> {

  /**
   * Execute service.
   *
   * @param in the in
   * @return the out
   */
  OUT executeService(IN in);

}
