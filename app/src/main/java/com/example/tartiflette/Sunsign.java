package com.example.tartiflette;

public class Sunsign {
    //
    private String Sign;
    private String Begin;
    private String End;
    private String url;
    private String Description;


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSign () {
        return Sign;
    }

    public void setSign (String sign) {
        this.Sign = sign;
    }

    public String getBegin () {
        return Begin;
    }

    public void setBegin (String begin){
        this.Begin=begin;
    }

    public String getEnd () {
        return End;
    }

    public void setEnd (String end){
        this.End=end;
    }
}
