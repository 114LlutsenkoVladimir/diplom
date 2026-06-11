import {getAppealInitMap} from "./api.js";
import {initialize} from "./init.js";


document.addEventListener('DOMContentLoaded', async () => {
    const initMap = await getAppealInitMap();
    await initialize(initMap);
});


document.addEventListener("DOMContentLoaded", () => {
    const closeBtn = document.getElementById("appeal-close-btn");
    if (closeBtn) {
        closeBtn.addEventListener("click", closeAppealModal);
    }
    window.addEventListener("click", (event) => {
        const modal = document.getElementById("custom-appeal-modal");
        if (event.target === modal) {
            closeAppealModal();
        }
    });
});

// Функция скрытия окна
function closeAppealModal() {
    const modal = document.getElementById("custom-appeal-modal");
    if (modal) modal.style.display = "none";
}

// 2. Настройка открытия окна (Глобальный перехват клика по кнопке)
document.addEventListener('click', function (event) {
    // Проверяем, кликнули ли по кнопке "Подивитись"
    const button = event.target.closest('.js-open-modal-btn');
    if (!button) return;

    const modal = document.getElementById('custom-appeal-modal');
    if (!modal) return;

    // Считываем данные
    const appId = button.getAttribute('data-app-id');
    const fullName = button.getAttribute('data-full-name');
    const specAppId = button.getAttribute('data-spec-app-id');
    const message = button.getAttribute('data-message');

    // Заполняем поля
    document.getElementById('dynamicModalTitle').textContent = `Апеляція за заявою #${specAppId}`;
    document.getElementById('appealModalContent').style.display = "none";
    document.getElementById('appealModalContent_applicantId').textContent = `ID Абітурієнта: ${appId}`;
    document.getElementById('appealModalContent_applicantFullName').textContent = `ПІБ: ${fullName}`;
    document.getElementById('appealModalContent_specialtyForApplicantId').textContent = `ID Заяви: ${specAppId}`;
    document.getElementById('appealModalContent_appealMessage').value = message;

    // ПОКАЗЫВАЕМ ОКНО
    modal.style.display = "block";
});
