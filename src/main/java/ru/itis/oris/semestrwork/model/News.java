package ru.itis.oris.semestrwork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
public class News {
    private int id;
    private String title;
    private String description;
    private String imageUrl;
}
