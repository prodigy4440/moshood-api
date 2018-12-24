package com.fahdisa.moshood.util;

import java.io.Serializable;
import java.util.Objects;

public class Status implements Serializable {
    private Integer code;
    private String description;

    public Status() {
    }

    public Status(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Status)) return false;
        Status status = (Status) o;
        return Objects.equals(getCode(), status.getCode()) &&
                Objects.equals(getDescription(), status.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getDescription());
    }

    @Override
    public String toString() {
        return "Status{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
