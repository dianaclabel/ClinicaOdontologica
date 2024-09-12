import { OdontologosApi } from "../api/odontologos.api.js";
import { Button } from "../ui/button.js";
import { Container } from "../ui/container.js";
import { Form, FORM_EVENT_INIT_RECORD } from "../ui/form.js";
import { createHeading as Heading } from "../ui/heading.js";
import { Modal, MODAL_EVENT_SET_TITLE } from "../ui/modal.js";
import { Stack } from "../ui/stack.js";
import { Table, TABLE_EVENT_RELOAD } from "../ui/table.js";
import { dispatch } from "../utils/dispatch.js";

const odontologosApi = new OdontologosApi();

const MODAL_ODONTOLOGO_ID = "modalOdontologo";
const TABLE_ODONTOLOGOS_ID = "odontologosTable";
const FORM_ODONTOLOGO_ID = "formOdontologo";

export const OdontologosView = async () =>
  Container({
    classnames: "py-5 px-0",
    children: [
      Stack({
        direction: "column",
        classNames: "my-2",
        children: [
          Heading("Odontólogos", {
            level: "h2",
            classnames: "text-center",
          }),

          Button({
            text: "Nuevo Odontólogo",
            onclick: () => {
              const modal = document.getElementById(MODAL_ODONTOLOGO_ID);
              $(modal).modal("show");
              dispatch(modal, MODAL_EVENT_SET_TITLE, "Nuevo Odontólogo");

              const form = document.getElementById(FORM_ODONTOLOGO_ID);
              dispatch(form, FORM_EVENT_INIT_RECORD, undefined);
            },
            classnames: "mx-auto",
          }),
        ],
      }),

      await Table({
        id: TABLE_ODONTOLOGOS_ID,
        getRecords: () => odontologosApi.getAll(),
        columns: [
          { key: "id", label: "ID" },
          { key: "nombre", label: "Nombre" },
          { key: "apellido", label: "Apellido" },
          { key: "matricula", label: "Matrícula" },
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
                      const modal =
                        document.getElementById(MODAL_ODONTOLOGO_ID);
                      $(modal).modal("show");
                      dispatch(
                        modal,
                        MODAL_EVENT_SET_TITLE,
                        "Editar Odontólogo"
                      );

                      const form = document.getElementById(FORM_ODONTOLOGO_ID);
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
                      await odontologosApi.delete(record.id);
                      dispatch(
                        document.getElementById(TABLE_ODONTOLOGOS_ID),
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
        id: MODAL_ODONTOLOGO_ID,
        title: "Nuevo Odontólogo",
        content: Form({
          id: FORM_ODONTOLOGO_ID,
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
              name: "matricula",
              label: "Matrícula",
              type: "text",
              validate: (v) => (v ? true : "La matrícula es requerida"),
            },
          ],
          submit: { text: "Guardar" },
          onSubmit: async (formData, record) => {
            if (record) {
              await odontologosApi.update(record.id, formData);
            } else {
              await odontologosApi.create(formData);
            }
            $(`#${MODAL_ODONTOLOGO_ID}`).modal("hide"); // Bootstrap
            // Reload table
            dispatch(
              document.getElementById(TABLE_ODONTOLOGOS_ID),
              TABLE_EVENT_RELOAD
            );
          },
        }),
      }),
    ],
  });
