package com.sgu.infowksporga.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.MoreObjects;
import com.sgu.core.framework.dao.jpa.entity.AbstractAuditedEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends AbstractAuditedEntity<Integer> implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "MySqlIdentifierGenerator", strategy = "com.sgu.core.framework.dao.mysql.MySqlIdentifierGenerator",
  parameters = { @Parameter(name = "tableName", value = "user") })
  @GeneratedValue(generator = "MySqlIdentifierGenerator")
  private Integer id;

  @Column(nullable = false, length = 30)
  private String firstname;

  @Column(nullable = false, length = 30)
  private String lastname;

  @Column(nullable = false, length = 20)
  private String login;

  @Column(nullable = false, length = 30)
  private String password;

  @Column(name = "email_1", nullable = false, length = 45)
  private String email1;

  @Column(name = "email_2", length = 45)
  private String email2;

  @Column(nullable = false)
  private boolean enabled;

  /**
   * The Constructor.
   */
  public User() {
    super();
  }

  /**
   * The Constructor.
   *
   * @param firstname the first name
   * @param lastname the last name
   * @param login the login
   * @param password the password
   * @param email1 the email1
   * @param email2 the email2
   * @param enable the enable
   */
  public User(final String firstname, final String lastname, final String login, final String password, final String email1,
              final String email2, final boolean enabled) {
    super();
    this.firstname = firstname;
    this.lastname = lastname;
    this.login = login;
    this.password = password;
    this.email1 = email1;
    this.email2 = email2;
    this.enabled = enabled;

    setCreationInfo("????", new Date());
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

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("super", super.toString()).add("id", id).add("firstname", firstname)
                      .add("lastname", lastname).add("login", login).add("password", password).add("email1", email1).add("email2", email2)
                      .add("enabled", enabled).toString();
  }

}