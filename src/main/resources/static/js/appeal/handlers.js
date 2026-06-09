import {showError} from "../errorPopup/errorPopup.js";
import {fetchAddAppeal} from "./api.js";
import {buildAddAppealQueryParams} from "./buildDto.js";
import {clearForm} from "../utils/clearForm.js";

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