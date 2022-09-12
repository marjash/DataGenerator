package com.knubisoft;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Point {
    private int x;
    private int y;

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

