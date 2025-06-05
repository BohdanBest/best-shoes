@import com.bohdanbest.shoestore.security.AuthenticationInfoFacade
@param String title
@param gg.jte.Content content
@param AuthenticationInfoFacade authFacade

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link rel="stylesheet" href="/styles/styles.css">
</head>
<body>
<header>
    <div class="header-content">
        <a href="/" class="logo-link">
            <h1>👟 Best-shoes</h1>
        </a>
        <nav>
            <a href="/about">About</a>
            @if(!authFacade.isAuthenticated())
                <a href="/login">Login</a>
                <a href="/register">Sign up</a>
            @else
                @if(authFacade.isAdmin())
                    <a href="/shoe-item/add">Add Shoe</a>
                @endif
                <a href="#">Welcome, ${authFacade.getUsername()}!</a>
                <a href="/logout">Logout</a>
            @endif
        </nav>
    </div>
</header>

<main>
    ${content}
</main>

<footer>
    <p>© 2025 Best Shoes. All rights reserved.</p>
</footer>
</body>
</html>