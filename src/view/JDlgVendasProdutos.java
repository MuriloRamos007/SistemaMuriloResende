package view;

import bean.TbProdutos;
import dao.GenericDAO;
import tools.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class JDlgVendasProdutos extends JDialog {

    private TbProdutos produtoSelecionado;
    private int quantidade;

    public JDlgVendasProdutos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        // Carrega produtos ativos
        GenericDAO<TbProdutos> dao = new GenericDAO<>(TbProdutos.class);
        List<TbProdutos> lista = dao.listAll();

        DefaultTableModel modelo = (DefaultTableModel) jTableProdutos.getModel();
        modelo.setRowCount(0);
        for (TbProdutos p : lista) {
            if (p.getAtivo() == null || p.getAtivo() != 'N') { // filtra inativos
                modelo.addRow(new Object[]{p.getIdProduto(), p.getNmProduto(), p.getPreco(), p.getCategoria(), p.getSabor()});
            }
        }

        // Double click para selecionar produto
        jTableProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int linha = jTableProdutos.getSelectedRow();
                    if (linha != -1) {
                        int id = (int) modelo.getValueAt(linha, 0);
                        produtoSelecionado = dao.findById(id, "idProduto");
                        quantidade = Integer.parseInt(jSpinnerQtd.getValue().toString());
                        dispose();
                    } else {
                        Util.mensagem("Selecione um produto primeiro.");
                    }
                }
            }
        });
    }

    public TbProdutos getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTableProdutos = new JTable();
        jSpinnerQtd = new JSpinner();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Selecionar Produto");

        jTableProdutos.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Produto", "Preço", "Categoria", "Sabor"}
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        jScrollPane1.setViewportView(jTableProdutos);

        JLabel lblQtd = new JLabel("Quantidade:");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(10)
                .addComponent(lblQtd)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerQtd, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
            .addGap(10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblQtd)
                .addComponent(jSpinnerQtd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    private JScrollPane jScrollPane1;
    private JTable jTableProdutos;
    private JSpinner jSpinnerQtd;
}
