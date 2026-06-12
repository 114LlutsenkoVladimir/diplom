import {buildDto} from "../applicant/buildDto.js";
import {showError} from "../errorPopup/errorPopup.js";

export async function handleRegisterNewApplicant(event) {
    try {
        event.preventDefault();
        const dto = buildDto();
        const response = await sendApplicant(dto);
        await setUserOnPassword(response.email)
        window.location.href = "/applicants/";
    } catch (error) {
        showError(error.message);
    }
}



export async function sendApplicant(dto) {
    const response = await fetch("/applicants/addApplicant", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dto)
    });
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }

    return await response.json();
}

export async function setUserOnPassword(password) {
    const params = new URLSearchParams();
    params.append("password", password);
    const response = await fetch("/users/setUserOnPassword?" + params);
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.json();
}