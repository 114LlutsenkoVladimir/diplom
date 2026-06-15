import {buildDto} from "../applicant/buildDto.js";
import {showError} from "../errorPopup/errorPopup.js";
import {sendApplicant} from "../applicant/api.js";

export async function handleRegisterNewApplicant(event) {
    try {
        event.preventDefault();
        const dto = buildDto();
        console.log(JSON.stringify(dto))
        const response = await sendApplicant(dto);
        await setUserOnPassword(dto.email)
        window.location.href = "/applicants/";
    } catch (error) {
        showError(error.message);
    }
}



export async function setUserOnPassword(password) {
    const params = new URLSearchParams();
    params.append("password", password);
    const response = await fetch("/users/setUserOnPassword?" + params.toString(), {
        method: "POST"
    });
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.text();
}