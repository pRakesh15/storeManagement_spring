package com.rakesh.server.global;

import com.rakesh.server.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart=new ArrayList<Product>();
    }
}
