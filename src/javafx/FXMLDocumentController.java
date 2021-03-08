/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import banco.Banco;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import modelo.Pessoa;

public class FXMLDocumentController implements Initializable {
    
    private Banco banco;
    private ResultSet listaPessoas;
  
    public void carregaLista(){
        Pessoa pessoa = new Pessoa(banco.getConexao());
        try{
            listaPessoas = pessoa.obterLista();
        }
        catch(SQLException e){
            
        }
    }
    
    @FXML
    private Button enviar;

    @FXML
    private Label mensagem;

    @FXML
    private TextField nome;

    @FXML
    private DatePicker nascimento;

    @FXML
    private TextField email;

    @FXML
    private TextField telefone;

    @FXML
    private TextField cpf;

    @FXML
    private ComboBox<String> sangue;
    
    @FXML
    void cadastrar(ActionEvent event) throws SQLException {
        banco = new Banco("localhost", "3306", "Poliana", "poliana", "cadastro");
        banco.realizaConexao();
        if (!banco.getStatusConexao()) {
            
        }
        Pessoa pessoa = new Pessoa(banco.getConexao());
        String n = nome.getText();
        LocalDate nasc = nascimento.getValue();
        String em = email.getText();
        String tel = telefone.getText();
        String c = cpf.getText();
        String tSangue = sangue.getSelectionModel().getSelectedItem();
        pessoa.setNome(n);
        pessoa.setNascimento(nasc);
        pessoa.setEmail(em);
        pessoa.setTelefone(tel);
        pessoa.setCpf(c);
        pessoa.setSangue(tSangue);
        if (pessoa.cadastro()) {
            mensagem.setText("Cadastro realizado com sucesso!");
        }
        else
            mensagem.setText("Cadastro não realizado!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> tipo = FXCollections.observableArrayList();
        tipo.add("A+");
        tipo.add("A-");
        tipo.add("B+");
        tipo.add("B-");
        tipo.add("AB+");
        tipo.add("AB-");
        tipo.add("O+");
        tipo.add("O-");
        sangue.setItems(tipo);
    }    
    
}
