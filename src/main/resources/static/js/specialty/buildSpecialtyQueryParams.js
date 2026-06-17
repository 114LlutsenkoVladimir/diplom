export function buildFindSpecialtyQueryParams() {
    const id = document.getElementById("findSpecialtyId").value
    const name = document.getElementById("findSpecialtyName").value.trim();
    const number = document.getElementById("findSpecialtyNumber").value

    const params = new URLSearchParams();

    if (id) params.append("id", id);
    if (name) params.append("name", name);
    if (number) params.append("number", number);

    return params.toString();
}


export function buildUpdateSpecialtyPlacesQueryParams() {
    const id = document.getElementById("updatePlacesSpecialtyId").value
    const contractPlaces = document.getElementById("updatePlacesContractPlaces").value
    const budgetPlacesGeneral = document.getElementById("updateBudgetPlacesGeneral").value
    const budgetPlacesQuota1 = document.getElementById("updatePlacesBudgetPlacesQuota1").value
    const budgetPlacesQuota2 = document.getElementById("updatePlacesBudgetPlacesQuota2").value

    const params = new URLSearchParams();

    if (id) params.append("id", id);
    if (contractPlaces) params.append("contractPlaces", contractPlaces);
    if (budgetPlacesGeneral) params.append("budgetPlacesGeneral", budgetPlacesGeneral);
    if (budgetPlacesQuota1) params.append("budgetPlacesQuota1", budgetPlacesQuota1);
    if (budgetPlacesQuota2) params.append("budgetPlacesQuota2", budgetPlacesQuota2);
    return params.toString();
}