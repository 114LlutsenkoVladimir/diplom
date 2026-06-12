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

export async function findAppealById(id) {
    const response = await fetch("findById/" + id)
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return response.json();
}


export async function getAppealInitMap() {
    const response = await fetch("initializeAppealPage")
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return response.json();
}

export async function deleteAppeal(id) {
    const response = await fetch("deleteById/" + id,{
        method: "DELETE"
    })
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
}


export async function updateAppealStatus(params) {
    const response = await fetch("updateAppealStatus?" + params)
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
}