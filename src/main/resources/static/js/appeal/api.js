export async function fetchAddAppeal(params) {
    const response = await fetch("addAppeal?" + params)
    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message);
    }
}