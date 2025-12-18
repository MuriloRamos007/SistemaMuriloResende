package view;

import bean.TbProdutos;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ControllerConsultasProdutos extends AbstractTableModel {

    private List<TbProdutos> lista;

    public void setList(List<TbProdutos> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public TbProdutos getBean(int rowIndex) {
        return lista.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lista == null ? 0 : lista.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TbProdutos produto = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return produto.getIdProduto();
            case 1:
                return produto.getNmProduto();
            case 2:
                return produto.getPreco();
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Código";
            case 1:
                return "Nome";
            case 2:
                return "Preço";
        }
        return "";
    }
}
