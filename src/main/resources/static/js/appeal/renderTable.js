export function renderAppealTable(report) {
    const container = document.getElementById("table-container");
    container.innerHTML = "";

    for (const [specialtyId, applicants] of Object.entries(report.report)) {

        const block = document.createElement("div");
        block.className = "table-wrapper card-table mb-4";

        const title = document.createElement("h2");
        title.textContent = report.specialtyNames[specialtyId];
        block.appendChild(title);

        const table = document.createElement("table");
        table.id = "applicants-table-" + specialtyId;
        table.className = "table table-striped table-hover align-middle";

        const thead = document.createElement("thead");
        thead.className = "table-light";
        thead.innerHTML = `
            <tr>
                <th>Id Абітурієнта</th>
                <th>Прізвище</th>
                <th>Id Заяви</th>
                <th>Подивитись</th>
                <th>Статус заяви</th>
                <th>Статус апеляції</th>
            </tr>
        `;
        table.appendChild(thead);

        const tbody = document.createElement("tbody");

        applicants.forEach(appeal => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${appeal.applicantId}</td>
                <td>${appeal.applicantFullName}</td>
                <td>${appeal.specialtyForApplicantId}</td>
                <td>${appeal.lastName}</td>
                <td>${appeal.specialtyForApplicantStatus}</td>
                <td>${appeal.appealStatus}</td>
            `;
            tbody.appendChild(row);
        });

        table.appendChild(tbody);
        block.appendChild(table);
        container.appendChild(block);
    }
}

