package com.example.voteindia;

import org.json.JSONException;
import org.json.JSONObject;

public class YourSmartContract {
    private String in;
    JSONObject reader = new JSONObject(in);
    JSONObject sys  = reader.getJSONObject("VoteIndia");
    public YourSmartContract() throws JSONException {
    }
    public static YourSmartContract load(String contract_address){
        return null;
    }


}
