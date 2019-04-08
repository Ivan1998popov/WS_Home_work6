package ru.myproject.ws_home_work6.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenResponse implements Parcelable {

    @SerializedName("access_token")
    @Expose
    private String accessToken;


    @SerializedName("token_type")
    @Expose
    private String typeToken;

    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;

    @SerializedName("expires_in")
    @Expose
    private long expiresIn;

    @SerializedName("scope")
    @Expose
    private String scope;



    protected TokenResponse(Parcel in) {
       this.accessToken = (String) in.readValue(String.class.getClassLoader());
       this.typeToken= (String) in.readValue(String.class.getClassLoader());
       this.refreshToken= (String) in.readValue(String.class.getClassLoader());
       this.expiresIn= (long) in.readValue(long.class.getClassLoader());
       this.scope= (String) in.readValue(String.class.getClassLoader());
    }

    public static final Creator<TokenResponse> CREATOR = new Creator<TokenResponse>() {
        @Override
        public TokenResponse createFromParcel(Parcel in) {
            return new TokenResponse(in);
        }

        @Override
        public TokenResponse[] newArray(int size) {
            return new TokenResponse[size];
        }
    };

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTypeToken() {
        return typeToken;
    }

    public void setTypeToken(String typeToken) {
        this.typeToken = typeToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accessToken);
        dest.writeValue(typeToken);
        dest.writeValue(refreshToken);
        dest.writeValue(expiresIn);
        dest.writeValue(scope);
    }


    @Override
    public String toString() {
        return "TokenResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", typeToken='" + typeToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", scope='" + scope + '\'' +
                '}';
    }

}
