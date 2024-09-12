


export class PacientesApi {
  async getAll() {
    const response = await fetch("/api/pacientes/all");
    const data = await response.json();
    return data;
  }

  async getById(id) {
    const response = await fetch(`/api/pacientes/${id}`);
    const data = await response.json();
    return data;
  }

  async create(paciente) {
    const response = await fetch("/api/pacientes", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(paciente),
    });
    const data = await response.json();
    return data;
  }

  async update(id, paciente) {
    const response = await fetch(`/api/pacientes/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(paciente),
    });
    const data = await response.json();
    return data;
  }

  async delete(id) {
    await fetch(`/api/pacientes/${id}`, {
      method: "DELETE",
    });
  }
}
