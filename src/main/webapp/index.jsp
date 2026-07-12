<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biblioteca UniCesumar</title>
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
            padding: 20px 40px;
            display: flex;
            align-items: center;
            gap: 16px;
        }
        header h1 { font-size: 1.6rem; }
        header p  { font-size: 0.9rem; opacity: 0.8; margin-top: 2px; }

        main {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 60px 20px;
        }

        .card-container {
            display: flex;
            gap: 30px;
            flex-wrap: wrap;
            justify-content: center;
        }

        .card {
            background: white;
            border-radius: 12px;
            padding: 40px 32px;
            width: 240px;
            text-align: center;
            box-shadow: 0 4px 16px rgba(0,0,0,0.1);
            text-decoration: none;
            color: inherit;
            transition: transform 0.2s, box-shadow 0.2s;
            border-top: 4px solid #1a3a5c;
        }
        .card:hover {
            transform: translateY(-6px);
            box-shadow: 0 8px 24px rgba(0,0,0,0.15);
        }
        .card .icone { font-size: 3rem; margin-bottom: 16px; }
        .card h2    { font-size: 1.1rem; color: #1a3a5c; margin-bottom: 8px; }
        .card p     { font-size: 0.85rem; color: #666; line-height: 1.5; }

        footer {
            background: #1a3a5c;
            color: rgba(255,255,255,0.7);
            text-align: center;
            padding: 16px;
            font-size: 0.8rem;
        }
    </style>
</head>
<body>

    <header>
        <div>
            <h1>📚 Biblioteca UniCesumar</h1>
            <p>Sistema de Gerenciamento de livros</p>
        </div>
    </header>

    <main>
        <div class="card-container">

            <a href="${pageContext.request.contextPath}/cadastrar.xhtml" class="card">
                <div class="icone">➕</div>
                <h2>Cadastrar Livro</h2>
                <p>Adicione um novo livro para lista da biblioteca.</p>
            </a>

            <a href="${pageContext.request.contextPath}/livros" class="card">
                <div class="icone">📋</div>
                <h2>Listar Livros</h2>
                <p>Visualize e gerencie todos os livros cadastrados.</p>
            </a>

        </div>
    </main>

    <footer>
        &copy; 2025 Universidade Cesumar — Sistema de Biblioteca
    </footer>

</body>
</html>
