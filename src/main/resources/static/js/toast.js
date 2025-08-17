
document.addEventListener("DOMContentLoaded", function () {
    const toast = document.getElementById("toast-success");
    if (toast && toast.textContent.trim() !== "") {
        toast.classList.remove("hidden");
        setTimeout(() => {
            toast.classList.add("hidden");
        }, 5000); // Ocultar después de 5 segundos
    }
});