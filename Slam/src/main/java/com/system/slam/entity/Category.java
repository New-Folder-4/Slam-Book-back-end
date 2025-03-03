package com.system.slam.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @Column(name = "IdCategory", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @Column(name = "Name", length = 25, nullable = false)
    private String name;

    @Column(name = "IdParent", nullable = false)
    private Integer idParent;

    @Column(name = "MultiSelect", nullable = false)
    private boolean multiSelect;

    public Category() {}

    public Category(Long idCategory,
                    String name,
                    Integer idParent,
                    boolean multiSelect) {
        this.idCategory = idCategory;
        this.name = name;
        this.idParent = idParent;
        this.multiSelect = multiSelect;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdParent() {
        return idParent;
    }

    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Category category = (Category) object;
        return multiSelect == category.multiSelect &&
                Objects.equals(idCategory, category.idCategory) &&
                Objects.equals(name, category.name) &&
                Objects.equals(idParent, category.idParent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory, name, idParent, multiSelect);
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", name='" + name + '\'' +
                ", idParent=" + idParent +
                ", multiSelect=" + multiSelect +
                '}';
    }
}

