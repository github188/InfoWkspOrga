package com.sgu.infowksporga.business.exception;

import com.sgu.core.framework.exception.BusinessException;

/**
 * Description : Exception thrown when a problem occured when a user tries to access to a service.<br>
 *
 * @author SGU
 */
public class UserSessionException extends BusinessException {

  /**
   * The attribute serialVersionUID.
   */
  private static final long serialVersionUID = -1790948890285785500L;

  /**
   * Constructor<br>
   *
   * @param  message the detail message (which is saved for later retrieval
   *         by the {@link #getMessage()} method).
   * @param  cause the cause (which is saved for later retrieval by the
   *         {@link #getCause()} method).  (A <tt>null</tt> value is
   *         permitted, and indicates that the cause is nonexistent or
   *         unknown.)
   */
  public UserSessionException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor<br>
   *
   * @param  message the detail message (which is saved for later retrieval
   *         by the {@link #getMessage()} method).
   */
  public UserSessionException(final String message) {
    super(message);
  }

  /**
   * Constructor<br>
   *
   * @param  cause the cause (which is saved for later retrieval by the
   *         {@link #getCause()} method).  (A <tt>null</tt> value is
   *         permitted, and indicates that the cause is nonexistent or
   *         unknown.)
   */
  public UserSessionException(final Throwable cause) {
    super(cause);
  }

}
