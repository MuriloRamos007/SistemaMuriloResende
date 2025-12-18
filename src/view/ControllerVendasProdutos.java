package view;

import bean.TbVendasProdutos;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ControllerVendasProdutos extends AbstractTableModel {

    private List<TbVendasProdutos> lista;

    public void setList(List<TbVendasProdutos> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public TbVendasProdutos getBean(int rowIndex) {
        return lista.get(rowIndex);
    }

    public void addBean(TbVendasProdutos vp) {
        lista.add(vp);
        fireTableDataChanged();
    }

    public void removeBean(int rowIndex) {
        lista.remove(rowIndex);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lista == null ? 0 : lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TbVendasProdutos vp = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return vp.getTbProdutos().getIdProduto();
            case 1:
                return vp.getTbProdutos().getNmProduto();
            case 2:
                return vp.getQuantidade();
            case 3:
                return vp.getValorUnitario();
            case 4:
                return (vp.getQuantidade() * vp.getValorUnitario()) - vp.getDesconto();
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Código";
            case 1:
                return "Produto";
            case 2:
                return "Quantidade";
            case 3:
                return "Valor Unitário";
            case 4:
                return "Total";
        }
        return "";
    }
}
