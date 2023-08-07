package ru.inno.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Objects;

public class Cat {
    private ObjectId id;
    private String name;
    private String breed;
    private Integer age;
    @BsonProperty(value = "localtion")
    private String location;
    private String color;
    @BsonProperty(value = "desc")
    private String description;

    public Cat() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat cat)) return false;
        return Objects.equals(getId(), cat.getId()) && Objects.equals(getName(), cat.getName()) && Objects.equals(getBreed(), cat.getBreed()) && Objects.equals(getAge(), cat.getAge()) && Objects.equals(getLocation(), cat.getLocation()) && Objects.equals(getColor(), cat.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBreed(), getAge(), getLocation(), getColor());
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", location='" + location + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
