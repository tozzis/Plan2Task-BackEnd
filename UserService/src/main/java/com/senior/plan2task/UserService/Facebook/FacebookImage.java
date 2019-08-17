package com.senior.plan2task.UserService.Facebook;

public class FacebookImage {
    
    private FacebookImageData data;

    public FacebookImageData getData() {
        return data;
    }

    public void setData(FacebookImageData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FacebookImage{" + "data=" + data + '}';
    }

}