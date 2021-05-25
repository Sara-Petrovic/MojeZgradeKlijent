/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.view.component.table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaSkupstine;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;


/**
 *
 * @author Sara
 */
public class SednicaSkupstineTableModel extends AbstractTableModel{
    private List<SednicaSkupstine> sednice;
    private String[] columnNames = new String[]{"ID", "Datum odrzavanja", "Broj prisutnih", "Dnevni red",  "Prisutni vlasnici"};
    private Class[] columnClasses = new Class[]{Long.class, Date.class, Integer.class, String.class, String.class};

    public SednicaSkupstineTableModel(List<SednicaSkupstine> sednice) {
        if (sednice != null) {
            this.sednice = sednice;
        } else {
            sednice = new ArrayList<>();
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
        if (sednice == null) {
            return 0;
        }
        return sednice.size();
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
        SednicaSkupstine sednicaSkupstine = sednice.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return sednicaSkupstine.getSednicaSkupstineId();
            case 1:
                return sednicaSkupstine.getDatumOdrzavanja();
            case 2:
                return sednicaSkupstine.getBrojPrisutnih();
            case 3:
                return sednicaSkupstine.getDnevniRed();
            case 4:
                String pom = "";
                for (VlasnikPosebnogDela vlasnikPosebnogDela : sednicaSkupstine.getVlasnici()) {
                    pom += vlasnikPosebnogDela.toString() +"; ";
                }
                return pom;
            default:
                return "n/a";
        }
    }


//    public SednicaSkupstine getSednicaAt(int row) {
//        System.out.println(sednice.get(row).getStambenaZajednica() + "u modelu");
//        return sednice.get(row);
//    }

    public void setSednice(List<SednicaSkupstine> sednice) {
        this.sednice = sednice;
        fireTableDataChanged();
    }

    public List<SednicaSkupstine> getSedniceSkupstine() {
        return sednice;
    }
}
