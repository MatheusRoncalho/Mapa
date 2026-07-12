package com.cesumar.biblioteca.controller;

import com.cesumar.biblioteca.model.LivroDAO;
import com.cesumar.biblioteca.model.Livro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/livros")
public class LivroServlet extends HttpServlet {

    private final LivroDAO dao = LivroDAO.getInstancia();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Livro> livros = dao.listarTodos();
        request.setAttribute("livros", livros);
        request.getRequestDispatcher("/views/listar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");

        if ("excluir".equalsIgnoreCase(acao)) {
            processarExclusao(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/livros");
        }
    }

    private void processarExclusao(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idParam   = request.getParameter("id");
        String isbnParam = request.getParameter("isbn");
        boolean excluido = false;
        String mensagem;

        if (idParam != null && !idParam.isBlank()) {
            try {
                int id = Integer.parseInt(idParam.trim());
                excluido = dao.excluirPorId(id);
                mensagem = excluido
                        ? "Livro removido com sucesso."
                        : "Nenhum livro encontrado com o ID " + id + ".";
            } catch (NumberFormatException e) {
                mensagem = "ID inválido: deve ser um número inteiro.";
            }

        } else if (isbnParam != null && !isbnParam.isBlank()) {
            String isbn = isbnParam.trim().replaceAll("[^0-9X]", "");
            if (!isIsbnValido(isbn)) {
                mensagem = "ISBN inválido. Informe 10 ou 13 dígitos numéricos.";
            } else {
                excluido = dao.excluirPorIsbn(isbn);
                mensagem = excluido
                        ? "Livro removido com sucesso."
                        : "Nenhum livro encontrado com ISBN " + isbn + ".";
            }

        } else {
            mensagem = "Informe um ID ou ISBN para excluir o livro.";
        }

        // Guarda mensagem na sessão (padrão PRG)
        request.getSession().setAttribute("mensagem", mensagem);
        request.getSession().setAttribute("sucesso", excluido);
        response.sendRedirect(request.getContextPath() + "/livros");
    }

    /** Valida formato ISBN-10 ou ISBN-13 */
    private boolean isIsbnValido(String isbn) {
        if (isbn == null) return false;
        return isbn.matches("\\d{10}") || isbn.matches("\\d{13}");
    }
}
