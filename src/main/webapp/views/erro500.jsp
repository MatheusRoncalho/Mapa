<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Erro interno — Biblioteca UniCesumar</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background:#f0f4f8; display:flex;
               justify-content:center; align-items:center; height:100vh; margin:0; }
        .box { background:white; padding:50px; border-radius:12px; text-align:center;
               box-shadow:0 4px 20px rgba(0,0,0,.1); border-top:4px solid #ffc107; }
        .box h1 { font-size:4rem; color:#ffc107; }
        .box h2 { color:#333; margin:12px 0; }
        .box p  { color:#666; margin-bottom:24px; }
        .box a  { background:#1a3a5c; color:white; padding:10px 24px; border-radius:7px;
                  text-decoration:none; font-weight:600; }
    </style>
</head>
<body>
    <div class="box">
        <h1>500</h1>
        <h2>Erro interno do servidor</h2>
        <p>Ocorreu um problema inesperado. Tente novamente mais tarde.</p>
        <a href="${pageContext.request.contextPath}/index.jsp">← Voltar ao Início</a>
    </div>
</body>
</html>
