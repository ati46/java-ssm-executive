package com.po;

import java.util.Date;

public class SysMaintenance {
    private Integer maintenance_id;

    private String maintenance_name;

    private String maintenance_phone;

    private String maintenance_id_card;

    private String maintenance_address;

    private Date maintenance_time;

    private String maintenance_remark;

    public Integer getMaintenance_id() {
        return maintenance_id;
    }

    public void setMaintenance_id(Integer maintenance_id) {
        this.maintenance_id = maintenance_id;
    }

    public String getMaintenance_name() {
        return maintenance_name;
    }

    public void setMaintenance_name(String maintenance_name) {
        this.maintenance_name = maintenance_name == null ? null : maintenance_name.trim();
    }

    public String getMaintenance_phone() {
        return maintenance_phone;
    }

    public void setMaintenance_phone(String maintenance_phone) {
        this.maintenance_phone = maintenance_phone == null ? null : maintenance_phone.trim();
    }

    public String getMaintenance_id_card() {
        return maintenance_id_card;
    }

    public void setMaintenance_id_card(String maintenance_id_card) {
        this.maintenance_id_card = maintenance_id_card == null ? null : maintenance_id_card.trim();
    }

    public String getMaintenance_address() {
        return maintenance_address;
    }

    public void setMaintenance_address(String maintenance_address) {
        this.maintenance_address = maintenance_address == null ? null : maintenance_address.trim();
    }

    public Date getMaintenance_time() {
        return maintenance_time;
    }

    public void setMaintenance_time(Date maintenance_time) {
        this.maintenance_time = maintenance_time;
    }

    public String getMaintenance_remark() {
        return maintenance_remark;
    }

    public void setMaintenance_remark(String maintenance_remark) {
        this.maintenance_remark = maintenance_remark == null ? null : maintenance_remark.trim();
    }
}