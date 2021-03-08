
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class Pessoa {
  private int id;
  private String nome;
  private LocalDate nascimento;
  private String email;
  private String telefone;
  private String cpf;
  private String sangue;
  private Connection conexao;

  public Pessoa() {
  }
  
  public Pessoa(Connection conexao){
    this.conexao = conexao;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDate getNascimento() {
    return nascimento;
  }

  public void setNascimento(LocalDate nascimento) {
    this.nascimento = nascimento;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  
  public String getSangue() {
    return sangue;
  }

  public void setSangue(String sangue) {
    this.sangue = sangue;
  }
  
  public void setConexao(Connection conexao) {
    this.conexao = conexao;
  }
  
  public ResultSet obterLista() throws SQLException{
    String sql = "select id, nome, nascimento, email, telefone, cpf, sangue from pessoa";
    PreparedStatement requisicao = this.conexao.prepareStatement(sql);
    return requisicao.executeQuery();
  }
  
  public boolean cadastro() throws SQLException{
    String sql = "insert into pessoa (nome, nascimento, email, telefone, cpf, sangue) values(?, ?, ?, ?, ?, ?)";
    PreparedStatement requisicao = this.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    requisicao.setString(1, this.nome);
    java.util.Date teste = Date.from(this.nascimento.atStartOfDay(ZoneId.systemDefault()).toInstant());
    java.sql.Date d = new java.sql.Date (teste.getTime());
    requisicao.setDate(2, d);
    requisicao.setString(3, this.email);
    requisicao.setString(4, this.telefone);
    requisicao.setString(5, this.cpf);
    requisicao.setString(6, this.sangue);
    requisicao.executeUpdate();
    return true;
  }
}
