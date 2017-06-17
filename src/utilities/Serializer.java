package utilities;

import entities.*;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
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

    private void serialize(List<Object> objects) throws UnsupportedEncodingException, FileNotFoundException, IllegalAccessException, NoSuchFieldException {
        try {
            out.writeStartDocument();
            out.writeCharacters("\n");
            out.writeStartElement("objects");
            parseObjects(objects, 1);
            out.writeEndElement();
            out.writeEndDocument();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                System.out.println("Finished serializing!");
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseObjects(List<Object> objects, int offset) throws XMLStreamException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder("\n");
        if (offset != 0) {
            for (int i = 0; i < offset; i++) {
                stringBuilder.append("\t");
            }
        }
        for (Object object : objects) {
            out.writeCharacters(stringBuilder.toString());
            out.writeStartElement("object");
            out.writeAttribute("type", objects.get(0).getClass().getSimpleName());
            writeFields(object, 2);
            writeMethods(object, 2);
            out.writeCharacters(stringBuilder.toString());
            out.writeEndElement();
            out.writeCharacters("\n");
        }
    }

    private void writeFields(Object object, int offset) throws XMLStreamException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (int i = 0; i < offset; i++) {
            stringBuilder.append("\t");
        }
        for (Field field : object.getClass().getDeclaredFields()) {
            if (Modifier.isTransient(field.getModifiers()))
                continue;
            field.setAccessible(true);
            if (field.get(object).getClass().getSuperclass().equals(Entity.class)) {
                parseObject((Entity) field.get(object), offset);
            } else {
                out.writeCharacters(stringBuilder.toString());
                out.writeStartElement("field");
                out.writeAttribute("type", field.getType().toString());
                out.writeAttribute("name", field.getName());
                out.writeAttribute("value", field.get(object).toString());
                out.writeEndElement();
            }
        }
    }

    private void writeMethods(Object object, int offset) throws XMLStreamException {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (int i = 0; i < offset; i++) {
            stringBuilder.append("\t");
        }
        for (Method method : object.getClass().getDeclaredMethods()) {
            out.writeCharacters(stringBuilder.toString());
            out.writeStartElement("method");
            out.writeAttribute("returntype", method.getReturnType().toString());
            out.writeAttribute("name", method.getName());
            writeParameters(method, offset + 1);
            out.writeCharacters(stringBuilder.toString());
            out.writeEndElement();
        }
    }

    private void writeParameters(Method method, int offset) throws XMLStreamException {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (int i = 0; i < offset; i++) {
            stringBuilder.append("\t");
        }
        for (Parameter parameter : method.getParameters()) {
            out.writeCharacters(stringBuilder.toString());
            out.writeStartElement("args");
            out.writeAttribute("type", parameter.getName());
            out.writeAttribute("name", parameter.getName());
            out.writeEndElement();
        }
    }

    private void parseObject(Entity entity, int offset) throws XMLStreamException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (int i = 0; i < offset; i++) {
            stringBuilder.append("\t");
        }
        out.writeCharacters(stringBuilder.toString());
        out.writeStartElement(entity.getClass().getSimpleName());
        writeFields(entity, offset + 1);
        writeMethods(entity, offset + 1);
        out.writeCharacters(stringBuilder.toString());
        out.writeEndElement();
    }

    public static void main(String[] args) throws FileNotFoundException,
            UnsupportedEncodingException,
            XMLStreamException,
            IllegalAccessException,
            NoSuchFieldException {
        List<Object> objects = new ArrayList<>();
        Group group = new Group(6L, 01, Semester.SPRING);
        Student student = new Student("Mark", "Brown", "Kidman",
                new Date(1994, 01, 01), 6L, group);
        Lesson lesson = new Lesson("android course", new Date(123456789L), new Date(1234567890L), "subject", "desc", "A. Pervushov");
        objects.add(student);
        objects.add(lesson);
        File file = new File("./src/utilities/students.xml");
        Serializer serializer = new Serializer(file);
        serializer.serialize(objects);
    }
}
