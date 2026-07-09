package com.cesumar.biblioteca.model;

/**
 * Classe de modelo que representa um Livro no acervo da biblioteca.
 * Segue o padrão JavaBean com atributos privados e métodos get/set.
 *
 * @author Biblioteca UniCesumar
 * @version 1.0
 */
public class Livro {

    /** Identificador único gerado automaticamente */
    private int id;

    /** Título do livro */
    private String titulo;

    /** Nome do autor principal */
    private String autor;

    /** Ano de publicação (ex: 2023) */
    private int anoPublicacao;

    /**
     * ISBN no formato 10 ou 13 dígitos (apenas números).
     * Utilizado também como chave alternativa para exclusão.
     */
    private String isbn;

    // ─── Construtores ──────────────────────────────────────────────────────────

    /** Construtor padrão exigido pelo JSF e frameworks */
    public Livro() {}

    /**
     * Construtor completo para criação de um livro com todos os campos.
     *
     * @param id            identificador único
     * @param titulo        título do livro
     * @param autor         autor do livro
     * @param anoPublicacao ano de publicação
     * @param isbn          ISBN (10 ou 13 dígitos)
     */
    public Livro(int id, String titulo, String autor, int anoPublicacao, String isbn) {
        this.id            = id;
        this.titulo        = titulo;
        this.autor         = autor;
        this.anoPublicacao = anoPublicacao;
        this.isbn          = isbn;
    }

    // ─── Getters e Setters ─────────────────────────────────────────────────────

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // ─── toString ──────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Livro{" +
                "id="            + id            +
                ", titulo='"     + titulo        + '\'' +
                ", autor='"      + autor         + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", isbn='"       + isbn          + '\'' +
                '}';
    }
}
