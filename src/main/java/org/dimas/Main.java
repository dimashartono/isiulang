package org.dimas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final Map<String, Pembeli> pembeliMap = new HashMap<>();
    private static final List<Transaksi> transaksiList = new ArrayList<>();

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            StringBuilder inputBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }

                inputBuilder.append(line).append(System.lineSeparator());
            }

            String[] input = inputBuilder.toString().split("\n");
            for(String word : input){
                executeCommands(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void executeCommands(String command) {
        String[] parts = command.split(";");
        String action = parts[0];

        switch (action) {
            case "isi": {
                String[] data = parts[1].split("-");
                String nama = data[0];
                String jenisKendaraan = data[1];
                int jumlahPembelian = Integer.parseInt(parts[2]);
                isiPertamax(nama, jenisKendaraan, jumlahPembelian);
                break;
            }
            case "total": {
                String jenisKendaraan = parts[1];
                showTotalPengisian(jenisKendaraan);
                break;
            }
            case "cek": {
                String nama = parts[1];
                cekSaldoPertamax(nama);
                break;
            }
            case "refill": {
                String[] data = parts[1].split("-");
                String nama = data[0];
                String jenisKendaraan = data[1];
                refillPertamax(nama, jenisKendaraan);
                break;
            }
            case "data pembeli":
                showDataPembeli();
                break;
            case "data transaksi":
                showDataTransaksi();
                break;
            default: {
                String[] data = command.split("-");
                if(data.length == 1){
                    break;
                } else {
                    String nama = data[0];
                    String jenisKendaraan = data[1];
                    registerPembeli(nama, jenisKendaraan);
                    break;
                }
            }
        }
    }

    private static void registerPembeli(String nama, String jenisKendaraan) {
        String key = nama + "-" + jenisKendaraan;
        if (pembeliMap.containsKey(key)) {
            System.out.println("Pembeli sudah terdaftar dengan jenis kendaraan yang sama");
        } else {
            Pembeli pembeli = new Pembeli(nama, jenisKendaraan);
            pembeliMap.put(key, pembeli);
        }
    }

    private static void isiPertamax(String nama, String jenisKendaraan, int jumlahPembelian) {
        Pembeli pembeli = getPembeli(nama, jenisKendaraan);
        if (pembeli != null) {
            pembeli.setIsiPertamax(jumlahPembelian);
            transaksiList.add(new Transaksi(nama, jenisKendaraan, jumlahPembelian));
        } else {
            System.out.println("Nama dan kendaraan belum terdaftar");
        }
    }

    private static void showTotalPengisian(String jenisKendaraan) {
        int totalPengisian = 0;
        for (Transaksi transaksi : transaksiList) {
            if (transaksi.getJenisKendaraan().equals(jenisKendaraan)) {
                System.out.println(transaksi.getNamaPembeli() + " telah mengisi: " + transaksi.getJumlahPertamax() + " liter" );
                totalPengisian += transaksi.getJumlahPertamax();
            }
        }
        System.out.println("Total pengisian " + jenisKendaraan + ": " + totalPengisian + " liter");
    }

    private static void cekSaldoPertamax(String nama) {
        Pembeli pembeliMotor = pembeliMap.get(nama+"-motor");
        Pembeli pembeliMobil = pembeliMap.get(nama+"-mobil");

        if (pembeliMotor == null && pembeliMobil == null) {
            System.out.println("Nama tersebut belum terdaftar");
            return;
        }

        if (pembeliMotor != null) {
            pembeliMotor.cekSisaPertamax();
        }

        if (pembeliMobil != null) {
            pembeliMobil.cekSisaPertamax();
        }
    }

    private static void refillPertamax(String nama, String jenisKendaraan) {
        Pembeli pembeli = getPembeli(nama, jenisKendaraan);
        if (pembeli != null) {
            pembeli.refillPertamax();
        } else {
            System.out.println("Nama dan kendaraan belum terdaftar");
        }
    }

    private static Pembeli getPembeli(String nama, String jenisKendaraan) {
        String key = nama + "-" + jenisKendaraan;
        return pembeliMap.get(key);
    }

    private static void showDataPembeli() {
        List<Pembeli> pembeliList = new ArrayList<>(pembeliMap.values());
        pembeliList.sort((p1, p2) -> p1.getNama().compareToIgnoreCase(p2.getNama()));

        System.out.println("Data pembeli secara ascending berdasarkan nama:");
        for (int i = 0; i < pembeliList.size(); i++) {
            Pembeli pembeli = pembeliList.get(i);
            System.out.println((i + 1) + ". " + pembeli );
        }
    }

    private static void showDataTransaksi() {
        if (transaksiList.isEmpty()) {
            System.out.println("Belum ada transaksi apapun");
        } else {
            transaksiList.sort(Comparator.comparing(Transaksi::getTimestamp));

            System.out.println("Data transaksi yang berhasil dilakukan secara ascending berdasarkan waktu pengisian:");
            for (int i = 0; i < transaksiList.size(); i++) {
                Transaksi transaksi = transaksiList.get(i);
                System.out.println(transaksi);
            }
        }
    }



}