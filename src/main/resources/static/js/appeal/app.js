import {getAppealInitMap} from "./api.js";
import {initialize} from "./init.js";


document.addEventListener('DOMContentLoaded', async () => {
    const initMap = await getAppealInitMap();
    await initialize(initMap);
});



