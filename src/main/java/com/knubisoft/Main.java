package com.knubisoft;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Main {
    static Map<Class<?>, Supplier<Object>> generator = new LinkedHashMap<>();

    static {
        generator.put(Integer.class, () -> 1);
        generator.put(Boolean.class, () -> true);
        generator.put(String.class, () -> "Hello");
        generator.put(Long.class, () -> 123456789);
        generator.put(Float.class, () -> 123.0F);
        generator.put(Double.class, () -> 123456789.0);
        generator.put(LocalDate.class, () -> LocalDate.now());
        generator.put(List.class, () -> Arrays.asList("hello, goodbye"));
    }

    public static void main(String[] args) {
        Map<String, Integer> realObject = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
//        LocalDate realObject = LocalDate.parse("2014-04-28");
        Object o = create(new GenericClass<>(list) {
        }.getType());
        System.out.println(o);

    }

    @SneakyThrows
    private static Object populate(Object x) {
        Type[] types = nestedTypes((Type) x);
        if (isSimpleType(types[0])) {
            return generator.get(types[0]).get();
        } else {
            Type rawType = ((ParameterizedType) types[0]).getRawType();
            if (isCollection((Class<?>) rawType)) {
                if (List.class.isAssignableFrom((Class<?>) rawType)) {
                    List<Object> result = new ArrayList<>();
                    for (int index = 0; index < 5; index++)
                        result.add(populate(types[0]));
                    return result;
                }
                if (Map.class.isAssignableFrom((Class<?>) rawType)){
                    Map<Object, Object> result = new LinkedHashMap<>();
                    for (int index = 0; index < 5; index++)
                        result.put(populate(types[0]), populate(types[1]));
                    return result;
                }

            }
        }
//            } else {
            //we caught class
            // Class.forName(x.getType().getTypeName()).getDeclaredFields()
        return null;
}

    private static boolean isCollection(Class<?> x) {
        return List.class.isAssignableFrom(x) || Map.class.isAssignableFrom(x);
    }

    private static boolean isSimpleType(Object x) {
        return generator.containsKey(x);
    }

    private static <T> Type[] nestedTypes(Type typeRef) {
        return typeRef instanceof ParameterizedType ?
                ((ParameterizedType) typeRef).getActualTypeArguments() : new Type[]{typeRef};
    }

    private static Type unpackGenericClass(Type type) {
        ParameterizedType params = (ParameterizedType) type;
        return params.getRawType().equals(GenericClass.class) ? params.getActualTypeArguments()[0] : type;
    }

    public static Object create(Type type) {
        return populate(type);
    }

@Getter
public static abstract class GenericClass<T> {
    private final T t;
    private final Type type;

    public GenericClass(T t) {
        this.t = t;
        this.type = this.getClass().getGenericSuperclass();
        ;
    }
}
}
