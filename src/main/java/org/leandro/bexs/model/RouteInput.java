package org.leandro.bexs.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteInput {
    final List<String> steps = new ArrayList<>();

    @Setter
    Integer value;

    @Override
    public String toString() {
        return "RouteInput{" +
                "steps=" + steps +
                ", value=" + value +
                '}';
    }

    public String buildLine(String delimiter){
        return String.join(delimiter, steps).concat(delimiter).concat(value.toString());
    }
}
