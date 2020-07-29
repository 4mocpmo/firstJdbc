package com.model;

import java.util.Objects;

public class Part_Order {
    private int quantity;
    private int order_num;
    private int part_num;
    public Part_Order(){
    }

    public Part_Order(int quantity, int part_num, int order_num) {
        this.quantity = quantity;
        this.order_num = order_num;
        this.part_num = part_num;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public void setPart_num(int part_num) {
        this.part_num = part_num;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrder_num() {
        return order_num;
    }

    public int getPart_num() {
        return part_num;
    }

    @Override
    public String toString() {
        return "Part_Order[    " +
                "quantity = " + quantity +
                "   |   order_num = " + order_num +
                "   |   part_num = " + part_num +
                "    ]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part_Order that = (Part_Order) o;
        return quantity == that.quantity &&
                order_num == that.order_num &&
                part_num == that.part_num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, order_num, part_num);
    }
}
