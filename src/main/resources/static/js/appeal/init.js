


export async function initialize(initMap) {

    await initCommon(initMap);
    // const user = await getUser();
    // await initAdmin(initMap);

}


export async function initCommon(initMap) {
    initSpecialtySelect(initMap.allSpecialties, "specialtySelect")
}

function initSpecialtySelect(specialties) {
    const select = document.getElementById('specialty-select');

    specialties.forEach(specialty => {
        const option = document.createElement('option');
        option.value = specialty.id;
        option.textContent = specialty.name;
        select.appendChild(option);
    });
}