document.addEventListener('DOMContentLoaded', () => {
    fetch('/cart/state')
        .then(response => response.json())
        .then(data => {
            updateMiniCart(data);
        })
        .catch(error => console.error('Ошибка при загрузке мини-корзины:', error));
});

function addToCart(productId) {
    fetch('/cart/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `productId=${productId}`
    })
        .then(response => response.json())
        .then(data => {
            alert('Товар добавлен в корзину');
            updateMiniCart(data);
        })
        .catch(error => console.error('Ошибка при добавлении в корзину:', error));
}

function updateMiniCart(items) {
    if (!Array.isArray(items)) {
        console.error('Ошибка: items не является массивом:', items);
        return;
    }

    const miniCart = document.querySelector('.mini-cart');
    let content = '<h5>Корзина</h5>';

    if (items.length === 0) {
        content += '<p>Корзина пуста</p>';
    } else {
        items.forEach(item => {
            content += `
            <div class="d-flex justify-content-between align-items-center mb-2">
                <span>${item.product.name}</span>
                <span>${item.quantity}</span>
            </div>
        `;
        });
        content += '<a href="/cart" class="btn btn-primary btn-sm mt-3 w-100">Завершить заказ</a>';
    }

    miniCart.innerHTML = content;
}
