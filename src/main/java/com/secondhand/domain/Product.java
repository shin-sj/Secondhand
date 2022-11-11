package com.secondhand.domain;

import java.io.Serializable;

/*
 * Secondhand - Product Domain Class
 * */
@SuppressWarnings("serial")
public class Product implements Serializable {

  /* Private Fields */

  private String productId;
  private String name;
  private String catId;

  /* JavaBeans Properties */

  public String getProductId() { return productId; }
  public void setProductId(String productId) { this.productId = productId.trim(); }

  public String getcatId() { return catId; }
  public void setcatId(String catId) { this.catId = catId; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  /* Public Methods*/

  public String toString() {
    return getName();
  }
}
