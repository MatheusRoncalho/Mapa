package com.cesumar.biblioteca.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LivroDAO {

    private static final List<Livro> livros = new ArrayList<>();
    private static final AtomicInteger contadorId = new AtomicInteger(1);
    private static final LivroDAO instancia = new LivroDAO();

    private LivroDAO() {
        adicionarLivroInicial("Clean Code", "Robert C. Martin", 2008, "0000000000001");
        adicionarLivroInicial("Arquitetura Limpa", "Robert C. Martin", 2017, "0000000000002");
        adicionarLivroInicial("Java: Como Programar", "Paul Deitel", 2016, "0000000000003");
    }

    public static LivroDAO getInstancia() {
        return instancia;
    }

    public void adicionar(Livro livro) {
        livro.setId(contadorId.getAndIncrement());
        livros.add(livro);
    }

    public List<Livro> listarTodos() {
        return new ArrayList<>(livros);
    }

    public boolean excluirPorId(int id) {
        return livros.removeIf(livro -> livro.getId() == id);
    }

    public boolean excluirPorIsbn(String isbn) {
        return livros.removeIf(livro -> livro.getIsbn().equals(isbn));
    }

    public boolean isbnJaCadastrado(String isbn) {
        return livros.stream().anyMatch(livro -> livro.getIsbn().equals(isbn));
    }

    public Livro buscarPorIsbn(String isbn) {
        return livros.stream()
                .filter(livro -> livro.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    private void adicionarLivroInicial(String titulo, String autor, int ano, String isbn) {
        Livro livro = new Livro(contadorId.getAndIncrement(), titulo, autor, ano, isbn);
        livros.add(livro);
    }
}
