package arep.parcial;

import java.lang.Math;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    private Math math;
    private static Method m;

    
    
    public static void main(String[] args) {
        
        Class c = Math.class;
        try {
            m = c.getMethod("",Double.TYPE);
            m.invoke(null, Double.parseDouble(""));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}