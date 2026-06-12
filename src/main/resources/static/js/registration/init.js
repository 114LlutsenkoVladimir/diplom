

initBenefits(initMap.allBenefits)
await initSubjectScoreInputs(initMap.allSubjects)

document.getElementById("addApplicantForm")
    .addEventListener('submit', (event) => {
        handleSubmit(event);
    });