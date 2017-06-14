package utilities;

import appcore.*;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by davlet on 6/12/17.
 */
public class Serializer {
    private File file;
    private OutputStream outputStream;
    private XMLStreamWriter out;

    private Serializer(File file) throws FileNotFoundException, UnsupportedEncodingException, XMLStreamException {
        this.file = file;
        outputStream = new FileOutputStream(file);
        out = XMLOutputFactory.newInstance().createXMLStreamWriter(
                new OutputStreamWriter(outputStream, "utf-8"));
    }

    private void serialize(List<Object> objects) throws UnsupportedEncodingException, XMLStreamException, FileNotFoundException, IllegalAccessException, NoSuchFieldException {
        out.writeStartDocument();
        out.writeCharacters("\n");
        out.writeStartElement("objects");
        for (Object object : objects){
            out.writeCharacters("\n\t");
            out.writeStartElement("object");
            out.writeAttribute("type", objects.get(0).getClass().getSimpleName());
            writeFields(object);
            writeMethods(object);
            out.writeCharacters("\n\t");
            out.writeEndElement();
            out.writeCharacters("\n");
        }
        out.writeEndElement();
        out.writeEndDocument();
        out.close();
    }

    private void writeMethods(Object object) throws XMLStreamException {
        for (Method method : object.getClass().getDeclaredMethods()){
            out.writeCharacters("\n\t\t");
            out.writeStartElement("method");
            out.writeAttribute("returntype", method.getReturnType().toString());
            out.writeAttribute("name", method.getName());

            for (Class<?> parameter : method.getParameterTypes()){
                out.writeCharacters("\n\t\t\t");
                out.writeStartElement("args");
                out.writeAttribute("type", parameter.getTypeName().toString());
                out.writeAttribute("name", parameter.getName());
                out.writeEndElement();
            }
            out.writeCharacters("\n\t\t");
            out.writeEndElement();
        }
    }

    private void writeFields(Object object) throws XMLStreamException, IllegalAccessException {
        for (Field field : object.getClass().getDeclaredFields()){
            if (Modifier.isTransient(field.getModifiers())) continue;
            field.setAccessible(true);
            Class<?> c = field.getType();

            out.writeCharacters("\n\t\t");
            out.writeStartElement("field");
            out.writeAttribute("type", field.getType().toString());
            String s = field.get(object).toString();
            out.writeAttribute("name", field.getName());
            out.writeAttribute("value", s);
            System.out.println(field.get(object).toString());
            out.writeEndElement();
        }
    }

    public static void main(String[] args) throws FileNotFoundException,
            UnsupportedEncodingException, XMLStreamException,
            IllegalAccessException, NoSuchFieldException {
        List<Object> objects = new ArrayList<>();
        Group group = new Group(6L, 01, Semester.SPRING);
        Student student = new Student("Mark", "Brown", "Kidman",
                new Date(1994, 01, 01), 6L, group);
        Lesson lesson = new Lesson("android course", new Date(123456789L), new Date(1234567890L), "subject", "desc", "A. Pervushov");
        objects.add(student);
        objects.add(lesson);
        File file = new File("students.xml");
        Serializer serializer = new Serializer(file);
        serializer.serialize(objects);
    }
}
