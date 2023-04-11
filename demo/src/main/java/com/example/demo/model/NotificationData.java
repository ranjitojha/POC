package com.example.demo.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NotificationData {

    @SerializedName("body")
    private String mDetail;

    @SerializedName("title")
    private String mTitle;
    
    @SerializedName("image")
    private String image;

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
