@import com.bohdanbest.shoestore.model.ShoeItemDTO
@import com.bohdanbest.shoestore.entity.Category
@import com.bohdanbest.shoestore.security.AuthenticationInfoFacade
@param String title
@param ShoeItemDTO shoe
@param java.util.List<Category> categories
@param AuthenticationInfoFacade authFacade
@param String error

@template.layout(
title = title,
authFacade = authFacade,
content = @`
    <h2>Edit Shoe Item</h2>
    @if(error != null)
        <div class="error-message">${error}</div>
    @endif

    <form action="/api/shoe-item/edit" method="POST">
        <input type="hidden" name="id" value="${shoe.getId()}">
        <div class="form-group">
            <label for="brandName">Brand Name:</label>
            <input type="text" id="brandName" name="brandName" value="${shoe.getBrandName()}" required>
        </div>
        <div class="form-group">
            <label for="modelName">Model Name:</label>
            <input type="text" id="modelName" name="modelName" value="${shoe.getShoeModelName()}" required>
        </div>
        <div class="form-group">
            <label for="categoryId">Category:</label>
            <select id="categoryId" name="categoryId" required>
                @for(Category category : categories)
                    @if(category.getName().equals(shoe.getCategoryName()))
                        <option value="${category.getId()}" selected>${category.getName()}</option>
                    @else
                        <option value="${category.getId()}">${category.getName()}</option>
                    @endif
                @endfor
            </select>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description">${shoe.getDescription() != null ? shoe.getDescription() : ""}</textarea>
        </div>
        <div class="form-group">
            <label for="size">Size:</label>
            <input type="text" id="size" name="size" value="${shoe.getSize()}" required>
        </div>
        <div class="form-group">
            <label for="color">Color:</label>
            <input type="text" id="color" name="color" value="${shoe.getColor()}" required>
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" step="0.01" id="price" name="price" value="${shoe.getPrice()}" required>
        </div>
        <div class="form-group">
            <label for="quantityInStock">Quantity in Stock:</label>
            <input type="number" id="quantityInStock" name="quantityInStock" value="${shoe.isInStock() ? String.valueOf(shoe.getQuantityInStock()) : "0"}" required>
        </div>
        <div class="form-group">
            <label for="imageUrl">Image URL:</label>
            <input type="text" id="imageUrl" name="imageUrl" value="${shoe.getImageUrl() != null ? shoe.getImageUrl() : ""}">
        </div>
        <button type="submit" class="btn save">Save Changes</button>
        <a href="/shoe?id=${shoe.getId()}" class="btn cancel">Cancel</a>
    </form>
`
)