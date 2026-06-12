import {showError} from "../errorPopup/errorPopup.js";
import {deleteAppeal, fetchAddAppeal, findAppealById, getAppealsBySpecialty, updateAppealStatus} from "./api.js";
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
    try {
        const selector = document.getElementById("specialty-select")
        const specialtyId = selector.value
        const response = await getAppealsBySpecialty(specialtyId)
        renderAppealTable(response)
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