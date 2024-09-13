export class TurnosApi {
  async getAll() {
    const response = await fetch("/api/turnos/all");
    const data = await response.json();
    return data;
  }

  async getById(id) {
    const response = await fetch(`/api/turnos/${id}`);
    const data = await response.json();
    return data;
  }

  async create(turno) {
    const response = await fetch("/api/turnos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(turno),
    });
    const data = await response.json();
    return data;
  }

  async update(id, turno) {
    const response = await fetch(`/api/turnos/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(turno),
    });
    const data = await response.json();
    return data;
  }

  async delete(id) {
    await fetch(`/api/turnos/${id}`, {
      method: "DELETE",
    });
  }
}
