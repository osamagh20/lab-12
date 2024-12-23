package com.example.blogsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "your title is empty")
    @Size(min=4,max = 20)
    private String title;
    @NotEmpty(message = "Enter content")
    private String body;
    @NotEmpty(message = "write category")
    @Size(min=4,max = 20)
    @Pattern(regexp = "^(professional|academic)$",message = "your category not found , " +
            "Enter category : professional or academic ")
    private String category;

    @ManyToOne
    @JsonIgnore
    private MyUser user;
}
