@import com.bohdanbest.shoestore.security.AuthenticationInfoFacade
@param String title
@param com.bohdanbest.shoestore.model.ShoeItemDTO shoe
@param AuthenticationInfoFacade authFacade

@template.layout(
title = title,
authFacade = authFacade,
content = @`
    @if(shoe == null)
        <div class="error-message">
            <h2>Shoe not found</h2>
            <p>Sorry, the shoe you're looking for doesn't exist.</p>
            <a href="/" class="back-button">← Back to Home</a>
        </div>
    @else
        <div class="shoe-details">
            <div class="shoe-details-container">
                <div class="shoe-image-large">
                    <img src="${shoe.getImageUrl()}"
                         alt="${shoe.getShoeModelName()}"
                         class="shoe-img-large"
                         onerror="this.src='/images/shoes/placeholder.jpg'">
                </div>
                <div class="shoe-info">
                    <h1>${shoe.getShoeModelName()}</h1>
                    <div class="shoe-meta">
                        <p><strong>Brand:</strong> <span class="brand-name">${shoe.getBrandName()}</span></p>
                        <p><strong>Category:</strong> <span class="category-name">${shoe.getCategoryName()}</span></p>
                        <p><strong>Size:</strong> <span class="size">${shoe.getSize()}</span></p>
                        <p><strong>Color:</strong> <span class="color">${shoe.getColor()}</span></p>
                        <p><strong>Description:</strong>
                            @if(shoe.getDescription() != null && !shoe.getDescription().isEmpty())
                                ${shoe.getDescription()}
                            @else
                                No description available.
                            @endif
                        </p>
                        <p class="price-large"><strong>Price:</strong> <span class="price-value">$${shoe.getPrice()}</span></p>

                        <div class="availability-section">
                            <h3>Availability</h3>
                            @if(shoe.isInStock())
                                <p class="availability in-stock">
                                    <span class="status-icon">✅</span>
                                    <span class="status-text">In Stock—Ready to ship!</span>
                                </p>
                            @else
                                <p class="availability out-of-stock">
                                    <span class="status-icon">❌</span>
                                    <span class="status-text">Currently out of stock</span>
                                </p>
                            @endif
                            @if(authFacade.isAuthenticated() && authFacade.isAdmin())
                                <a href="/shoe-item/edit?id=${shoe.getId()}" class="edit-button">Edit</a>
                            @endif
                        </div>

                        <div class="actions">
                            @if(shoe.isInStock())
                                <button class="add-to-cart-btn">Add to Cart</button>
                            @endif
                            <a href="/" class="back-button">← Back to Home</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    @endif
`
)