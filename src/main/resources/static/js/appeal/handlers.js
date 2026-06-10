import {showError} from "../errorPopup/errorPopup.js";
import {fetchAddAppeal, getAppealsBySpecialty} from "./api.js";
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
        const data = await response.json()
        renderAppealTable(data)
    } catch (error) {
        showError(error.message);
    }
}