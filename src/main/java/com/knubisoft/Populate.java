package com.knubisoft;

import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Populate {

    static Map<Class<?>, Supplier<Object>> generator = new LinkedHashMap<>();
    static Randomizer randomizer = new Randomizer();

    static {
        generator.put(Integer.class, () -> randomizer.setRandomInt());
        generator.put(Boolean.class, () -> randomizer.setRandomBoolean());
        generator.put(String.class, () -> randomizer.setRandomString());
        generator.put(Long.class, () -> randomizer.setRandomLong());
        generator.put(Float.class, () -> randomizer.setRandomFloat());
        generator.put(Double.class, () -> randomizer.serRandomDouble());
        generator.put(LocalDate.class, () -> randomizer.setRandomDate());

        generator.put(int.class, () -> randomizer.setRandomInt());
        generator.put(boolean.class, () -> randomizer.setRandomBoolean());
        generator.put(long.class, () -> randomizer.setRandomLong());
        generator.put(float.class, () -> randomizer.setRandomFloat());
        generator.put(double.class, () -> randomizer.serRandomDouble());
    }

    @SneakyThrows
    public Object create(Type type) {
        if (isSimpleType(type))
            return generator.get(type).get();
        if (type instanceof ParameterizedType){
            Type rawType = ((ParameterizedType) type).getRawType();
            if (isCollection((Class<?>) rawType)) {
                if (List.class.isAssignableFrom((Class<?>) rawType)) {
                    return getList(type);
                }
                if (Map.class.isAssignableFrom((Class<?>) rawType)) {
                    return getMap(type);
                }
            }
        }
        return getCustomClassInstance(type);
    }

    @SneakyThrows
    private Object getCustomClassInstance(Type type){
        Constructor<?> constructor = ((Class<?>) type).getConstructor();
        Object o = constructor.newInstance();

        Field[] fields = ((Class) type).getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            f.set(o, create(f.getGenericType()));
        }
        return o;
    }


    private List<Object> getList(Type type) {
        Type[] types = nestedTypes(type);
        List<Object> result = new ArrayList<>();
        for (int index = 0; index < 5; index++)
            result.add(create(types[0]));
        return result;
    }

    private  Map<Object, Object> getMap(Type type) {
        Map<Object, Object> result = new LinkedHashMap<>();
        Type[] typeArgs = ((ParameterizedType) type).getActualTypeArguments();
        for (int index = 0; index < 5; index++)
            result.put(create(typeArgs[0]), create(typeArgs[1]));
        return result;
    }

    private boolean isCollection(Class<?> x) {
        return List.class.isAssignableFrom(x) || Map.class.isAssignableFrom(x);
    }

    private boolean isSimpleType(Type type) {
        return generator.containsKey(type);
    }

    public Type unpackGenericClass(Type type) {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return parameterizedType.getRawType().equals(GenericClass.class) ?
                parameterizedType.getActualTypeArguments()[0] : type;
    }

    private static <T> Type[] nestedTypes(Type typeRef) {
        return typeRef instanceof ParameterizedType ?
                ((ParameterizedType) typeRef).getActualTypeArguments() : new Type[]{typeRef};
    }

    @Getter
    public abstract static class GenericClass<T> {
        private final T t;
        private final Type type;

        public GenericClass(T t) {
            this.t = t;
            this.type = this.getClass().getGenericSuperclass();
        }
    }
}
