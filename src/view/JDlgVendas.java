package view;

import dao.GenericDAO;
import bean.TbClientes;
import bean.TbProdutos;
import bean.TbUsuarios;
import bean.TbVendas;
import bean.TbVendasProdutos;
import tools.Util;

import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 * JDlgVendas - Tela de vendas corrigida para usar TbClientes, TbUsuarios e TbProdutos
 */
public class JDlgVendas extends javax.swing.JDialog {

    public JDlgVendas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        // CONFIGURA TABELA
        String colunas[] = {"ID", "Produto", "Qtd", "Preço", "Subtotal"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        jTableItens.setModel(modelo);

        // CARREGA CLIENTES (entidades)
        GenericDAO<TbClientes> cliDAO = new GenericDAO<>(TbClientes.class);
        List<TbClientes> listaCli = cliDAO.listAll();
        jCboCliente.removeAllItems();
        for (TbClientes c : listaCli) {
            jCboCliente.addItem(c);
        }

        // CARREGA USUÁRIOS (vendedores) como objetos TbUsuarios
        GenericDAO<TbUsuarios> usuDAO = new GenericDAO<>(TbUsuarios.class);
        List<TbUsuarios> listaUsu = usuDAO.listAll();
        jCboVendedor.removeAllItems();
        for (TbUsuarios u : listaUsu) {
            jCboVendedor.addItem(u);
        }

        // Opcional: esconder a coluna ID do produto (index 0) se quiser visual limpo:
        // jTableItens.getColumnModel().getColumn(0).setMinWidth(0);
        // jTableItens.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    private void atualizarTotal() {
        double total = 0;
        DefaultTableModel modelo = (DefaultTableModel) jTableItens.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object val = modelo.getValueAt(i, 4);
            if (val instanceof Number) {
                total += ((Number) val).doubleValue();
            } else {
                try {
                    total += Double.parseDouble(val.toString());
                } catch (Exception ex) {
                    // ignora célula inválida
                }
            }
        }
        jTxtTotal.setText(String.format("%.2f", total));
    }

    private TbVendas viewBean() {
        TbVendas v = new TbVendas();

        // seta cliente como entidade
        TbClientes cliente = (TbClientes) jCboCliente.getSelectedItem();
        v.setTbClientes(cliente);

        // seta vendedor como entidade (TbUsuarios)
        TbUsuarios vendedor = (TbUsuarios) jCboVendedor.getSelectedItem();
        v.setTbUsuarios(vendedor);

        // data da venda
        v.setDtVenda(new Date());

        // total
        try {
            v.setTotal(Double.parseDouble(jTxtTotal.getText().replace(",", ".")));
        } catch (Exception ex) {
            v.setTotal(0.0);
        }

        v.setStatus("CONFIRMADA");

        return v;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCboCliente = new javax.swing.JComboBox<TbClientes>();
        jCboVendedor = new javax.swing.JComboBox<TbUsuarios>();
        jBtnAddItem = new javax.swing.JButton();
        jBtnDelItem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableItens = new javax.swing.JTable();
        jBtnConfirmar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jTxtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vendas");

        jLabel1.setText("Cliente:");
        jLabel2.setText("Vendedor:");
        jLabel3.setText("Total:");

        jBtnAddItem.setText("Incluir Item");
        jBtnAddItem.addActionListener(evt -> jBtnAddItemActionPerformed(evt));

        jBtnDelItem.setText("Excluir Item");
        jBtnDelItem.addActionListener(evt -> jBtnDelItemActionPerformed(evt));

        jTableItens.setModel(new javax.swing.table.DefaultTableModel());
        jScrollPane1.setViewportView(jTableItens);

        jBtnConfirmar.setText("Confirmar");
        jBtnConfirmar.addActionListener(evt -> jBtnConfirmarActionPerformed(evt));

        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.addActionListener(evt -> dispose());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jCboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jCboVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jBtnAddItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnDelItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCboVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAddItem)
                    .addComponent(jBtnDelItem)
                    .addComponent(jBtnConfirmar)
                    .addComponent(jBtnCancelar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    private void jBtnAddItemActionPerformed(java.awt.event.ActionEvent evt) {
        JDlgVendasProdutos tela = new JDlgVendasProdutos(null, true);
        tela.setVisible(true);

        TbProdutos p = tela.getProdutoSelecionado();
        if (p != null) {
            int qtd = tela.getQuantidade();
            double subtotal = p.getPreco() * qtd;

            DefaultTableModel modelo = (DefaultTableModel) jTableItens.getModel();
            modelo.addRow(new Object[]{
                p.getIdProduto(),
                p.getNmProduto(),
                qtd,
                p.getPreco(),
                subtotal
            });

            atualizarTotal();
        }
    }

    private void jBtnDelItemActionPerformed(java.awt.event.ActionEvent evt) {
        int linha = jTableItens.getSelectedRow();
        if (linha == -1) {
            Util.mensagem("Selecione um item.");
            return;
        }
        if (Util.perguntar("Remover item?")) {
            ((DefaultTableModel) jTableItens.getModel()).removeRow(linha);
            atualizarTotal();
        }
    }

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTableItens.getRowCount() == 0) {
            Util.mensagem("Nenhum produto adicionado!");
            return;
        }

        TbVendas venda = viewBean(); // agora já pega cliente e vendedor como objetos

        GenericDAO<TbVendas> vendaDAO = new GenericDAO<>(TbVendas.class);
        vendaDAO.insert(venda);

        GenericDAO<TbVendasProdutos> itensDAO = new GenericDAO<>(TbVendasProdutos.class);
        GenericDAO<TbProdutos> prodDAO = new GenericDAO<>(TbProdutos.class);

        DefaultTableModel modelo = (DefaultTableModel) jTableItens.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            int idProduto = (int) modelo.getValueAt(i, 0);
            int quantidade = (int) modelo.getValueAt(i, 2);
            double valorUnitario = (double) modelo.getValueAt(i, 3);

            TbProdutos produto = prodDAO.findById(idProduto, "idProduto");

            TbVendasProdutos item = new TbVendasProdutos();
            item.setTbVendas(venda);
            item.setTbProdutos(produto);
            item.setQuantidade(quantidade);
            item.setValorUnitario(valorUnitario);
            item.setDesconto(0);
            item.setObservacoes("");

            itensDAO.insert(item);
        }

        Util.mensagem("Venda registrada com sucesso!");
        dispose();
    }

    // Componentes (declarados explicitamente)
    private javax.swing.JButton jBtnAddItem;
    private javax.swing.JButton jBtnDelItem;
    private javax.swing.JButton jBtnConfirmar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JComboBox<TbClientes> jCboCliente;
    private javax.swing.JComboBox<TbUsuarios> jCboVendedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableItens;
    private javax.swing.JTextField jTxtTotal;
}
