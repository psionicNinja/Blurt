package homeworkisdead.blurt;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by dcook on 11/1/16.
 */
public class MainResponseEvent implements Parcelable {
    private String mTitle;
    private String mDomain;
    private String mBreachDate;
    private String mPwnCount;
    private String mDescription;

    private ArrayList<MyResponse> mResponses;
    public String getTitle() {
        return mTitle;
    }

    public String getDomain() {
        return mDomain;
    }

    public String getBreachDate() {
        return mBreachDate;
    }

    public String getPwnCount() {
        return mPwnCount;
    }

    public void setmPwnCount(String mPwnCount) {
        this.mPwnCount = mPwnCount;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public MainResponseEvent(ArrayList<MyResponse> arrayList) {

        mResponses=arrayList;
    }

    public ArrayList<MyResponse> getmResponses() {
        return mResponses;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeString(this.mDomain);
        dest.writeString(this.mBreachDate);
        dest.writeString(this.mPwnCount);
        dest.writeString(this.mDescription);
    }

    protected MainResponseEvent(Parcel in) {
        this.mTitle = in.readString();
        this.mDomain = in.readString();
        this.mBreachDate = in.readString();
        this.mPwnCount = in.readString();
        this.mDescription = in.readString();
    }

    public static final Parcelable.Creator<MainResponseEvent> CREATOR = new Parcelable.Creator<MainResponseEvent>() {
        @Override
        public MainResponseEvent createFromParcel(Parcel source) {
            return new MainResponseEvent(source);
        }

        @Override
        public MainResponseEvent[] newArray(int size) {
            return new MainResponseEvent[size];
        }
    };
}
