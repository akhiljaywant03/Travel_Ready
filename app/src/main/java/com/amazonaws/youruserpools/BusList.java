package com.amazonaws.youruserpools;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class BusList implements  Parcelable{



    //@ColumnInfo(name = "title")
    private String Bus;

    //@ColumnInfo(name = "content")
    private String crowd;

    //@ColumnInfo(name = "timestamp")
    private String fare;

    public BusList(String Buses, String crowd, String fare) {
        this.Bus = Buses;
        this.crowd = crowd;
        this.fare = fare;
    }


    public BusList() {

    }


    protected BusList(Parcel in) {
        Bus = in.readString();
        crowd = in.readString();
        fare = in.readString();
    }

    public static final Creator<BusList> CREATOR = new Creator<BusList>() {
        @Override
        public BusList createFromParcel(Parcel in) {
            return new BusList(in);
        }

        @Override
        public BusList[] newArray(int size) {
            return new BusList[size];
        }
    };

    public String getBus() {
        return Bus;
    }

    public void setBus(String bus) {
        Bus = bus;
    }

    public String getCrowd() {
        return crowd;
    }

    public void setCrowd(String crowd) {
        this.crowd = crowd;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }




    @Override
    public String toString() {
        return "Note{" +
                "Bus='" + Bus + '\'' +
                ", crowd='" + crowd + '\'' +
                ", fare='" + fare + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Bus);
        dest.writeString(crowd);
        dest.writeString(fare);
    }
}
