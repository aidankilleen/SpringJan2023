package ie.pt.aopinvestigation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Timed
public class TestClass {

    private String message;

    @Timed
    public void display() throws InterruptedException {

        System.out.println("TestClass:");
        System.out.println(message);
        System.out.println("=========");

        int r = (int) (Math.random() * 20000);

        Thread.sleep(r);
    }
}




