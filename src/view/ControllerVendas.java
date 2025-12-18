package view;

import bean.TbVendas;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ControllerVendas extends AbstractTableModel {

    private List<TbVendas> lista;

    public void setList(List<TbVendas> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public TbVendas getBean(int rowIndex) {
        return lista.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lista == null ? 0 : lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TbVendas venda = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return venda.getIdVenda();
            case 1:
                return venda.getDtVenda();
            case 2:
                return venda.getTotal();
            case 3:
                return venda.getTbClientes().getNmCliente();
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Código";
            case 1:
                return "Data";
            case 2:
                return "Total";
            case 3:
                return "Cliente";
        }
        return "";
    }
}
