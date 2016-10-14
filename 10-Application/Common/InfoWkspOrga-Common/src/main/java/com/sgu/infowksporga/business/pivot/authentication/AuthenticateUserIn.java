package com.sgu.infowksporga.business.pivot.authentication;

import com.sgu.core.framework.pivot.AbstractIn;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : Authorization Pivot In class<br>
 *
 * @author SGU
 */
@Getter
@Setter
public class AuthenticateUserIn extends AbstractIn {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -5573385359851678654L;

  /**
   * the user password
   */
  private String password;

  /**
   * the language
   */
  private String language;

  /**
   * Constructor<br>
   *
   * @param userLogin The user Login
   * @param password the user password
   * @param language the language to use to display label
   */
  public AuthenticateUserIn(final String userLogin, final String password, final String language) {
    super();
    this.setUserLogin(userLogin);
    this.password = password;
    this.language = language;

  }

  /**
   * Constructor<br>
   */
  public AuthenticateUserIn() {
    super();
  }

}
