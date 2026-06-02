export function buildAddAppealQueryParams() {
    const specialtyForApplicantId = document.getElementById("specialtyForApplicantId").value
    const appealMessage = document.getElementById("appealMessage").value
    const params = new URLSearchParams();
    params.append("specialtyForApplicantId", specialtyForApplicantId)
    params.append("appealMessage", appealMessage)
    return params.toString();
}