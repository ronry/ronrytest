package com.ronrytest.protobuf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.citrus.util.StringUtil;

/**
 * <pre>
 * protoc -I=/home/ronry/projects/opensource/ronrytest/src/main/resources/protobuffer/ --java_out=/home/ronry/projects/opensource/ronrytest/src/main/java /home/ronry/projects/opensource/ronrytest/src/main/resources/protobuffer/
 * </pre>
 * 
 * @author ronry
 */
public class ProtoFileGenerator {

    private static Map<Class<?>, String> primitiveNameMapping = new HashMap<Class<?>, String>();
    private static List<String>          libImportList        = new ArrayList<String>();

    static {
        primitiveNameMapping.put(Boolean.class, "BooleanEntity");
        primitiveNameMapping.put(boolean.class, "BooleanEntity");
        primitiveNameMapping.put(java.util.Date.class, "DateEntity");
        primitiveNameMapping.put(double.class, "DoubleEntity");
        primitiveNameMapping.put(Double.class, "DoubleEntity");
        primitiveNameMapping.put(float.class, "FloatEntity");
        primitiveNameMapping.put(Float.class, "FloatEntity");
        primitiveNameMapping.put(int.class, "IntegerEntity");
        primitiveNameMapping.put(Integer.class, "IntegerEntity");
        primitiveNameMapping.put(Long.class, "LongEntity");
        primitiveNameMapping.put(long.class, "LongEntity");
        primitiveNameMapping.put(String.class, "StringEntity");
        primitiveNameMapping.put(Map.class, "MapEntity");

        libImportList.add("lib/boolean.proto");
        libImportList.add("lib/date.proto");
        libImportList.add("lib/double.proto");
        libImportList.add("lib/float.proto");
        libImportList.add("lib/int.proto");
        libImportList.add("lib/long.proto");
        libImportList.add("lib/map.proto");
        libImportList.add("lib/string.proto");
    }

    public static void generate(Class<?> clazz, String rootPath) throws Exception {

        String classSimpleName = clazz.getSimpleName();

        String filePath = rootPath + StringUtil.toLowerCaseWithUnderscores(classSimpleName) + ".proto";

        ProtoFileWriter writer = new ProtoFileWriter(new FileWriter(filePath));

        writer.writeLine(clazz.getPackage() + ";");
        writer.writeLine("option java_outer_classname = \"" + classSimpleName + "Protos\";");
        writer.writeLine("message " + classSimpleName + "{");

        Field[] fields = clazz.getDeclaredFields();

        int index = 1;
        List<String> importList = new ArrayList<String>(libImportList);
        for (int i = 0; i < fields.length; i++) {

            Field field = fields[i];

            Class<?> fClass = field.getType();

            if (fClass.isArray() || Collection.class.isAssignableFrom(fClass)) {
                Type type = null;
                if (fClass.isArray()) {
                    type = fClass.getComponentType();
                }

                Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType _type = (ParameterizedType) genericType;
                    Type[] actualTypeArguments = _type.getActualTypeArguments();
                    type = actualTypeArguments[0];

                } else if (genericType instanceof GenericArrayType) {
                    type = ((GenericArrayType) genericType).getGenericComponentType();
                }

                if (genericType instanceof WildcardType) {
                    type = ((WildcardType) genericType).getUpperBounds()[0];
                }

                fClass = (Class<?>) type;

                writer.write("    repeated ");
            } else if (Map.class.isAssignableFrom(fClass)) {
                writer.write("    repeated ");
            } else {
                writer.write("    optional ");
            }

            if (primitiveNameMapping.containsKey(fClass)) {
                writer.write(primitiveNameMapping.get(fClass));
            } else {
                writer.write(fClass.getSimpleName());
                importList.add(fClass.getSimpleName());
            }
            writer.writeLine(" " + field.getName() + " = " + (index++) + ";");
        }

        writer.writeLine("}");

        for (String aimport : importList) {
            writer.writeLine("import \"" + aimport + "\";");
        }

        writer.flush();
        writer.close();

    }

    static class ProtoFileWriter extends BufferedWriter {

        public ProtoFileWriter(Writer out){
            super(out);
        }

        public void writeLine(String str) throws IOException {
            write(str, 0, str.length());
            newLine();
        }

    }

    public static void main(String[] args) throws Exception {
        ProtoFileGenerator.generate(Bean.class,
                                    "/home/ronry/projects/opensource/ronrytest/src/main/resources/protobuffer/");
    }

}
