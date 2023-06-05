package org.dimas;

import java.time.LocalDateTime;

public class Transaksi {
    private final String namaPembeli;
    private final String jenisKendaraan;
    private final int jumlahPertamax;

    private final LocalDateTime timestamp;

    public Transaksi(String namaPembeli, String jenisKendaraan, int jumlahPertamax) {
        this.namaPembeli = namaPembeli;
        this.jenisKendaraan = jenisKendaraan;
        this.jumlahPertamax = jumlahPertamax;
        this.timestamp = LocalDateTime.now();
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public int getJumlahPertamax() {
        return jumlahPertamax;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return namaPembeli + " mengisi pertamax jenis " + jenisKendaraan + " sebanyak " + jumlahPertamax + " liter";
    }
}
