@import com.bohdanbest.shoestore.model.ShoeItemDTO
@import com.bohdanbest.shoestore.security.AuthenticationInfoFacade
@param String title
@param java.util.List<com.bohdanbest.shoestore.model.ShoeItemDTO> inStockShoeItems
@param java.util.List<com.bohdanbest.shoestore.model.ShoeItemDTO> outOfStockShoeItems
@param AuthenticationInfoFacade authFacade
@param String success
@param String sort
@param String query

@template.layout(
title = title,
authFacade = authFacade,
content = @`
    <h2>Welcome to our Shoe Store!</h2>
    <p>Choose from our wide selection of quality footwear</p>
    <div class="filter-container">
        <form id="searchForm" action="/" method="GET">
            <input type="text" id="query" name="query" placeholder="Search by model name..." value="${query != null ? query : ""}">
            @if(sort != null)
                <input type="hidden" name="sort" value="${sort}">
            @endif
        </form>
        <form id="sortForm" action="/" method="GET">
            <label for="sort">Sort by:</label>
            <select id="sort" name="sort" onchange="this.form.submit()">
                <option value="" selected="${sort == null || sort.isEmpty()}">Default</option>
                <option value="price_asc" selected="${sort != null && sort.equals("price_asc")}">Price: Low to High</option>
                <option value="price_desc" selected="${sort != null && sort.equals("price_desc")}">Price: High to Low</option>
            </select>
            @if(query != null)
                <input type="hidden" name="query" value="${query}">
            @endif
        </form>
    </div>
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
                        <div class="actions">
                            <a href="/shoe?id=${shoe.getId()}" class="view-details-btn">View Details</a>
                            @if(authFacade.isAuthenticated() && authFacade.isAdmin())
                                <button class="delete-btn" onclick="openDeleteModal('${shoe.getId()}', '${shoe.getShoeModelName()}')">Delete</button>
                            @endif
                        </div>
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
                       <div class="actions">
                        <a href="/shoe?id=${shoe.getId()}" class="view-details-btn">View Details</a>

                        @if(authFacade.isAuthenticated() && authFacade.isAdmin())
                            <button class="delete-btn" onclick="openDeleteModal('${shoe.getId()}', '${shoe.getShoeModelName()}')">Delete</button>
                        @endif
                       </div>
                    </div>
                </div>
            @endfor
        @endif
    </div>
    <div id="deleteModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeDeleteModal()">&times;</span>
            <h3>Confirm Deletion</h3>
            <p>Are you sure you want to delete <span id="shoeName"></span>?</p>
            <form id="deleteForm" action="/api/shoe-item/delete" method="POST">
                <input type="hidden" name="id" id="deleteShoeId">
                <button type="submit" class="btn confirm">Yes, Delete</button>
                <button type="button" class="btn cancel" onclick="closeDeleteModal()">Cancel</button>
            </form>
        </div>
    </div>
    @if(success != null)
        <div id="toast" class="toast">${success}</div>
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                const toast = document.getElementById('toast');
                if (toast) {
                    toast.classList.add('show');
                    setTimeout(() => {
                        toast.classList.remove('show');
                        setTimeout(() => toast.remove(), 500);
                    }, 3000);
                }
            });
        </script>
    @endif
    <script>
        function openDeleteModal(id, name) {
            document.getElementById('deleteModal').style.display = 'block';
            document.getElementById('shoeName').textContent = name;
            document.getElementById('deleteShoeId').value = id;
        }
        function closeDeleteModal() {
            document.getElementById('deleteModal').style.display = 'none';
            document.getElementById('shoeName').textContent = '';
            document.getElementById('deleteShoeId').value = '';
        }
        document.addEventListener('DOMContentLoaded', function() {
            const toast = document.getElementById('toast');
            if (toast) {
                toast.classList.add('show');
                setTimeout(() => {
                    toast.classList.remove('show');
                    setTimeout(() => toast.remove(), 500);
                }, 3000);
            }
        });
    </script>

    @template.partials.promo()
`
)