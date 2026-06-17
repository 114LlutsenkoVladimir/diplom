import {checkEmptyInputs, checkNegativeInputs} from "../utils/checkEmptyInputs.js";

export function checkFindSpecialtyForm() {
    checkEmptyInputs("find-specialty")
}

export function checkUpdateSpecialtyPlacesForm() {
    checkEmptyInputs("updateSpecialtyPlaces")
    const contractPlacesValue = document.getElementById("updatePlacesContractPlaces").value
    const budgetPlacesValue = document.getElementById("updateBudgetPlacesGeneral").value
    const budgetPlacesQuota1 = document.getElementById("updatePlacesBudgetPlacesQuota1").value
    const budgetPlacesQuota2 = document.getElementById("updatePlacesBudgetPlacesQuota2").value
    if (contractPlacesValue.trim() !== "") {
        if (contractPlacesValue <= 0)
            throw new Error("Кіль-ть контрактних місць не может бути <= 0")
    }

    if (budgetPlacesValue.trim() !== "" ||
        budgetPlacesQuota1.trim() !== "" ||
        budgetPlacesQuota2.trim() !== "") {
        if (budgetPlacesValue < 0 ||
            budgetPlacesQuota1 < 0 ||
            budgetPlacesQuota2 < 0)
            throw new Error("Кіль-ть бюджетних місць не может бути < 0")
    }
}

export function checkCreateSpecialtyFromDtoForm() {
    checkEmptyInputs("createSpecialtyForm")
    checkNegativeInputs([
        "createSpecialtyBudgetPlaces",
        "createSpecialtyContractPlaces",
        "createSpecialtyNumber"
    ])
}
