package com.secondhand.domain;

import java.io.Serializable;
/*
 * Secondhand - Item(GroupBuying) Domain Class
 * */
@SuppressWarnings("serial")
public class GroupBuying extends Item implements Serializable {
   
   // Field
   private int discount;
   private int listPrice;
   private String deadLine;
   private int state;  
   
   public GroupBuying() {
      super();
   }
   
   public GroupBuying(Item item, int discount, int listPrice, String deadLine) {
      super(item.getItemId(), item.getUnitCost(), item.getTitle(), item.getDescription(), item.getViewCount(), item.getQty(), item.getTags(), 
            item.getUserId(), item.getProductId());
      this.discount = discount;
      this.listPrice = listPrice;
      this.deadLine = deadLine;
   }
   
   // Getter & Setter
   
   public int getDiscount() {
      return discount;
   }
   public void setDiscount(int discount) {
      this.discount = discount;
   }
   public int getListPrice() {
      return listPrice;
   }
   public void setListPrice(int listPrice) {
      this.listPrice = listPrice;
   }
   public String getDeadLine() {
      return deadLine;
   }
   public void setDeadLine(String deadLine) {
      this.deadLine = deadLine;
   }
   
   public int getState() {
      return state;
   }

   public void setState(int state) {
      this.state = state;
   }
	
   @Override
   public String toString() {
      return "GroupBuying [discount=" + discount + ", listPrice=" + listPrice + ", deadLine=" + deadLine + ", state="
            + state + "]";
   }

}