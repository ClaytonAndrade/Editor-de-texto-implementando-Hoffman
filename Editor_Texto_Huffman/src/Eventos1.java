
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Carlos Fernandes
 */
public class Eventos1 extends Eventos implements ActionListener {

    public Eventos1(TelaPrincipal telaprincipal) {
        super(telaprincipal);
    }

    public void actionPerformed(ActionEvent e) {
        if ("btnovo".equals(e.getActionCommand())) {
            novo();
            file2 = null;

        }
        if ("btabrir".equals(e.getActionCommand())) {
            file2 = null;
            try {
                s.setArea(telaprincipal.jTextArea.getText());
                if (s.area.isEmpty()) {
                    Abrir();
                } else {
                    int escolha = JOptionPane.showConfirmDialog(null, "Deseja Abrir novo Texto ? Att : Salve para vocé não Perder os seu Dados "
                            + "\nDigitado até o momento."
                            + "\nDeseja Continuar");

                    if (escolha == 0) {
                        telaprincipal.jTextArea.setText("");
                        Abrir();
                    }
                }
            } catch (IOException ex) {

            }
        }
        if ("btsalvar".equals(e.getActionCommand())) {
            try {
                salvar();
            } catch (IOException ex) {
                Logger.getLogger(Eventos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if ("btsalvacomo".equals(e.getActionCommand())) {
            file2 = null;
            salvarcomo();
        }
        if ("btcompactar".equals(e.getActionCommand())) {
        }
    }

}
