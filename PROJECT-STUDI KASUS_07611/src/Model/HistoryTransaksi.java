package Model;

import Entity.Transaksi;
import Entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryTransaksi{
    private List<Transaksi> transaksis = new ArrayList<>();

    public Transaksi saveTransaksi(Transaksi transaksi) {
        transaksis.add(transaksi);
        return transaksi;
    }

    public List<Transaksi> getTrasaksiByUser(User user) {
        return transaksis.stream()
                .filter(transaksi1 -> transaksi1.getPengirim().equals(user) || transaksi1.getPengirim().equals(user))
                .collect(Collectors.toList());
    }
}
