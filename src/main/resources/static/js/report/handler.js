import {getApplicantsReport, getSpecialtiesReport} from "./api.js";
import {showError} from "../errorPopup/errorPopup.js";
import {renderApplicantTable} from "../applicant/renderApplicantTable.js";
import {renderSpecialtyTable} from "../specialty/renderSpecialtyTable.js";


export async function handleShowReport({
                                           buttonId,
                                           wrapId,
                                           getReportFunction,
                                           renderTableFunction,
                                           paramsKey = "static" // По умолчанию, если у отчета нет параметров
                                       }) {
    const btn = document.getElementById(buttonId);
    const wrap = document.getElementById(wrapId);

    const isHidden = wrap.style.display === "none" || wrap.style.display === "";
    const isParamsChanged = btn.dataset.lastParams !== paramsKey;

    // Если блок скрыт ИЛИ параметры поменялись (нужно обновить открытый отчет)
    if (isHidden || isParamsChanged) {
        wrap.style.display = "block"; // Показываем блок

        // Запрашиваем данные на сервере только если параметры действительно изменились
        if (isParamsChanged) {
            try {
                const report = await getReportFunction();
                renderTableFunction(report);
                // Запоминаем текущие параметры, чтобы не скачивать то же самое дважды
                btn.dataset.lastParams = paramsKey;
            } catch (err) {
                showError(err.message);
                wrap.style.display = "none"; // Прячем блок при ошибке
            }
        }
    } else {
        // Если блок открыт и параметры НЕ менялись — прячем его
        wrap.style.display = "none";
    }
}


export async function handleShowApplicantsReport() {
    // Собираем текущие значения прямо перед вызовом
    const year = document.getElementById("year-select").value;
    const type = document.getElementById("quota-type-select").value;

    // Создаем уникальный "слепок" параметров, например: "2024_GENERAL"
    const currentParamsKey = `${year}_${type}`;

    await handleShowReport({
        buttonId: "applicantsReportBtn",
        wrapId: "applicantsReportWrap",
        getReportFunction: getApplicantsReport,
        renderTableFunction: renderApplicantTable,
        paramsKey: currentParamsKey
    });
}


export async function handleShowSpecialtiesReport() {
    await handleShowReport({
        buttonId: "specialtiesReportBtn",
        wrapId: "specialtiesReportWrap",
        getReportFunction: getSpecialtiesReport,
        renderTableFunction: renderSpecialtyTable
    });
}