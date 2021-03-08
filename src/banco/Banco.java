/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
  private boolean statusConexao;
  private Connection conexao;
  private String mensagemErro;
  private String nomeDriver;
  private String nomeServidor;
  private String portaServidor;
  private String nomeUsuario;
  private String senha;
  private String nomeBanco;
  private String urlConexao;

  public Banco(String nomeServidor, String portaServidor, String nomeUsuario, String senha, String nomeBanco) {
    this.nomeServidor = nomeServidor;
    this.portaServidor = portaServidor;
    this.nomeUsuario = nomeUsuario;
    this.senha = senha;
    this.nomeBanco = nomeBanco;
    statusConexao = false;
    mensagemErro = new String();
    nomeDriver = "com.mysql.cj.jdbc.Driver";
    urlConexao = "jdbc:mysql://"+this.nomeServidor+":"+this.portaServidor+"/"+this.nomeBanco
            + "?autoReconnect=true&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
  }

  public String getNomeDriver() {
    return nomeDriver;
  }

  public void setNomeDriver(String nomeDriver) {
    this.nomeDriver = nomeDriver;
  }

  public String getNomeServidor() {
    return nomeServidor;
  }

  public void setNomeServidor(String nomeServidor) {
    this.nomeServidor = nomeServidor;
  }

  public String getPortaServidor() {
    return portaServidor;
  }

  public void setPortaServidor(String portaServidor) {
    this.portaServidor = portaServidor;
  }

  public String getNomeUsuario() {
    return nomeUsuario;
  }

  public void setNomeUsuario(String nomeUsuario) {
    this.nomeUsuario = nomeUsuario;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getNomeBanco() {
    return nomeBanco;
  }

  public void setNomeBanco(String nomeBanco) {
    this.nomeBanco = nomeBanco;
  }

  public boolean getStatusConexao() {
    return statusConexao;
  }

  public Connection getConexao() {
    return conexao;
  }

  public String getMensagemErro() {
    return mensagemErro;
  }
  
  public void realizaConexao(){
    try{
      Class.forName(this.nomeDriver);
      this.conexao = DriverManager.getConnection(this.urlConexao,this.nomeUsuario,this.senha);
      this.statusConexao = true;
    }
    catch(ClassNotFoundException e){
      mensagemErro = "Não foi possível carregar o driver";
    }
    catch(SQLException e){
      mensagemErro = "Não foi possível conectar ao banco";
    }
  }
  
  
  
  
  
}
