package com.example.muhammadhumayun.readqr;

public class merchantStringHandle {
    String merchantstring;
    String tag,value,length;


    public String getMerchantstring() {
        return merchantstring;
    }

    public void setMerchantstring(String merchantstring) {
        this.merchantstring = merchantstring;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public merchantStringHandle (String merchant){
        this.merchantstring = merchant;
    }


}
