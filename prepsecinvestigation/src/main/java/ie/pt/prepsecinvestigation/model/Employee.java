package ie.pt.prepsecinvestigation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;

    //@ElementCollection(fetch=FetchType.EAGER)
    //@CollectionTable(name="employee_records", joinColumns = @JoinColumn("employeeId"))
    List<Record> records = new ArrayList<Record>();

}
