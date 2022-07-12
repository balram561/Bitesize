package com.example.app2.Common;
import com.example.app2.Model.User;
import com.example.app2.Remote.IGoogleService;
import com.example.app2.Remote.RetrofitClient;

public class Common {

    public static User currentUser;
    private static final String GOOGLE_API_URL = "https://maps.googleapis.com/";

    public static IGoogleService getGoogleMapsAPI() {
        return RetrofitClient.getGoogleClient(GOOGLE_API_URL).create(IGoogleService.class);
    }

    public static String convertCodeToStatus(String status) {
        if(status.equals("0"))
            return "Order Placed";
        else if(status.equals("1"))
            return "On My Way!";
        else
            return "Order Delivered";
    }
}