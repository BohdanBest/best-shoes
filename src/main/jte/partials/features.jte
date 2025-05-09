@param String... features

<div class="features">
    <h3>Our Features:</h3>
    <ul>
        @for(String feature : features)
            <li>${feature}</li>
        @endfor
    </ul>
</div>