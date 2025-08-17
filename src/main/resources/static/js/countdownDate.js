document.addEventListener('DOMContentLoaded', () => {

    const countdownDate = new Date('2025-12-31T23:59:59');

    const daysEl = document.getElementById('days');
    const hoursEl = document.getElementById('hours');
    const minutesEl = document.getElementById('minutes');
    const secondsEl = document.getElementById('seconds');
    const countdownDiv = document.getElementById('countdown');

    function updateCountdown() {
        const now = new Date();
        const diff = countdownDate - now;

        if (diff <= 0) {
            countdownDiv.textContent = 'Server Online';
            clearInterval(intervalId);
            return;
        }

        const days = Math.floor(diff / (1000 * 60 * 60 * 24));
        const hours = Math.floor((diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutes = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
        const seconds = Math.floor((diff % (1000 * 60)) / 1000);

        daysEl.textContent = days;
        hoursEl.textContent = hours;
        minutesEl.textContent = minutes;
        secondsEl.textContent = seconds;
    }

    updateCountdown();
    const intervalId = setInterval(updateCountdown, 1000);
});
