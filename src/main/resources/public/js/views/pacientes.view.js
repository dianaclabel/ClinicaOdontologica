import { PacientesApi } from "../api/pacientes.api.js";
import { Button } from "../ui/button.js";
import { Container } from "../ui/container.js";
import { Form, FORM_EVENT_INIT_RECORD } from "../ui/form.js";
import { createHeading as Heading } from "../ui/heading.js";
import { Modal, MODAL_EVENT_SET_TITLE } from "../ui/modal.js";
import { Stack } from "../ui/stack.js";
import { Table, TABLE_EVENT_RELOAD } from "../ui/table.js";
import { dispatch } from "../utils/dispatch.js";

const pacientesApi = new PacientesApi();

const PACIENTE_MODAL_ID = "modalPaciente";
const PACIENTES_TABLE_ID = "pacientesTable";
const PACIENTE_FORM_ID = "formPaciente";

export const PacientesView = async () =>
  Container({
    classnames: "py-5 px-0",
    children: [
      Stack({
        direction: "column",
        classNames: "my-2",
        children: [
          Heading("Pacientes", {
            level: "h2",
            classnames: "text-center",
          }),

          Button({
            text: "Nuevo Paciente",
            onclick: () => {
              const modal = document.getElementById(PACIENTE_MODAL_ID);
              $(modal).modal("show");
              dispatch(modal, MODAL_EVENT_SET_TITLE, "Nuevo Paciente");

              const form = document.getElementById(PACIENTE_FORM_ID);
              dispatch(form, FORM_EVENT_INIT_RECORD, undefined);
            },
            classnames: "mx-auto",
          }),
        ],
      }),

      await Table({
        id: PACIENTES_TABLE_ID,
        getRecords: () => pacientesApi.getAll(),
        columns: [
          { key: "id", label: "ID" },
          { key: "nombre", label: "Nombre" },
          { key: "apellido", label: "Apellido" },
          { key: "dni", label: "DNI" },
          { key: "fechaAlta", label: "Fecha de Alta" },
          {
            key: "domicilio",
            label: "Domicilio",
            format: (domicilio) =>
              `${domicilio.calle} ${domicilio.numero}, ${domicilio.localidad}, ${domicilio.provincia}`,
          },

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
                      const modal = document.getElementById(PACIENTE_MODAL_ID);
                      $(modal).modal("show");
                      dispatch(modal, MODAL_EVENT_SET_TITLE, "Editar paciente");

                      const form = document.getElementById(PACIENTE_FORM_ID);
                      dispatch(form, FORM_EVENT_INIT_RECORD, record);
                    },
                    classnames: "btn btn-primary",
                  }),

                  Button({
                    text: "Eliminar",
                    onclick: async () => {
                      // confirm before delete
                      const confirmDelete = confirm(
                        `¿Está seguro que desea eliminar a ${record.nombre} ${record.apellido}?`
                      );
                      if (!confirmDelete) {
                        return;
                      }
                      await pacientesApi.delete(record.id);
                      dispatch(
                        document.getElementById(PACIENTES_TABLE_ID),
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
        id: PACIENTE_MODAL_ID,
        title: "Nuevo paciente",
        content: Form({
          id: PACIENTE_FORM_ID,
          classNames: "p-4",
          fields: [
            {
              name: "nombre",
              label: "Nombre",
              type: "text",
              validate: (v) => (v ? true : "El nombre es requerido"),
            },
            {
              name: "apellido",
              label: "Apellido",
              type: "text",
              validate: (v) => (v ? true : "El apellido es requerido"),
            },
            {
              name: "dni",
              label: "DNI",
              type: "text",
              validate: (v) => (v ? true : "La matrícula es requerida"),
            },
            {
              name: "fechaAlta",
              label: "Fecha de Alta",
              type: "date",
              validate: (v) => (v ? true : "La fecha de ingreso es requerida"),
            },
            {
              name: "domicilio.calle",
              label: "Calle",
              type: "text",
              validate: (v) => (v ? true : "La calle es requerida"),
            },
            {
              name: "domicilio.numero",
              label: "Número",
              type: "text",
              validate: (v) => (v ? true : "El número es requerido"),
            },
            {
              name: "domicilio.localidad",
              label: "Localidad",
              type: "text",
              validate: (v) => (v ? true : "La localidad es requerida"),
            },
            {
              name: "domicilio.provincia",
              label: "Provincia",
              type: "text",
              validate: (v) => (v ? true : "La provincia es requerida"),
            },
          ],
          submit: { text: "Guardar" },
          onSubmit: async (formData, record) => {
            if (record) {
              await pacientesApi.update(record.id, formData);
            } else {
              await pacientesApi.create(formData);
            }
            $(`#${PACIENTE_MODAL_ID}`).modal("hide"); // Bootstrap
            // Reload table
            dispatch(
              document.getElementById(PACIENTES_TABLE_ID),
              TABLE_EVENT_RELOAD
            );
          },
        }),
      }),
    ],
  });
