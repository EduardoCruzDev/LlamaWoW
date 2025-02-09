// navbar hamburguesa
document.addEventListener('DOMContentLoaded', function() {
    const hamburgerIcon = document.getElementById('hamburger-icon');
    const mobileMenu = document.getElementById('mobile-menu');

    if (hamburgerIcon && mobileMenu) {
        hamburgerIcon.addEventListener('click', function() {
            mobileMenu.classList.toggle('hidden');
        });
    }
});
