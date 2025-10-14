package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaPesquisaDialog extends JDialog {

    // Componentes
    private JTable jTable1;
    private JTextField jTxtPesquisa;
    private JButton jBtnOk;
    private JLabel jLabelPesquisa;
    private JScrollPane jScrollPane1;

    // Tela pai genérica
    private Window telaPai;

    // Construtor
    public TelaPesquisaDialog(Window parent, boolean modal) {
        super(parent, modal ? ModalityType.APPLICATION_MODAL : ModalityType.MODELESS);
        this.telaPai = parent;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Resultados da Pesquisa");
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jBtnOk = new JButton();
        jTxtPesquisa = new JTextField();
        jLabelPesquisa = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jLabelPesquisa.setText("Pesquisar:");

        jTxtPesquisa.setToolTipText("Digite aqui, mas não faz nada");

        // Tabela com dados de exemplo
        jTable1.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{"Coluna 1", "Coluna 2", "Coluna 3", "Coluna 4"}
        ));

        jScrollPane1.setViewportView(jTable1);

        jBtnOk.setText("OK");
        jBtnOk.addActionListener(e -> dispose()); // Só fecha

        // Layout simples
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelPesquisa)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTxtPesquisa, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jBtnOk, GroupLayout.Alignment.TRAILING))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelPesquisa)
                                        .addComponent(jTxtPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnOk)
                                .addContainerGap())
        );

        pack();
    }

    public Window getTelaPai() {
        return telaPai;
    }
}
