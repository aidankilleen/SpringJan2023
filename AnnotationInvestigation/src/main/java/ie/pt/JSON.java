package ie.pt;

import java.lang.reflect.Field;

public class JSON {

    public static String stringify(Object o) throws IllegalAccessException {

        StringBuilder json = new StringBuilder("{");

        Field[] fields = o.getClass().getDeclaredFields();

        for (int i=0; i<fields.length; i++) {

            Field f = fields[i];

            if (!f.isAnnotationPresent(JsonOmit.class)) {

                if (i > 0) {
                    json.append(",");
                }

                json.append("\"");
                json.append(f.getName());
                json.append("\"");

                json.append(":");

                String value = "" + f.get(o);
                if (f.isAnnotationPresent(JsonHide.class)) {
                    value = "***********";
                }

                if (f.getType().isAssignableFrom(String.class)) {
                    json.append("\"");
                    json.append(value);
                    json.append("\"");
                } else {
                    json.append(value);
                }


            }
        }
        json.append("}");

        return json.toString();
    }
}
