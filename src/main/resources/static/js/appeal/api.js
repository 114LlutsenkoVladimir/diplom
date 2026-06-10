export async function fetchAddAppeal(params) {
    const response = await fetch("addAppeal?" + params)
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return response.json();
}

export async function getAppealsBySpecialty(id) {
    const response = await fetch("filterAppealsBySpecialty/" + id)
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return response.json();
}

export async function getAppealInitMap() {
    const response = await fetch("appeals/initializeAppealPage")
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return response.json();
}