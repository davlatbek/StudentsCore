package examples;

import appcore.Student;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * Created by davlet on 6/12/17.
 */
public class ReflectionExample {
    public static void main(String[] args) {
        Student student = new Student("Mark", "Brown", "Kidman", new Date(1994, 01, 01), 6L);
        Class studentClass = student.getClass();
        for (Field field : studentClass.getDeclaredFields()){
            System.out.println(field.getName() + " " + field.getType());
        }

        for (Method method : studentClass.getDeclaredMethods()){
            System.out.println(method.getName() + " " + method.getReturnType().toString() + " " + method.getParameterTypes().length);
        }

        for (Annotation annotation : Student.class.getDeclaredAnnotations()){
            System.out.println(annotation.annotationType().toString() + " " + annotation.toString());
        }

        try {
            Field firstName = student.getClass().getDeclaredField("name");
            firstName.setAccessible(true);
            System.out.println(firstName.get(student));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Field studentId = student.getClass().getDeclaredField("id");
            studentId.setAccessible(true);
            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            studentId.setInt(studentId, studentId.getModifiers() & ~Modifier.FINAL);
            studentId.setLong(student, 1L);
            System.out.println(studentId.get(student));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
