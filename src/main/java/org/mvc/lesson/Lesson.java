package org.mvc.lesson;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "description")
    private String description;

    @Column(name="add_info")
    private String addInfo;

    @Column(name="comments", columnDefinition = "TEXT")
    private String comments;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Person author;

    @ManyToMany
    @JoinTable(name = "lesson_persons",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "persons_id"))
    @Size(min=1)
    private Set<Person> persons = new LinkedHashSet<>();

}
