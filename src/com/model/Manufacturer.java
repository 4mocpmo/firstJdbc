package com.model;

public class Manufacturer {
    private String name;
    private String city;
    private String street;

    public Manufacturer(String name, String city, String street) {
        this.name = name;
        this.city = city;
        this.street = street;
    }



    public Manufacturer() {
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return this.street;
    }

    @Override
    public String toString() {
        return "Manufacturer[   " +
                " name = " + name +
                "   |   city = " + city  +
                "   |   street = " + street +
                "    ]";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Manufacturer){
            if (this.city.equals(((Manufacturer) obj).city))
                if (this.name.equals(((Manufacturer) obj).name))
                    if (this.street.equals(((Manufacturer) obj).street))
                        return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
