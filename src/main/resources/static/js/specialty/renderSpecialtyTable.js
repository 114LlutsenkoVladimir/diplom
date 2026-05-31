export function renderSpecialtyTable(report) {
    const container = document.getElementById("specialtiesTable");
    container.innerHTML = ""; // очистка контейнера

    for (const [facultyId, specialties] of Object.entries(report.report)) {
        // обёртка для конкретного факультету
        const block = document.createElement("div");
        block.className = "table-wrapper card-table mb-4";

        const title = document.createElement("h2");
        title.textContent = report.facultyNames[facultyId] || "Невідомий факультет";
        block.appendChild(title);

        // таблица с бутстраповскими классами
        const table = document.createElement("table");
        table.className = "table table-striped table-hover align-middle";

        const thead = document.createElement("thead");
        thead.className = "table-light";
        thead.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Назва спеціальності</th>
                <th>Код спеціальності</th>
                <th>Бюджетні місця квоти 1</th>
                <th>Бюджетні місця квоти 2</th>
                <th>Бюджетні місця загальний конкурс</th>
                <th>Контрактні місця</th>
                <th>Всього місць</th>
            </tr>
        `;
        table.appendChild(thead);

        const tbody = document.createElement("tbody");
        specialties.forEach(specialty => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${specialty.id}</td>
                <td>${specialty.name}</td>
                <td>${specialty.number}</td>
                <td>${specialty.budgetPlacesQuota1 ?? ''}</td>
                <td>${specialty.budgetPlacesQuota2 ?? ''}</td>
                <td>${specialty.budgetPlacesGeneral ?? ''}</td>
                <td>${specialty.numberOfContractPlaces ?? ''}</td>
                <td>${specialty.sumOfPlaces ?? ''}</td>
            `;
            tbody.appendChild(row);
        });

        table.appendChild(tbody);
        block.appendChild(table);
        container.appendChild(block);
    }
}
