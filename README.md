# About

This progect creates random objects using reflection

## Usage

```java
private static final Populate populate = new Populate();

Map<List<String>, List<Integer>> map = new LinkedHashMap<>();
Object o = populate.create(populate.unpackGenericClass(new Populate.GenericClass<>(map){
                }.getType()))
```
