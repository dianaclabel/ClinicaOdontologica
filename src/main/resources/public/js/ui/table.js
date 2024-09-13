export const TABLE_EVENT_RELOAD = "reload";

export async function Table({ id, columns = [], getRecords } = {}) {
  // Validate columns and getRecords
  if (!Array.isArray(columns) || typeof getRecords !== "function") {
    throw new Error(
      "Columns must be an array and getRecords must be a function"
    );
  }

  const container = document.createElement("div");
  container.classList.add("table-responsive"); // Add Bootstrap class to container

  const table = document.createElement("table");
  table.classList.add("table", "table-striped", "table-bordered"); // Add Bootstrap classes to table

  if (id) {
    table.id = id; // Set the id if provided
  }

  const thead = document.createElement("thead");
  thead.classList.add("thead-dark"); // Add Bootstrap class to thead

  const tbody = document.createElement("tbody");

  const theadRow = document.createElement("tr");
  for (const column of columns) {
    const th = document.createElement("th");
    th.textContent = column.label;
    th.classList.add("text-center", "align-middle"); // Add Bootstrap classes to th
    theadRow.appendChild(th);
  }
  thead.appendChild(theadRow);
  table.appendChild(thead);

  function getNestedValue(obj, key) {
    return key.split(".").reduce((acc, part) => acc?.[part], obj);
  }

  async function loadRows() {
    tbody.innerHTML = ""; // Clear existing rows
    try {
      const rowData = await getRecords();
      for (const row of rowData) {
        const tr = document.createElement("tr");
        for (const column of columns) {
          const td = document.createElement("td");
          // if format returns HTMLElement, append it to td, else set textContent
          const data = column.format
            ? column.format(getNestedValue(row, column.key), row)
            : getNestedValue(row, column.key);
          if (data instanceof HTMLElement) {
            td.appendChild(data);
          } else {
            td.textContent = data;
          }

          td.classList.add("text-center", "align-middle"); // Add Bootstrap classes to td
          tr.appendChild(td);
        }
        tbody.appendChild(tr);
      }
    } catch (error) {
      console.error("Error fetching rows:", error);
    }
  }

  await loadRows(); // Initial load of rows

  table.appendChild(tbody);

  // Listen for custom "reload" event
  table.addEventListener(TABLE_EVENT_RELOAD, async () => {
    await loadRows();
  });

  container.appendChild(table); // Append table to container

  return container;
}
