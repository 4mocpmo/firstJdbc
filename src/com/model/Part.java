package com.model;

public class Part {
    private int part_num;
    private String name;
    private String description;

    public Part(){

    }

    public Part(int part_num, String description, String name) {
        this.part_num = part_num;
        this.name = name;
        this.description = description;
    }

    public int getPart_num() {
        return part_num;
    }

    public void setPart_num(int part_num) {
        this.part_num = part_num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Part [   " +
                "part_num = " + part_num +
                "   |   name = " + name +
                "   |   description = " + description +
                "   ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Part) {
            if (this.name.equals(((Part) obj).name))
                if (this.description.equals(((Part) obj).description))
                    if (this.part_num == ((Part) obj).part_num)
                        return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
