<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Livros — Biblioteca UniCesumar</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f0f4f8;
            color: #333;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        header {
            background: #1a3a5c;
            color: white;
            padding: 18px 40px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        header h1  { font-size: 1.4rem; }
        header nav a {
            color: rgba(255,255,255,0.85);
            text-decoration: none;
            margin-left: 20px;
            font-size: 0.9rem;
        }
        header nav a:hover { color: white; text-decoration: underline; }

        main { flex: 1; padding: 40px; max-width: 1100px; margin: 0 auto; width: 100%; }

        .alerta {
            padding: 14px 20px;
            border-radius: 8px;
            margin-bottom: 24px;
            font-size: 0.95rem;
            font-weight: 500;
        }
        .alerta-sucesso { background: #d4edda; color: #155724; border-left: 4px solid #28a745; }
        .alerta-erro    { background: #f8d7da; color: #721c24; border-left: 4px solid #dc3545; }

        .painel-excluir {
            background: white;
            border-radius: 10px;
            padding: 24px;
            margin-bottom: 32px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
        }
        .painel-excluir h3 { color: #1a3a5c; margin-bottom: 16px; font-size: 1rem; }
        .form-row {
            display: flex;
            gap: 12px;
            flex-wrap: wrap;
            align-items: flex-end;
        }
        .form-group { display: flex; flex-direction: column; gap: 6px; }
        .form-group label { font-size: 0.82rem; color: #555; font-weight: 600; }
        .form-group input {
            padding: 9px 12px;
            border: 1px solid #ccd;
            border-radius: 6px;
            font-size: 0.95rem;
            width: 180px;
        }
        .form-group input:focus {
            outline: none;
            border-color: #1a3a5c;
            box-shadow: 0 0 0 3px rgba(26,58,92,0.1);
        }
        .btn-excluir {
            background: #dc3545;
            color: white;
            border: none;
            padding: 9px 22px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 0.95rem;
            font-weight: 600;
        }
        .btn-excluir:hover { background: #b02a37; }

        .secao-titulo {
            font-size: 1.2rem;
            color: #1a3a5c;
            margin-bottom: 16px;
            font-weight: 700;
        }
        .badge-total {
            background: #1a3a5c;
            color: white;
            border-radius: 20px;
            padding: 2px 10px;
            font-size: 0.8rem;
            margin-left: 8px;
        }
        .tabela-wrapper {
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
            overflow: hidden;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        thead {
            background: #1a3a5c;
            color: white;
        }
        thead th {
            padding: 14px 16px;
            text-align: left;
            font-size: 0.85rem;
            font-weight: 600;
            letter-spacing: 0.5px;
        }
        tbody tr { border-bottom: 1px solid #eee; }
        tbody tr:last-child { border-bottom: none; }
        tbody tr:hover { background: #f5f8fc; }
        tbody td { padding: 14px 16px; font-size: 0.9rem; }

        .isbn-cell { font-family: monospace; color: #555; }
        .id-cell   { color: #999; font-size: 0.85rem; }

        .vazio {
            text-align: center;
            padding: 60px;
            color: #999;
            font-size: 1rem;
        }

        footer {
            background: #1a3a5c;
            color: rgba(255,255,255,0.7);
            text-align: center;
            padding: 14px;
            font-size: 0.8rem;
            margin-top: auto;
        }
    </style>
</head>
<body>

    <header>
        <h1>📚 Biblioteca UniCesumar — Livros</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/views/index.jsp">🏠 Início</a>
            <a href="${pageContext.request.contextPath}/views/cadastrar.xhtml">➕ Cadastrar Livro</a>
        </nav>
    </header>

    <main>

        <c:if test="${not empty sessionScope.mensagem}">
            <div class="alerta ${sessionScope.sucesso ? 'alerta-sucesso' : 'alerta-erro'}">
                ${sessionScope.mensagem}
            </div>
            <c:remove var="mensagem" scope="session"/>
            <c:remove var="sucesso"  scope="session"/>
        </c:if>

        <div class="painel-excluir">
            <h3>🗑️ Excluir Livro</h3>
            <form action="${pageContext.request.contextPath}/livros" method="post">
                <input type="hidden" name="acao" value="excluir"/>
                <div class="form-row">
                    <div class="form-group">
                        <label for="id">Excluir por ID</label>
                        <input type="number" id="id" name="id" placeholder="Ex: 1" min="1"/>
                    </div>
                    <div class="form-group">
                        <label for="isbn">Excluir por ISBN</label>
                        <input type="text" id="isbn" name="isbn"
                               placeholder="Ex: 9780132350884"
                               maxlength="17"/>
                    </div>
                    <button type="submit" class="btn-excluir">Excluir</button>
                </div>
            </form>
        </div>

        <p class="secao-titulo">
            Livros Cadastrados
            <span class="badge-total">${livros.size()}</span>
        </p>

        <div class="tabela-wrapper">
            <c:choose>

                <c:when test="${not empty livros}">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Título</th>
                                <th>Autor</th>
                                <th>Ano</th>
                                <th>ISBN</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="livro" items="${livros}">
                                <tr>
                                    <td class="id-cell">#${livro.id}</td>
                                    <td><strong>${livro.titulo}</strong></td>
                                    <td>${livro.autor}</td>
                                    <td>${livro.anoPublicacao}</td>
                                    <td class="isbn-cell">${livro.isbn}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>

                <c:otherwise>
                    <div class="vazio">
                        📭 Nenhum livro cadastrado no acervo.<br/>
                        <a href="${pageContext.request.contextPath}/views/cadastrar.xhtml">
                            Clique aqui para adicionar o primeiro livro.
                        </a>
                    </div>
                </c:otherwise>

            </c:choose>
        </div>

    </main>

    <footer>
        &copy; 2025 Universidade Cesumar — Sistema de Biblioteca
    </footer>

</body>
</html>
