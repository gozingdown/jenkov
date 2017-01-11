package com.zheng.jsontest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class PersonDeserialize {

    public long    id      = 0;
    public String  name    = null;

    @JsonDeserialize(using = OptimizedBooleanDeserializer.class)
    public boolean enabled = false;
}
