import { Container } from "./ui/container.js";
import { createHeading as Heading } from "./ui/heading.js";
import { OdontologosView } from "./views/odontologos.view.js";
import { PacientesView } from "./views/pacientes.view.js";
import { TurnosView } from "./views/turnos.view.js";

const ClinicaView = async () =>
  Container({
    classnames: "py-5",
    children: [
      Heading("Clínica Odontológica", {
        classnames: "text-center text-primary mb-3",
      }),

      await TurnosView(),
      await PacientesView(),
      await OdontologosView(),


    ],
  });

const appView = await ClinicaView();

document.getElementById("app").appendChild(appView);
