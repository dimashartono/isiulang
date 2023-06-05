package org.dimas;

public class Pembeli {
    private final String nama;
    private String jenisKendaraan;
    private int sisaPertamax;

    public Pembeli(String nama, String jenisKendaraan) {
        this.nama = nama;
        this.jenisKendaraan = jenisKendaraan;
        this.sisaPertamax = jenisKendaraan.equals("motor") ? 10 : 50;
    }

    public String getNama() {
        return nama;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
        this.sisaPertamax = jenisKendaraan.equals("motor") ? 10 : 50;
    }

    public int getSisaPertamax() {
        return sisaPertamax;
    }

    public void setIsiPertamax(int jumlahPembelian) {
        if (jumlahPembelian <= sisaPertamax) {
            sisaPertamax -= jumlahPembelian;
            System.out.println("Berhasil melakukan pengisian " + jenisKendaraan + "[" + nama + "]");
        } else {
            System.out.println("Maaf, sisa pertamax tidak mencukupi");
        }
    }

    public void refillPertamax() {
        int maxPengisian = jenisKendaraan.equals("motor") ? 10 : 50;
        int tambahanPertamax = maxPengisian + sisaPertamax;
        sisaPertamax = tambahanPertamax;
        System.out.println(nama + " berhasil melakukan pengisian ulang sebanyak " + tambahanPertamax + " liter");
    }

    public void cekSisaPertamax() {
        System.out.println(jenisKendaraan.substring(0, 1).toUpperCase() + jenisKendaraan.substring(1) + " : " + sisaPertamax + " liter");
    }

    @Override
    public String toString() {
        return nama + " | jenis " + jenisKendaraan + " | sisa maksimal pengisian: " + sisaPertamax + " liter";
    }
}
