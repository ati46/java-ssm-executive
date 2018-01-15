package com.po;

public class SysMac {
    private Integer mac_id;

    private String mac_code;

    public Integer getMac_id() {
        return mac_id;
    }

    public void setMac_id(Integer mac_id) {
        this.mac_id = mac_id;
    }

    public String getMac_code() {
        return mac_code;
    }

    public void setMac_code(String mac_code) {
        this.mac_code = mac_code == null ? null : mac_code.trim();
    }
}