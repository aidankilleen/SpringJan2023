package ie.pt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    public int id;
    @JsonOmit
    public String name;
    @JsonHide
    public String email;
    public boolean active;
}
