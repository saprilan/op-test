package com.sapril.op.model;


public class ParsedEfakturData {
    private String npwpPenjual;
    private String namaPenjual;
    private String npwpPembeli;
    private String namaPembeli;
    private String nomorFaktur;
    private String tanggalFaktur;
    private String jumlahDpp;
    private String jumlahPpn;

    public ParsedEfakturData() {
    }

    public ParsedEfakturData(String npwpPenjual, String namaPenjual, String npwpPembeli, String namaPembeli,
                             String nomorFaktur, String tanggalFaktur, String jumlahDpp, String jumlahPpn) {
        this.npwpPenjual = npwpPenjual;
        this.namaPenjual = namaPenjual;
        this.npwpPembeli = npwpPembeli;
        this.namaPembeli = namaPembeli;
        this.nomorFaktur = nomorFaktur;
        this.tanggalFaktur = tanggalFaktur;
        this.jumlahDpp = jumlahDpp;
        this.jumlahPpn = jumlahPpn;
    }

    public String getNpwpPenjual() {
        return npwpPenjual;
    }

    public void setNpwpPenjual(String npwpPenjual) {
        this.npwpPenjual = npwpPenjual;
    }

    public String getNamaPenjual() {
        return namaPenjual;
    }

    public void setNamaPenjual(String namaPenjual) {
        this.namaPenjual = namaPenjual;
    }

    public String getNpwpPembeli() {
        return npwpPembeli;
    }

    public void setNpwpPembeli(String npwpPembeli) {
        this.npwpPembeli = npwpPembeli;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getNomorFaktur() {
        return nomorFaktur;
    }

    public void setNomorFaktur(String nomorFaktur) {
        this.nomorFaktur = nomorFaktur;
    }

    public String getTanggalFaktur() {
        return tanggalFaktur;
    }

    public void setTanggalFaktur(String tanggalFaktur) {
        this.tanggalFaktur = tanggalFaktur;
    }

    public String getJumlahDpp() {
        return jumlahDpp;
    }

    public void setJumlahDpp(String jumlahDpp) {
        this.jumlahDpp = jumlahDpp;
    }

    public String getJumlahPpn() {
        return jumlahPpn;
    }

    public void setJumlahPpn(String jumlahPpn) {
        this.jumlahPpn = jumlahPpn;
    }
}


