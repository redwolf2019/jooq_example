/*
 * This file is generated by jOOQ.
 */
package com.redwolf.example.jooq.generator.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Device implements Serializable {

    private static final long serialVersionUID = 503903209;

    private Long   id;
    private String code;
    private String name;
    private String description;

    public Device() {}

    public Device(Device value) {
        this.id = value.id;
        this.code = value.code;
        this.name = value.name;
        this.description = value.description;
    }

    public Device(
        Long   id,
        String code,
        String name,
        String description
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Device (");

        sb.append(id);
        sb.append(", ").append(code);
        sb.append(", ").append(name);
        sb.append(", ").append(description);

        sb.append(")");
        return sb.toString();
    }
}
