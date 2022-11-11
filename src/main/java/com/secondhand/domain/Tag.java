package com.secondhand.domain;

import java.io.Serializable;

/*
 * Mypage, Item - Tag Domain Class
 * */
@SuppressWarnings("serial")
public class Tag  implements Serializable {
   /* Private Fields */
   private int tagId;          // Sequence
   private int itemId;       // (FK) itemId
   private String tagName;
 
   public Tag() {}
   
   public Tag(String tagName) {
      this.tagName = tagName;
   }
      
   /* JavaBeans Properties */
   public int getTagId() { return tagId; }
   public void setTagId(int tagId) {   this.tagId = tagId; }
   
   public int getItemId() { return itemId; }
   public void setItemId(int itemId) { this.itemId = itemId; }
   
   public String getTagName() { return tagName; }
   public void setTagName(String tagName) { this.tagName = tagName; }

   @Override
   public String toString() {
      return "Tag [tagId=" + tagId + ", itemId=" + itemId + ", tagName=" + tagName + "]";
   }
}