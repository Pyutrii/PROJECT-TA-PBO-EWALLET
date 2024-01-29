package Entity;

import Entity.User;

public class Transaksi {
    private int id;
    private User pengirim;
    private User penerima;
    private double jmlh;
    private String type;

    public Transaksi(int id, User pengirim, User penerima, double jmlh, String type) {
        this.id = id;
        this.pengirim = pengirim;
        this.penerima = penerima;
        this.jmlh = jmlh;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPengirim() {
        return pengirim;
    }

    public void setPengirim(User pengirim) {
        this.pengirim = pengirim;
    }

    public User getPenerima() {
        return penerima;
    }

    public void setPenerima(User penerima) {
        this.penerima = penerima;
    }

    public double getJmlh() {
        return jmlh;
    }

    public void setJmlh(double jmlh) {
        this.jmlh = jmlh;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
