package homeworkisdead.blurt;

/**
 * Created by dcook on 11/1/16.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dcook on 9/26/16.
 */
public class MyResponse implements Parcelable {
    @SerializedName("Title")
    public String title;
    @SerializedName("Name")
    public String name;
    @SerializedName("Domain")
    public String domain;
    @SerializedName("BreachDate")
    public String breachDate;
    @SerializedName("PwnCount")
    public String pwnCount;

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public String getBreachDate() {
        return breachDate;
    }

    public String getPwnCount() {
        return pwnCount;
    }

    public String getDescription() {
        return description;
    }

    @SerializedName("Description")

    public String description;

    public MyResponse(String title, String domain, String breachDate, String pwnCount, String description) {

        this.title = title;
        this.domain = domain;
        this.breachDate = breachDate;
        this.pwnCount = pwnCount;
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.name);
        dest.writeString(this.domain);
        dest.writeString(this.breachDate);
        dest.writeString(this.pwnCount);
        dest.writeString(this.description);
    }

    protected MyResponse(Parcel in) {
        this.title = in.readString();
        this.name = in.readString();
        this.domain = in.readString();
        this.breachDate = in.readString();
        this.pwnCount = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<MyResponse> CREATOR = new Parcelable.Creator<MyResponse>() {
        @Override
        public MyResponse createFromParcel(Parcel source) {
            return new MyResponse(source);
        }

        @Override
        public MyResponse[] newArray(int size) {
            return new MyResponse[size];
        }
    };
}