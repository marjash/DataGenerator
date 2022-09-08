package com.knubisoft;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class X {
    private String name;
    private List<X> list;
    private boolean flag;

}
