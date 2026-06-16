import {
    handleAddAppeal,
    handleDeleteAppeal,
    handleFindAppeal,
    handleQuotaSelect, handleSelectBoth,
    handleSpecialtySelect,
    handleUpdateAppealStatus
} from "./handlers.js";
import {initQuotaSelect} from "../applicant/init.js";


export async function initialize(initMap) {

    await initCommon(initMap);
    // const user = await getUser();
    // await initAdmin(initMap);

}



export async function initCommon(initMap) {
    initSpecialtySelect(initMap.allSpecialties)
    initAppealStatusSelect(initMap.allAppealStatuses)
    initQuotaSelect(initMap.allQuotaTypes)
    document.getElementById("specialty-select").addEventListener("click", handleSpecialtySelect)
    document.getElementById("quota-type-select").addEventListener("click", handleQuotaSelect)
    document.getElementById("select-by-specialty-and-quota").addEventListener("click", handleSelectBoth)
    document.getElementById("updateAppealStatusForm").addEventListener("submit", (event) => {
        handleUpdateAppealStatus(event)
    })
    document.getElementById("findAppealForm").addEventListener("submit", (event) => handleFindAppeal(event))

    document.getElementById("addAppealForm").addEventListener("submit", (event) => handleAddAppeal(event))
    document.getElementById("deleteAppealById").addEventListener("submit", (event) => handleDeleteAppeal(event))

}

function initAppealStatusSelect(appeals) {
    const select = document.getElementById('appealStatusSelect');

    appeals.forEach(appeal => {
        const option = document.createElement('option');
        option.value = appeal.code;
        option.textContent = appeal.name;
        select.appendChild(option);
    });
}



function initSpecialtySelect(specialties) {
    const select = document.getElementById('specialty-select');

    specialties.forEach(specialty => {
        const option = document.createElement('option');
        option.value = specialty.id;
        option.textContent = specialty.name;
        select.appendChild(option);
    });
}