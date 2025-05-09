@import com.bohdanbest.shoestore.model.ShoeModel
@param String title
@param java.util.List<com.bohdanbest.shoestore.model.ShoeModel> shoes


@template.layout(
title = title,
content = @`
    <h2>Welcome to our Shoe Store!</h2>

    <div class="shoe-list">
        @if(shoes.isEmpty())
            <p>No shoes available at the moment.</p>
        @else
            @for(ShoeModel shoe : shoes)
                <div class="shoe-card">
                    <h3>${shoe.name}</h3>
                    <p>Brand: ${shoe.brand}</p>
                    <p>Price: $${shoe.price}</p>
                    <a href="/shoe?id=${shoe.id}">View Details</a>
                </div>
            @endfor
        @endif
    </div>
    @template.partials.promo(discount = 15)
`
)