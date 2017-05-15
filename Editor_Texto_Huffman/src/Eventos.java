
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Eventos implements ActionListener {

    SetGet s = new SetGet();
    TelaPrincipal telaprincipal;
    File file2;

    public Eventos(TelaPrincipal telaprincipal) {
        this.telaprincipal = telaprincipal;
    }

    public void Abrir() throws IOException {
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter arquivo = new FileNameExtensionFilter("Arquivo de Texto", "txt");
        file.setFileFilter(arquivo);
        int retorno = file.showOpenDialog(telaprincipal);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            file2 = new File(file.getSelectedFile().getAbsolutePath());
            telaprincipal.N_A(file2);

            try {
                FileReader fis = new FileReader(file2);
                BufferedReader bis = new BufferedReader(fis);

                String linha = bis.readLine();

                while (linha != null) {
                    telaprincipal.jTextArea.append(linha + "\n");
                    linha = bis.readLine();

                }
                bis.close();
                fis.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }

        }

    }

    public void salvarcomo() {
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter arquivo = new FileNameExtensionFilter("Arquivo de Texto", "txt");
        file.setFileFilter(arquivo);
        int retorno = file.showSaveDialog(telaprincipal);
        try {
            FileWriter escreve;
            escreve = new FileWriter(file.getSelectedFile().getAbsolutePath() + ".txt");
            escreve.write(telaprincipal.jTextArea.getText());
            escreve.close();
            file2 = new File(file.getSelectedFile().getAbsolutePath() + ".txt");
            telaprincipal.N_A(file2);
        } catch (Exception erro) {

        }
    }

    public void salvar() throws IOException {

        if (file2 == null) {
            salvarcomo();

        } else {

            FileWriter arq = new FileWriter(file2);

            PrintWriter gravar = new PrintWriter(arq);
            gravar.printf(telaprincipal.jTextArea.getText());

            gravar.close();
        }
    }

    public void novo() {
        s.setArea(telaprincipal.jTextArea.getText());
        if (s.area.isEmpty()) {
            return;
        }

        int escolha = JOptionPane.showConfirmDialog(null, "Deseja criar novo Texto ? Att : voce perderá tudo que foi "
                + "\nDigitado até o momento.");

        if (escolha == 0) {
            telaprincipal.jTextArea.setText("");
            file2 = null;
            telaprincipal.N_A(file2);
        }
    }

    public void sair() {
        s.setArea(telaprincipal.jTextArea.getText());
        if (s.area.isEmpty()) {
            System.exit(0);

        } else {
            int escolha = JOptionPane.showConfirmDialog(null, "Voce esta perto de encerrar o seu programa Att : voce perderá tudo que foi "
                    + "\nDigitado até o momento.");

            if (escolha == 0) {
                System.exit(0);

            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        if ("sair".equals(e.getActionCommand())) {
            sair();

        }
        if ("novo".equals(e.getActionCommand()) || "btnovo".equals(e.getActionCommand())) {

            novo();
            file2 = null;
        }

        if ("abrir".equals(e.getActionCommand()) || "btabrir".equals(e.getActionCommand())) {
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
        if ("salvarcomo".equals(e.getActionCommand()) || "btsalvacomo".equals(e.getActionCommand())) {
            file2 = null;
            salvarcomo();

        }
        if ("salvar".equals(e.getActionCommand()) || "btsalvar".equals(e.getActionCommand())) {
            try {
                salvar();
            } catch (IOException ex) {
                Logger.getLogger(Eventos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
