package com.example.Api;

public class ItemsResponse {

    private String CategoryID;
    private String ItemDesc;
    private int ItemID;  //qhs

    public ItemsResponse(String categoryID, String itemDesc, int itemID) {
        CategoryID = categoryID;
        ItemDesc = itemDesc;
        ItemID = itemID;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public String getItemDesc() {
        return ItemDesc;
    }

    public int getItemID() {
        return ItemID;
    }
}
