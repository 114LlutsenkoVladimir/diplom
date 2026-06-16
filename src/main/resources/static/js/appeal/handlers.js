import {showError} from "../errorPopup/errorPopup.js";
import {deleteAppeal, fetchAddAppeal, findAppealById, getAppealsBySpecialtyAndQuota, updateAppealStatus} from "./api.js";
import {buildAddAppealQueryParams} from "./buildDto.js";
import {clearForm} from "../utils/clearForm.js";
import {renderAppealTable} from "./renderTable.js";

export async function handleAddAppeal(event) {
    try {
        event.preventDefault()
        const params = buildAddAppealQueryParams();
        await fetchAddAppeal(params);
        clearForm("addAppealForm");
        document.getElementById("appealMessage").innerHTML="";
    } catch (error) {
        showError(error.message);
    }
}

export async function handleSpecialtySelect() {
    const selector = document.getElementById("specialty-select")
    const specialtyId = selector.value
    await handleSpecialtyAndQuotaSelect(specialtyId, null)
}


export async function handleQuotaSelect() {
    const selector = document.getElementById("quota-type-select")
    const type = selector.value
    await handleSpecialtyAndQuotaSelect(null, type)
}

export async function handleSelectBoth() {
    const specialtyId = document.getElementById("specialty-select").value
    const type = document.getElementById("quota-type-select").value
    await handleSpecialtyAndQuotaSelect(specialtyId, type)
}

export async function handleSpecialtyAndQuotaSelect(specialtyId, type) {
    try {
        const params = new URLSearchParams();

        // Отсеиваем undefined, настоящий null, пустую строку "" и строку "null"
        if (specialtyId && specialtyId !== "null" && specialtyId !== "") {
            params.append("specialtyId", specialtyId);
        }

        if (type && type !== "null" && type !== "") {
            params.append("type", type);
        }

        const response = await getAppealsBySpecialtyAndQuota(params);
        renderAppealTable(response);
    } catch (error) {
        showError(error.message);
    }
}


export async function handleDeleteAppeal(event) {
    try {
        event.preventDefault();
        const appealId = document.getElementById("deleteAppealById_appealId").value
        await deleteAppeal(appealId)
        clearForm("deleteAppealById")
    } catch (error) {
        showError(error.message);
    }
}

export async function handleFindAppeal(event) {
    try {
        event.preventDefault();
        const appealId = document.getElementById("findAppeal_appealId").value
        const response = await findAppealById(appealId)
        renderAppealTable(response)
        clearForm("findAppealForm")
    } catch (error) {
        showError(error.message);
    }
}



export async function handleUpdateAppealStatus(event) {
    try {
        event.preventDefault();
        const params = new URLSearchParams();
        const selector = document.getElementById("appealStatusSelect")
        const status = selector.value
        const appealId = document.getElementById("appealId").value

        params.append("status", status)
        params.append("appealId", appealId)

        await updateAppealStatus(params)
        clearForm("updateAppealStatusForm")
    } catch (error) {
        showError(error.message);
    }
}