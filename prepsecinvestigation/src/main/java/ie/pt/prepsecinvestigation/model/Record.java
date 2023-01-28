package ie.pt.prepsecinvestigation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Record {

    private int id;
    private int employeeId;
    private String colour;
    private int quantity;
}
