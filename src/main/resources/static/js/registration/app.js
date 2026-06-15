import {getApplicantInitDto} from "../applicant/api.js";
import {init} from "./init.js";
import {setupSpecialtyChecker} from "../applicant/listener.js";


document.addEventListener('DOMContentLoaded', async () => {
    const initMap = await getApplicantInitDto();
    await init(initMap);
    setupSpecialtyChecker();
});