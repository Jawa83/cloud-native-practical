package com.ezgroceries.shoppinglist.internal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class Resources<T> extends ArrayList<T> {

    public Resources(List<T> list) {
        this.addAll(list);
    }

}
