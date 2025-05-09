@param String title
@param com.bohdanbest.shoestore.model.ShoeModel shoe

@template.layout(
title = title,
content = @`
    @if(shoe == null)
        <p>Shoe not found</p>
    @else
        <div class="shoe-details">
            <h2>${shoe.getName()}</h2>
            <p><strong>Brand:</strong> ${shoe.getBrand()}</p>
            <p><strong>Price:</strong> $${shoe.getPrice()}</p>
            <p><strong>Color:</strong> ${shoe.getColor()}</p>
            <p><strong>Size:</strong> ${shoe.getSize()}</p>
            <p><strong>Category:</strong> ${shoe.getCategory()}</p>
            <p><strong>In Stock:</strong> ${shoe.getInStock()}</p>
            <a href="/">Back to Home</a>
        </div>
    @endif
`
)