/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.view.component.table;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;


/**
 *
 * @author Sara
 */
public class StambenaZajednicaTableModel extends AbstractTableModel {

    private  List<StambenaZajednica> stambeneZajednice;
    private String[] columnNames = new String[]{"ID", "Mesto", "Ulica", "Broj", "Banka", "Tekuci racun", "PIB", "Maticni broj"};
    private Class[] columnClasses = new Class[]{Long.class, Mesto.class, String.class, String.class, String.class, String.class, String.class, String.class};

    public StambenaZajednicaTableModel(List<StambenaZajednica> stambeneZajednice) {
        this.stambeneZajednice = stambeneZajednice; //kad neko kreira model, odma mu da listu sa kojom ce da radi
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
        if (stambeneZajednice == null) {
            return 0;
        }
        return stambeneZajednice.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 1) || (columnIndex == 3);
        //if (columnIndex==1) return true;
        //return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StambenaZajednica sz = stambeneZajednice.get(rowIndex); //nadje i-ti product u listi

        switch (columnIndex) {
            case 0:
                return sz.getStambenaZajednicaId();
            case 1:
                return sz.getMesto();
            case 2:
                return sz.getUlica();
            case 3:
                return sz.getBroj();
            case 4:
                return sz.getBanka();
            case 5:
                return sz.getTekuciRacun();
            case 6:
                return sz.getPib();
            case 7:
                return sz.getMaticniBroj();
            default:
                return "n/a";
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        StambenaZajednica sz = stambeneZajednice.get(rowIndex);
        switch (columnIndex) {
//            case 2:
//                sz.setUlica(String.valueOf(value));
//                break;
//            case 3:
//                sz.setBroj(String.valueOf(value));
//                break;
        }
    }

    public void addStambenaZajednica(StambenaZajednica sz) {
        stambeneZajednice.add(sz);
        //fireTableDataChanged();
        fireTableRowsInserted(stambeneZajednice.size() - 1, stambeneZajednice.size() - 1);
    }

    public StambenaZajednica getStambenaZajednicaAt(int row) {
        return stambeneZajednice.get(row);
    }

    public void setStambeneZajednice(List<StambenaZajednica> stambeneZajednice) {
        this.stambeneZajednice = stambeneZajednice;
        fireTableDataChanged();
    }
   
}
