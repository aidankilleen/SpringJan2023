package ie.pt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Hello world!
 *
 */
public class App 
{

    public void function1() {
        System.out.println("running function1");
    }

    @VeryImportant
    public void function2() {

        System.out.println("running function2");
    }

    @VeryImportant
    public void function3() {
        System.out.println("running function3");
    }

    public static void main( String[] args ) throws InvocationTargetException, IllegalAccessException {
        System.out.println( "Hello World!" );

        @SuppressWarnings("unused")
        int x = 10;

        App app = new App();

        Method[] methods = app.getClass().getDeclaredMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(VeryImportant.class)) {
                System.out.println(method.getName());

                method.invoke(app);
            }
        }
    }
}
