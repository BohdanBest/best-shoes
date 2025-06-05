@import com.bohdanbest.shoestore.model.LoginDTO
@import com.bohdanbest.shoestore.security.AuthenticationInfoFacade
@param String title
@param LoginDTO loginDTO
@param String error
@param String message
@param AuthenticationInfoFacade authFacade

@template.layout(
title = title,
authFacade = authFacade,
content = @`
    <h2>Login</h2>
    <div class="login-form form">
        @if(error != null)
            <p class="error">${error}</p>
        @endif
        @if(message != null)
            <p class="success">${message}</p>
        @endif
        <form action="/login" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required
                       value="${loginDTO != null ? loginDTO.getUsername() : ""}">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="login-btn btn">Login</button>
        </form>
        <p>Don't have an account? <a href="/register">Register here</a></p>
    </div>
`
)