package com.gralek.praktiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Figure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public Figure(String description, Section section) {
        this.description = description;
        this.section = section;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Figure parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<Figure> children = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Objects.equals(id, figure.id) &&
                Objects.equals(description, figure.description) &&
                Objects.equals(section, figure.section) &&
                Objects.equals(parent, figure.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, section, parent);
    }

    @Override
    public String toString() {
        return "Figure{" +
                "id=" + id +
                ", description='" + description +
                ", children=" + children +
                '}';
    }
}
