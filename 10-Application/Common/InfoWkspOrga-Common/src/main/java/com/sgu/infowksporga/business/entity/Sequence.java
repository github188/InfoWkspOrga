package com.sgu.infowksporga.business.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the sequence database table.
 */
@Entity
@Table(name = "sequence")
@Setter
@Getter
public class Sequence implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(unique = true, nullable = false, length = 100)
  private String name;

  private BigInteger curValue;

  @Column(nullable = false)
  private byte cycle;

  @Column(nullable = false)
  private int increment;

  @Column(nullable = false)
  private BigInteger maxValue;

  @Column(nullable = false)
  private int minValue;

  public Sequence() {
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("name", name).add("curValue", curValue).add("cycle", cycle).add("increment", increment)
                  .add("maxValue", maxValue).add("minValue", minValue).toString();
  }

}