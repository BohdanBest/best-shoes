@template.footer()
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Інтернет-магазин взуття</title>
    <link rel="stylesheet" href="../static/styles.css">
</head>
<body>
<header class="header">
    <div class="container">
        <h1>${message}</h1>
    </div>
</header>

<main class="main">
    <div class="container">
        <div class="welcome-section">
            <p class="welcome-text">Знайдіть ідеальне взуття для себе!</p>
            <a href="/templates/shoes" class="btn">Переглянути каталог взуття</a>
        </div>
    </div>
</main>

@template.footer()
</body>
</html>