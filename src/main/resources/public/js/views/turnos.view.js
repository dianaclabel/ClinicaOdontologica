import { TurnosApi } from "../api/turnos.api.js";
import { Button } from "../ui/button.js";
import { Container } from "../ui/container.js";
import { Form, FORM_EVENT_INIT_RECORD } from "../ui/form.js";
import { createHeading as Heading } from "../ui/heading.js";
import { Modal, MODAL_EVENT_SET_TITLE } from "../ui/modal.js";
import { Stack } from "../ui/stack.js";
import { Table, TABLE_EVENT_RELOAD } from "../ui/table.js";
import { dispatch } from "../utils/dispatch.js";

const turnosApi = new TurnosApi();

const TURNO_MODAL_ID = "modalTurno";
const TURNOS_TABLE_ID = "turnosTable";
const TURNO_FORM_ID = "formTurno";

export const TurnosView = async () =>
  Container({
    classnames: "py-5 px-0",
    children: [
      Stack({
        direction: "column",
        classNames: "my-2",
        children: [
          Heading("Turnos", {
            level: "h2",
            classnames: "text-center",
          }),

          Button({
            text: "Nuevo Turno",
            onclick: () => {
              const modal = document.getElementById(TURNO_MODAL_ID);
              $(modal).modal("show");
              dispatch(modal, MODAL_EVENT_SET_TITLE, "Nuevo Turno");

              const form = document.getElementById(TURNO_FORM_ID);
              dispatch(form, FORM_EVENT_INIT_RECORD, undefined);
            },
            classnames: "mx-auto",
          }),
        ],
      }),

      await Table({
        id: TURNOS_TABLE_ID,
        getRecords: () => turnosApi.getAll(),
        columns: [
          { key: "id", label: "ID" },
          { key: "paciente.nombre", label: "Paciente" },
          { key: "odontologo.nombre", label: "Odontólogo" },
          { key: "fecha", label: "Fecha del Turno" },
          {
            key: "acciones",
            label: "Acciones",
            format: (_, record) =>
              Stack({
                gap: "0.5rem",
                children: [
                  Button({
                    text: "Editar",
                    onclick: () => {
                      const modal = document.getElementById(TURNO_MODAL_ID);
                      $(modal).modal("show");
                      dispatch(modal, MODAL_EVENT_SET_TITLE, "Editar Turno");

                      const form = document.getElementById(TURNO_FORM_ID);
                      dispatch(form, FORM_EVENT_INIT_RECORD, record);
                    },
                    classnames: "btn btn-primary",
                  }),

                  Button({
                    text: "Eliminar",
                    onclick: async () => {
                      const confirmDelete = confirm(
                        `¿Está seguro que desea eliminar el turno?`
                      );
                      if (!confirmDelete) {
                        return;
                      }
                      await turnosApi.delete(record.id);
                      dispatch(
                        document.getElementById(TURNOS_TABLE_ID),
                        TABLE_EVENT_RELOAD
                      );
                    },
                    classnames: "btn btn-danger",
                  }),
                ],
              }),
          },
        ],
      }),

      Modal({
        id: TURNO_MODAL_ID,
        title: "Nuevo Turno",
        content: Form({
          id: TURNO_FORM_ID,
          classNames: "p-4",
          fields: [
            {
              name: "pacienteId",
              label: "ID del Paciente",
              type: "text",
              validate: (v) => (v ? true : "El ID del paciente es requerido"),
            },
            {
              name: "odontologoId",
              label: "ID del Odontólogo",
              type: "text",
              validate: (v) => (v ? true : "El ID del odontólogo es requerido"),
            },
            {
              name: "fecha",
              label: "Fecha del Turno",
              type: "date",
              validate: (v) => (v ? true : "La fecha es requerida"),
            },
          ],
          submit: { text: "Guardar" },
          onSubmit: async (formData, record) => {
            if (record) {
              await turnosApi.update(record.id, formData);
            } else {
              await turnosApi.create(formData);
            }
            $(`#${TURNO_MODAL_ID}`).modal("hide");
            dispatch(
              document.getElementById(TURNOS_TABLE_ID),
              TABLE_EVENT_RELOAD
            );
          },
        }),
      }),
    ],
  });
