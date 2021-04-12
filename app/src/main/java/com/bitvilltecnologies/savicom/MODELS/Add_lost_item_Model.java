package com.bitvilltecnologies.savicom.MODELS;

public class Add_lost_item_Model {
    String lostimage, lostdate,lostdes;

    public Add_lost_item_Model() {
    }

    public Add_lost_item_Model(String lostimage, String lostdate, String lostdes) {
        this.lostimage = lostimage;
        this.lostdate = lostdate;
        this.lostdes = lostdes;
    }


    public String getLostimage() {
        return lostimage;
    }

    public void setLostimage(String lostimage) {
        this.lostimage = lostimage;
    }

    public String getLostdate() {
        return lostdate;
    }

    public void setLostdate(String lostdate) {
        this.lostdate = lostdate;
    }

    public String getLostdes() {
        return lostdes;
    }

    public void setLostdes(String lostdes) {
        this.lostdes = lostdes;
    }
}
