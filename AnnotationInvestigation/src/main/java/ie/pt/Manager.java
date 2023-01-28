package ie.pt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Manager {

    public int id;
    public String name;
    public String company;
    public int headCount;
    public boolean securityApproved;
    public String country;

    @JsonOmit
    public String password;
}
