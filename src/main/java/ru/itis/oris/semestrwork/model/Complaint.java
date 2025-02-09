package ru.itis.oris.semestrwork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Complaint {
    private int id;
    private int userId;
    private String complaintText;
    private String status;
}
