import {handleShowApplicantsReport, handleShowSpecialtiesReport} from "./handler.js";

import {initQuotaSelect} from "../applicant/init.js";
import {getApplicantInitDto} from "../applicant/api.js";


export async function initialize() {
    document.getElementById("applicantsReportBtn")
        .addEventListener("click", handleShowApplicantsReport);

    document.getElementById("specialtiesReportBtn")
        .addEventListener("click", handleShowSpecialtiesReport);

    const initMap = await getApplicantInitDto();
    initQuotaSelect(initMap.allQuotaTypes)
    initYearSelect(initMap.submissionYears)
}


export function initYearSelect(years) {
    // Вкажіть правильний ID вашого селектора в HTML
    const select = document.getElementById('year-select');

    select.innerHTML = '';

    const defaultOption = document.createElement('option');
    defaultOption.value = ""; // Порожнє значення для всіх років
    defaultOption.textContent = "Всі роки";
    select.appendChild(defaultOption);


    years.forEach(year => {
        const option = document.createElement('option');
        option.value = year;
        option.textContent = year;
        select.appendChild(option);
    });
}
