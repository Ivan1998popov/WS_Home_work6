package ru.myproject.ws_home_work6.utils;

public class LoginController {

    public static final LoginController owrInstance = new LoginController();

    public static LoginController getOwrInstance(){
        return owrInstance;
    }

    private LoginController() {
    }


    private String login;
    private String refreshToken;
    private String accessToken;


    public String getLogin() {
        if (login == null){
            login = Prefs.get().getString(Prefs.Keys.Login.toString(), "");
        }
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getRefreshToken() {
        if(refreshToken==null){
            refreshToken=Prefs.get().getString(Prefs.Keys.RefreshToken.toString(),null);
        }
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        Prefs.edit().putString(Prefs.Keys.RefreshToken.toString(),refreshToken).apply();
    }

    public String getAccessToken() {
        if(accessToken==null){
            accessToken=Prefs.get().getString(Prefs.Keys.Token.toString(),null);
        }
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        Prefs.edit().putString(Prefs.Keys.Token.toString(),accessToken).apply();
    }


}
