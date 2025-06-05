@import com.bohdanbest.shoestore.security.AuthenticationInfoFacade
@param String title
@param AuthenticationInfoFacade authFacade

@template.layout(
title = title,
authFacade = authFacade,
content = @`
    <h2>About Our Shoe Store</h2>
    <p>We offer the best selection of shoes for all occasions.</p>
    @template.partials.features("Free shipping", "30-day returns", "24/7 support")
`
)
