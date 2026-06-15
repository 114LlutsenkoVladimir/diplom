import {handleRegisterNewApplicant} from "./handlers.js";
import {initBenefits, initSubjectScoreInputs} from "../applicant/init.js";

export async function init(initMap) {
    initBenefits(initMap.allBenefits)
    await initSubjectScoreInputs(initMap.allSubjects)
    document.getElementById("addApplicantForm")
        .addEventListener('submit', (event) => {
            handleRegisterNewApplicant(event)
        });
}

