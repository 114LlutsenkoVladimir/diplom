import {
    handleCreateSpecialtyFromDto, handleDeleteSpecialtyById,
    handleFilterSpecialtyTable,
    handleFindSpecialty,
    handleUpdateSpecialtyPlaces
} from "./handlers.js";
import {getUser} from "../user/api.js";

export async function initialize(initMap) {

    await initCommon(initMap);
    const user = await getUser();

    if(user === "admin")
        await initAdmin(initMap);

}

export async function initCommon(initMap) {
    initFacultySelector(initMap.allFaculties, "facultySelect")
    initFacultySelector(initMap.allFaculties, "createSpecialtyFacultySelector")

    document.getElementById("facultySelect").addEventListener("change", handleFilterSpecialtyTable)
    document.getElementById("findSpecialtyBtn").addEventListener("click", handleFindSpecialty)
    document.getElementById("updateSpecialtyBtn").addEventListener("click", handleUpdateSpecialtyPlaces)

}

export async function initAdmin(initMap) {
    initSubjects(initMap.allSubjects)
    document.getElementById("addSpecialtyBtn").addEventListener("click", handleCreateSpecialtyFromDto)
    document.getElementById("deleteSpecialtyBtn").addEventListener("click", handleDeleteSpecialtyById)
}


function initSubjects(subjects) {
    const container = document.getElementById('createSpecialtyRequiredSubjectsSelect');

    // Очищаем контейнер перед добавлением (на случай повторного вызова)
    container.innerHTML = '';

    subjects.forEach(subject => {
        const wrapper = document.createElement('div');
        // Используем классы Bootstrap для красивого выравнивания в строку
        wrapper.className = 'mb-2 d-flex align-items-center justify-content-between';

        // Название предмета
        const label = document.createElement('span');
        label.textContent = subject.name;
        label.className = 'me-3';

        // Поле ввода для веса
        const weightInput = document.createElement('input');
        weightInput.type = 'number';
        weightInput.step = '0.1'; // Позволяет вводить десятые доли (0.1, 0.5)
        weightInput.min = '0';    // Минимальный вес
        weightInput.max = '1';    // Максимальный вес
        weightInput.placeholder = 'Вага (0.0)';
        weightInput.className = 'form-control form-control-sm';
        weightInput.style.width = '100px'; // Ограничиваем ширину поля

        // ВАЖНО: сохраняем ID предмета прямо в поле ввода,
        // чтобы потом достать его при сборке JSON
        weightInput.dataset.subjectId = subject.id;

        // Добавляем элементы в обертку, а обертку — в контейнер
        wrapper.appendChild(label);
        wrapper.appendChild(weightInput);
        container.appendChild(wrapper);
    });
}


function initFacultySelector(faculties, selectorName) {
    const select = document.getElementById(selectorName);

    faculties.forEach(faculty => {
        const option = document.createElement('option');
        option.value = faculty.id;
        option.textContent = faculty.name;
        select.appendChild(option);
    });
}
