package com.knubisoft;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class X {
    private String name;
    private List<String> list;
    private int y;
    private boolean flag;

    @Override
    public String toString() {
        return "X{" +
                "name='" + name + '\'' +
                ", list=" + list +
                ", y=" + y +
                ", flag=" + flag +
                '}';
    }
}
