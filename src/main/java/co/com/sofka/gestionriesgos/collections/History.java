package co.com.sofka.gestionriesgos.collections;

import co.com.sofka.gestionriesgos.model.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "history")
public class History {

    @Id
    private String id;
    //private String email;
    private LocalDate date;
    private String time;
    private ProjectDTO project;
}
