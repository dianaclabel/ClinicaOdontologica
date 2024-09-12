// service to get odontologos data
// each method uses a fetch call to the server
// the base path is /api/odontologos
// odontolog object has the following structure: {id: number, nombre: string, apellido: string, matricula: string}

export class OdontologosApi {
  async getAll() {
    const response = await fetch("/api/odontologos");
    const data = await response.json();
    return data;
  }

  async getById(id) {
    const response = await fetch(`/api/odontologos/${id}`);
    const data = await response.json();
    return data;
  }

  async create(odontologo) {
    const response = await fetch("/api/odontologos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(odontologo),
    });
    const data = await response.json();
    return data;
  }

  async update(id, odontologo) {
    const response = await fetch(`/api/odontologos/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(odontologo),
    });
    const data = await response.json();
    return data;
  }

  async delete(id) {
    await fetch(`/api/odontologos/${id}`, {
      method: "DELETE",
    });
  }
}
