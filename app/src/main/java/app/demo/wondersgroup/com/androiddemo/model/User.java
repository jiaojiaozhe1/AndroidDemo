package app.demo.wondersgroup.com.androiddemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangwentao on 2016/12/2.
 */

public class User implements Parcelable{
    private String accountId;
    public User(){}

    protected User(Parcel in) {
        accountId = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(accountId);
    }
}
