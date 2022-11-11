package com.secondhand.domain;

import java.io.Serializable;

/*
 * Sequence Domain Class
 * */
@SuppressWarnings("serial")
public class Sequence implements Serializable {

  /* Private Fields */

  private String name;
  private int nextId;

  /* Constructors */

  public Sequence() { }

  public Sequence(String name, int nextId) {
    this.name = name;
    this.nextId = nextId;
  }

  /* JavaBeans Properties */

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public int getNextId() { return nextId; }
  public void setNextId(int nextId) { this.nextId = nextId; }

}
