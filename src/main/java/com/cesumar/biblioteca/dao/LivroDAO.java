package com.cesumar.biblioteca.dao;

import com.cesumar.biblioteca.model.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classe DAO (Data Access Object) responsável por gerenciar o acervo de livros.
 *
 * Utiliza armazenamento em memória (List estática) para simular uma camada
 * de persistência. Em um projeto real, essa classe seria substituída por
 * operações com banco de dados via JDBC ou JPA.
 *
 * O padrão Singleton garante que todos os Servlets e Beans JSF compartilhem
 * o mesmo acervo durante a execução do servidor.
 *
 * @author Biblioteca UniCesumar
 * @version 1.0
 */
public class LivroDAO {

    /** Lista estática que simula o armazenamento dos livros em memória */
    private static final List<Livro> acervo = new ArrayList<>();

    /** Gerador de IDs sequenciais e thread-safe */
    private static final AtomicInteger contadorId = new AtomicInteger(1);

    /** Instância única do DAO (Singleton) */
    private static final LivroDAO instancia = new LivroDAO();

    // ─── Singleton ─────────────────────────────────────────────────────────────

    /** Construtor privado — impede instanciação externa */
    private LivroDAO() {
        // Dados iniciais para demonstração do sistema
        adicionarLivroInicial("Clean Code", "Robert C. Martin", 2008, "9780132350884");
        adicionarLivroInicial("Arquitetura Limpa", "Robert C. Martin", 2017, "9780134494166");
        adicionarLivroInicial("Java: Como Programar", "Paul Deitel", 2016, "9788543004792");
    }

    /**
     * Retorna a instância única do DAO.
     *
     * @return instância singleton de LivroDAO
     */
    public static LivroDAO getInstancia() {
        return instancia;
    }

    // ─── Operações CRUD ────────────────────────────────────────────────────────

    /**
     * Adiciona um livro ao acervo, atribuindo-lhe um ID automático.
     *
     * @param livro objeto Livro a ser adicionado (sem ID)
     */
    public void adicionar(Livro livro) {
        livro.setId(contadorId.getAndIncrement());
        acervo.add(livro);
    }

    /**
     * Retorna uma cópia da lista de todos os livros cadastrados.
     *
     * @return lista de livros no acervo
     */
    public List<Livro> listarTodos() {
        return new ArrayList<>(acervo);
    }

    /**
     * Remove um livro do acervo pelo seu ID.
     *
     * @param id identificador do livro a ser excluído
     * @return true se o livro foi encontrado e removido, false caso contrário
     */
    public boolean excluirPorId(int id) {
        return acervo.removeIf(livro -> livro.getId() == id);
    }

    /**
     * Remove um livro do acervo pelo seu ISBN.
     *
     * @param isbn ISBN do livro a ser excluído
     * @return true se o livro foi encontrado e removido, false caso contrário
     */
    public boolean excluirPorIsbn(String isbn) {
        return acervo.removeIf(livro -> livro.getIsbn().equals(isbn));
    }

    /**
     * Verifica se já existe um livro com o ISBN informado.
     * Evita duplicatas no acervo.
     *
     * @param isbn ISBN a verificar
     * @return true se o ISBN já está cadastrado
     */
    public boolean isbnJaCadastrado(String isbn) {
        return acervo.stream().anyMatch(livro -> livro.getIsbn().equals(isbn));
    }

    /**
     * Busca um livro pelo seu ISBN.
     *
     * @param isbn ISBN do livro a ser buscado
     * @return o Livro encontrado, ou null se não existir
     */
    public Livro buscarPorIsbn(String isbn) {
        return acervo.stream()
                .filter(livro -> livro.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    // ─── Método auxiliar privado ───────────────────────────────────────────────

    /** Adiciona livros de demonstração sem expor o método publicamente */
    private void adicionarLivroInicial(String titulo, String autor, int ano, String isbn) {
        Livro livro = new Livro(contadorId.getAndIncrement(), titulo, autor, ano, isbn);
        acervo.add(livro);
    }
}
