@import com.bohdanbest.shoestore.model.ShoeItemDTO
@param String title
@param java.util.List<com.bohdanbest.shoestore.model.ShoeItemDTO> inStockShoeItems
@param java.util.List<com.bohdanbest.shoestore.model.ShoeItemDTO> outOfStockShoeItems

@template.layout(
title = title,
content = @`
    <h2>Welcome to our Shoe Store!</h2>
    <p>Choose from our wide selection of quality footwear</p>

    <h3>In Stock</h3>
    <div class="shoe-list">
        @if(inStockShoeItems.isEmpty())
            <div class="no-shoes">
                <p>No shoes are available in stock at the moment.</p>
            </div>
        @else
            @for(ShoeItemDTO shoe : inStockShoeItems)
                <div class="shoe-card">
                    <div class="shoe-image">
                        <img src="${shoe.getImageUrl()}"
                             alt="${shoe.getShoeModelName()}"
                             class="shoe-img"
                             onerror="this.src='/images/shoes/placeholder.jpg'">
                    </div>
                    <div class="shoe-content">
                        <h3>${shoe.getShoeModelName()}</h3>
                        <p class="brand"><strong>Brand:</strong> ${shoe.getBrandName()}</p>
                        <p class="category"><strong>Category:</strong> ${shoe.getCategoryName()}</p>
                        <p class="size"><strong>Size:</strong> ${shoe.getSize()}</p>
                        <p class="color"><strong>Color:</strong> ${shoe.getColor()}</p>
                        <p class="price"><strong>Price:</strong> $${shoe.getPrice()}</p>
                        <div class="stock-status">
                            <span class="in-stock">✅ In Stock</span>
                        </div>
                        <a href="/shoe?id=${shoe.getId()}" class="view-details-btn">View Details</a>
                    </div>
                </div>
            @endfor
        @endif
    </div>

    <h3>Out of Stock</h3>
    <div class="shoe-list">
        @if(outOfStockShoeItems.isEmpty())
            <div class="no-shoes">
                <p>No shoes out of stock at the moment.</p>
            </div>
        @else
            @for(ShoeItemDTO shoe : outOfStockShoeItems)
                <div class="shoe-card">
                    <div class="shoe-image">
                        <img src="${shoe.getImageUrl()}"
                             alt="${shoe.getShoeModelName()}"
                             class="shoe-img"
                             onerror="this.src='/images/shoes/placeholder.jpg'">
                    </div>
                    <div class="shoe-content">
                        <h3>${shoe.getShoeModelName()}</h3>
                        <p class="brand"><strong>Brand:</strong> ${shoe.getBrandName()}</p>
                        <p class="category"><strong>Category:</strong> ${shoe.getCategoryName()}</p>
                        <p class="size"><strong>Size:</strong> ${shoe.getSize()}</p>
                        <p class="color"><strong>Color:</strong> ${shoe.getColor()}</p>
                        <p class="price"><strong>Price:</strong> $${shoe.getPrice()}</p>
                        <div class="stock-status">
                            <span class="out-of-stock">❌ Out of Stock</span>
                        </div>
                        <a href="/shoe?id=${shoe.getId()}" class="view-details-btn">View Details</a>
                    </div>
                </div>
            @endfor
        @endif
    </div>

    @template.partials.promo()
`
)