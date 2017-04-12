package com.example.roman.supercontact;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Roman on 28/02/2017.
 */

public class Contact implements Parcelable {
    Contact(int id, String first_name, String last_name, String home_phone, String mobile_phone, String mail)
    {
        inserted = true;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mail = mail;
        this.home_phone = home_phone;
        this.mobile_phone = mobile_phone;
    }

    Contact(String first_name, String last_name, String home_phone, String mobile_phone, String mail)
    {
        inserted = false;
        this.id = -1;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mail = mail;
        this.home_phone = home_phone;
        this.mobile_phone = mobile_phone;
    }

    public void replaceEmptyByNA()
    {
        if (home_phone.isEmpty())
            home_phone = "N/A";
        if (mobile_phone.isEmpty())
            mobile_phone = "N/A";
        if (mail.isEmpty())
            mail = "N/A";
    }
    public static final Parcelable.Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source.readInt(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString());
        }
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(first_name);
        parcel.writeString(last_name);
        parcel.writeString(home_phone);
        parcel.writeString(mobile_phone);
        parcel.writeString(mail);
    }

    public boolean inserted;
    public int id;
    public String first_name;
    public String last_name;
    public String home_phone;
    public String mobile_phone;
    public String mail;
}
