package com.model;

public class Order {
    private int order_num;
    private int id;
    public Order(){

    }

    public Order(int order_num, int id) {
        this.order_num = order_num;
        this.id = id;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order[   " +
                "order_num = " + order_num +
                "    |   id = " + id +
                "    ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Order) {
            if (this.id == ((Order) obj).id)
                if (((Order) obj).order_num == this.order_num)
                    return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
