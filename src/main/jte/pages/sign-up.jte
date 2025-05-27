@param String title
@param com.bohdanbest.shoestore.model.UserSignUpDTO userSignUpDTO

@template.layout(
title = title,
content = @`
    <h2>Register</h2>
    <div class="register-form form">
        <form action="/api/register" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required
                       value="${userSignUpDTO != null ? userSignUpDTO.getUsername() : ""}">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required
                       value="${userSignUpDTO != null ? userSignUpDTO.getEmail() : ""}">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            <div class="form-group">
                <label for="role">Role:</label>
                <select id="role" name="role" required>
                    <option value="USER ${userSignUpDTO != null && userSignUpDTO.getRole() == "USER" ? "selected" : ""}">User</option>
                    <option value="ADMIN ${userSignUpDTO != null && userSignUpDTO.getRole() == "ADMIN" ? "selected" : ""}">Admin</option>
                </select>
            </div>
            <button type="submit" class="register-btn btn">Register</button>
        </form>
        <p>Already have an account? <a href="/login">Login here</a></p>
    </div>
`
)