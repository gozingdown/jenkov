package com.zheng.jsontest;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

public class PersonValue {

    public long   personId = 0;
    public String name = null;

    @JsonValue
    @JsonRawValue
    public String toJson(){
        return this.personId + "," + this.name;
    }

}
