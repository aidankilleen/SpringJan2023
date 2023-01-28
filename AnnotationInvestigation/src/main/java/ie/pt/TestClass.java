package ie.pt;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestClass {

    @EqualsAndHashCode.Exclude
    private int id;
    private String name;

    public static void main(String[] args) {

        TestClass tc = new TestClass();
        tc.getId();
        tc.setId(999);
        System.out.println(tc.getId());
        tc.setName("Aidan");
        System.out.println(tc);

        TestClass tc1 = new TestClass(1, "Aidan");
        TestClass tc2 = new TestClass(2, "Aidan");

        if (tc1.equals(tc2)) {
            System.out.println("Same");
        } else {
            System.out.println("Different");
        }
    }
}
