/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author u1845853
 */
public class Util {
    public static void habilitar(boolean valor, JComponent ... componentes) {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].setEnabled(valor);           
        }
    }
    
    public static void limpar(JComponent ... componentes){
        for (JComponent comp : componentes) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setText("");
            } else if (comp instanceof javax.swing.JPasswordField) {
                ((javax.swing.JPasswordField) comp).setText("");
            } else if (comp instanceof javax.swing.JComboBox) {
                ((javax.swing.JComboBox<?>) comp).setSelectedIndex(-1);
            } else if (comp instanceof javax.swing.JFormattedTextField) {
                ((javax.swing.JFormattedTextField) comp).setValue(null);
            } else if (comp instanceof javax.swing.JCheckBox) {
                ((javax.swing.JCheckBox) comp).setSelected(false);
            }
        }
    }
    
    public static void mensagem(String cad){
        JOptionPane.showMessageDialog(null, cad );
    }
    
    public static boolean perguntar(String cad){
        int resposta = JOptionPane.showConfirmDialog(
            null,
            cad,
            "Confirmação",
            JOptionPane.YES_NO_OPTION
        );
        return resposta == JOptionPane.YES_OPTION;
    }
    
    public static int strToInt(String cad) {
        return Integer.valueOf(cad);
    }
    
    public static String intToStr(int num) {
        return String.valueOf(num);
    }

    public static double strToDouble(String cad) {
        try {
            return Double.parseDouble(cad.replace(",", "."));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static String doubleToStr(double num) {
        return String.valueOf(num);
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static Date strToDate(String cad) {
        try {
            return sdf.parse(cad);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateToStr(Date data) {
        if (data == null) return "";
        return sdf.format(data);
    }
    
}
