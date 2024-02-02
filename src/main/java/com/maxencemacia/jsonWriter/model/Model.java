package com.maxencemacia.jsonWriter.model;

import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Model implements Serializable {
    private Long id;
    private String name;
    private Map<String, Object> attributes;
}
