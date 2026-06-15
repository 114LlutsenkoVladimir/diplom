
export async function getApplicantsReport() {
    const params = new URLSearchParams();
    const year = document.getElementById("year-select").value
    const type = document.getElementById("quota-type-select").value
    params.append("year", year)
    params.append("type", type)

    const response = await fetch("applicantsReport?" + params)
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.json();
}


export async function getSpecialtiesReport() {
    const response = await fetch("specialtiesReport")
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
    return await response.json();
}