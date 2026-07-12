package com.cesumar.biblioteca.controller.jsf;

import com.cesumar.biblioteca.model.LivroDAO;
import com.cesumar.biblioteca.model.Livro;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "livroBean")
@RequestScoped
public class LivroBean {

    private String  titulo;
    private String  autor;
    private Integer anoPublicacao;
    private String  isbn;
    private String  mensagem;
    private boolean sucesso;

    private final LivroDAO dao = LivroDAO.getInstancia();

    public String cadastrar() {

        if (isVazio(titulo) || isVazio(autor) || isVazio(isbn) || anoPublicacao == null) {
            mensagem = "Erro: todos os campos são obrigatórios.";
            sucesso  = false;
            return null;
        }

        if (titulo.trim().length() < 2) {
            mensagem = "Erro: o título deve ter pelo menos 2 caracteres.";
            sucesso  = false;
            return null;
        }

        int anoAtual = java.time.Year.now().getValue();
        if (anoPublicacao < 1000 || anoPublicacao > anoAtual) {
            mensagem = "Erro: ano inválido. Informe um valor entre 1000 e " + anoAtual + ".";
            sucesso  = false;
            return null;
        }

        String isbnLimpo = isbn.trim().replaceAll("[^0-9X]", "");
        if (!isbnLimpo.matches("\\d{10}") && !isbnLimpo.matches("\\d{13}")) {
            mensagem = "Erro: ISBN inválido. Informe 10 ou 13 dígitos (sem hifens).";
            sucesso  = false;
            return null;
        }

        if (dao.isbnJaCadastrado(isbnLimpo)) {
            mensagem = "Erro: já existe um livro com esse ISBN (" + isbnLimpo + ").";
            sucesso  = false;
            return null;
        }

        Livro novoLivro = new Livro();
        novoLivro.setTitulo(titulo.trim());
        novoLivro.setAutor(autor.trim());
        novoLivro.setAnoPublicacao(anoPublicacao);
        novoLivro.setIsbn(isbnLimpo);
        dao.adicionar(novoLivro);

        mensagem = "Livro \"" + novoLivro.getTitulo() + "\" cadastrado! (ID: " + novoLivro.getId() + ")";
        sucesso  = true;
        limpar();
        return null;
    }

    private void limpar() {
        titulo = null; autor = null; anoPublicacao = null; isbn = null;
    }

    private boolean isVazio(String v) {
        return v == null || v.trim().isEmpty();
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String t) { this.titulo = t; }
    public String getAutor() { return autor; }
    public void setAutor(String a) { this.autor = a; }
    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer a) { this.anoPublicacao = a; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String i) { this.isbn = i; }
    public String getMensagem() { return mensagem; }
    public boolean isSucesso() { return sucesso; }
}
