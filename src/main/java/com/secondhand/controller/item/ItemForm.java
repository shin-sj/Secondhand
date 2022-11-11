package com.secondhand.controller.item;

import java.util.List;

import com.secondhand.domain.Tag;

public class ItemForm {
   /* Private Fields */
     private int itemId;   
     private int unitCost;       //정가
     private String title;    //제목
     private String description;   //내용
     private int viewCount;        //조회수
     private int qty;      //재고
     private List<Tag> tags;	//태그 리스트
     private String userId;       //작성자
     private int productId;        //판매 카테고리
     
     public ItemForm() {}

   /* JavaBeans Properties */
     public int getItemId() {
      return itemId;
     }

     public void setItemId(int itemId) {
      this.itemId = itemId;
     }

     public int getUnitCost() {
      return unitCost;
     }

     public void setUnitCost(int unitCost) {
      this.unitCost = unitCost;
     }

     public String getTitle() {
      return title;
     }

     public void setTitle(String title) {
      this.title = title;
     }

     public String getDescription() {
      return description;
     }

     public void setDescription(String description) {
      this.description = description;
     }

     public int getViewCount() {
      return viewCount;
     }

     public void setViewCount(int viewCount) {
      this.viewCount = viewCount;
     }

     public int getQty() {
      return qty;
     }

     public void setQty(int qty) {
      this.qty = qty;
     }

     public String getUserId() {
      return userId;
     }

     public void setUserId(String userId) {
      this.userId = userId;
     }

     public int getProductId() {
      return productId;
     }

     public void setProductId(int productId) {
      this.productId = productId;
     }
     
     public List<Tag> getTags() {
      return tags;
     }

     public void setTags(List<Tag> tags) {
      this.tags = tags;
     }

     @Override
     public String toString() {
      return "ItemForm [itemId=" + itemId + ", unitCost=" + unitCost + ", title=" + title + ", description="
            + description + ", viewCount=" + viewCount + ", qty=" + qty + "tags = " + tags + ", userId=" + userId
            + ", productId=" + productId + "]";
     }
}