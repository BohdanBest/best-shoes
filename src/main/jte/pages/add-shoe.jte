@import com.bohdanbest.shoestore.entity.Category
@import com.bohdanbest.shoestore.security.AuthenticationInfoFacade
@param String title
@param java.util.List<Category> categories
@param AuthenticationInfoFacade authFacade
@param String error
@param String success

@template.layout(
title = title,
authFacade = authFacade,
content = @`
    <h2>Add New Shoe Item</h2>
    <div class="add-shoe-form form">
        @if(error != null)
            <div class="error-message">
                <p>${error}</p>
            </div>
        @endif
        @if(success != null)
            <div class="success-message">
                <p>${success}</p>
            </div>
        @endif
        <form action="/api/shoe-item/add" method="post">
            <div class="form-group">
                <label for="brandName">Brand Name:</label>
                <input type="text" id="brandName" name="brandName" required placeholder="e.g., Nike">
            </div>
            <div class="form-group">
                <label for="modelName">Model Name:</label>
                <input type="text" id="modelName" name="modelName" required placeholder="e.g., Air Max">
            </div>
            <div class="form-group">
                <label for="categoryId">Category:</label>
                <select id="categoryId" name="categoryId" required>
                    <option value="" disabled selected>Select a category</option>
                    @if(categories.isEmpty())
                        <option value="" disabled>No categories available</option>
                    @else
                        @for(Category category : categories)
                            <option value="${category.getId()}">${category.getName()}</option>
                        @endfor
                    @endif
                </select>
            </div>
            <div class="form-group">
                <label for="description">Model Description (optional):</label>
                <textarea id="description" name="description" placeholder="e.g., Lightweight running shoes"></textarea>
            </div>
            <div class="form-group">
                <label for="size">Size (e.g., EU 42, US 8):</label>
                <input type="text" id="size" name="size" required placeholder="e.g., EU 42">
            </div>
            <div class="form-group">
                <label for="color">Color:</label>
                <input type="text" id="color" name="color" required placeholder="e.g., Black">
            </div>
            <div class="form-group">
                <label for="price">Price ($):</label>
                <input type="number" id="price" name="price" step="0.01" min="0.01" required>
            </div>
            <div class="form-group">
                <label for="quantityInStock">Quantity in Stock:</label>
                <input type="number" id="quantityInStock" name="quantityInStock" min="0" required>
            </div>
            <div class="form-group">
                <label for="imageUrl">Image URL (optional):</label>
                <input type="url" id="imageUrl" name="imageUrl" placeholder="https://example.com/image.jpg">
            </div>
            <button type="submit" class="add-btn btn">Add Shoe Item</button>
        </form>
    </div>
`
)