

export function buildDto() {
    return {
        number: getVal("createSpecialtyNumber"),
        name: getVal("createSpecialtyName"),
        facultyId: getVal("createSpecialtyFacultySelector"),
        budgetPlaces: getValOrElseZero("createSpecialtyBudgetPlaces"),
        contractPlaces: getValOrElseZero("createSpecialtyContractPlaces"),

        // Используем новую функцию для сбора ID и весов
        subjects: getSubjectsWithWeights()
    };
}

function getVal(id) {
    return document.getElementById(id).value.trim();
}

function getValOrElseZero(id) {
    const val = document.getElementById(id).value.trim();
    return val ? val : 0;
}


export function getSubjectsWithWeights() {
    const subjectsMap = {};
    let hasFilledFields = false; // Флаг: было ли заполнено хоть одно поле

    const inputs = document.querySelectorAll('#createSpecialtyRequiredSubjectsSelect input[type="number"]');

    for (const input of inputs) {
        const stringValue = input.value.trim();

        if (stringValue !== "") {
            hasFilledFields = true;

            const weight = parseFloat(stringValue);


            if (weight <= 0) {
                throw new Error("Вага не може дорівнювати 0");
            }

            const subjectId = input.dataset.subjectId;

            if (!isNaN(weight) && subjectId) {
                subjectsMap[subjectId] = weight;
            }
        }
    }

    if (!hasFilledFields) {
        throw new Error("Заповніть хоча б 1 поле");
    }

    return subjectsMap;
}