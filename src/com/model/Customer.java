package com.model;

import java.util.Objects;

public class Customer {
    private int id ;
    private String name;

    public Customer(){

    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Customer[   " +
                "id = " + id +
                "   |   name = " + name +
                "    ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Customer) {
            if (((Customer) obj).name.equals(name))
                if (((Customer) obj).id == id)
                    return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
