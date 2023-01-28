package ie.pt.aopinvestigation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestBean {

    private String message;


    public void display() {

        System.out.println("TestBean:");
        System.out.println(message);
        System.out.println("=========");
    }
}
