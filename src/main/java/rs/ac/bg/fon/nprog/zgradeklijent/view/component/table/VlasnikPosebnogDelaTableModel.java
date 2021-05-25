/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.view.component.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import rs.ac.bg.fon.nprog.zgradezajednicki.domain.MernaJedinica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;


/**
 *
 * @author Sara
 */
public class VlasnikPosebnogDelaTableModel extends AbstractTableModel {

    private List<VlasnikPosebnogDela> vlasnici;
    private String[] columnNames = new String[]{"ID", "Ime", "Prezime", "Broj posebnog dela", "Velicina posebnog dela", "Merna jedinica", "Kontakt", "Stambena zajednica"};
    private Class[] columnClasses = new Class[]{Long.class, String.class, String.class, String.class, Double.class, MernaJedinica.class, String.class, StambenaZajednica.class};

    public VlasnikPosebnogDelaTableModel(List<VlasnikPosebnogDela> vlasnici) {
        if (vlasnici != null) {
            this.vlasnici = vlasnici;
            System.out.println("postavio ");
        } else {
            vlasnici = new ArrayList<>();
            System.out.println("uneo novu listu");
        }

    }

    @Override
    public String getColumnName(int column) {
        if (column > columnNames.length) {
            return "n/a";
        }
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex > columnClasses.length) {
            return Object.class;
        }
        return columnClasses[columnIndex];
    }

    @Override
    public int getRowCount() {
        if (vlasnici == null) {
            return 0;
        }
        return vlasnici.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VlasnikPosebnogDela vlasnik = vlasnici.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return vlasnik.getVlasnikId();
            case 1:
                return vlasnik.getIme();
            case 2:
                return vlasnik.getPrezime();
            case 3:
                return vlasnik.getBrojPosebnogDela();
            case 4:
                return vlasnik.getVelicinaPosebnogDela();
            case 5:
                return vlasnik.getMernaJedinica();
            case 6:
                return vlasnik.getKontaktVlasnika();
            case 7:
                System.out.println("case u table modelu: " + vlasnik.getStambenaZajednica().getStambenaZajednicaId());
                return vlasnik.getStambenaZajednica();
            default:
                return "n/a";
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        //VlasnikPosebnogDela sz = vlasnici.get(rowIndex);
        // switch (columnIndex) {
//            case 2:
//                sz.setUlica(String.valueOf(value));
//                break;
//            case 3:
//                sz.setBroj(String.valueOf(value));
//                break;
        // }
    }

    public void addVlasnikPosebnogDela(VlasnikPosebnogDela vlasnik) {
        vlasnici.add(vlasnik);
        //fireTableDataChanged();
        fireTableRowsInserted(vlasnici.size() - 1, vlasnici.size() - 1);
    }

    public void removeVlasnikPosebnogDela(int rowIndex) {
        VlasnikPosebnogDela vlasnik = vlasnici.get(rowIndex);
        vlasnici.remove(rowIndex);
        fireTableRowsDeleted(vlasnici.size() - 1, vlasnici.size() - 1);
    }

    public VlasnikPosebnogDela getVlasnikAt(int row) {
        System.out.println(vlasnici.get(row).getStambenaZajednica() + "u modelu");
        return vlasnici.get(row);
    }

    public void setVlasnici(List<VlasnikPosebnogDela> vlasnici) {
        this.vlasnici = vlasnici;
        fireTableDataChanged();
    }

    public List<VlasnikPosebnogDela> getVlasnici() {
        return vlasnici;
    }

}
