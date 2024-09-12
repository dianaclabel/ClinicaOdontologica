// form component using DOM manipulation and bootstrap classes
// it receives a single object with the following properties:
// - id: string with the id of the form (it is used also to generate fields ids)
// - onSubmit: function that receives the form data as an object
// - record: object with the values to set in the form fields
// - fields: array of objects with the following properties:
//   - name: string with the name of the input
//   - label: string with the label of the input
//   - type: string with the type of the input (text, number, email, password, etc)
//   - placeholder?: string with the placeholder of the input
//   - validate?: function that receives the value of the input and returns a boolean or a string with the error message
// - submit: object with the following properties:
//   - text: string with the text of the button

export const FORM_EVENT_SET_VALUES = "setValues";
export const FORM_EVENT_UPDATE_SUBMIT = "updateSubmit";
export const FORM_EVENT_INIT_RECORD = "initRecord";

export function Form({
  id,
  onSubmit,
  fields,
  submit,
  classNames = "",
  record,
}) {
  const form = document.createElement("form");
  // Autogenerate id if not provided
  if (!id) {
    id = `form-${Math.floor(Math.random() * 1000)}`;
  }
  form.id = id;
  form.classList.add("needs-validation");
  form.noValidate = true;

  // Add custom class names if provided
  if (classNames) {
    for (const className of classNames.split(" ")) {
      form.classList.add(className);
    }
  }

  for (const field of fields) {
    const fieldContainer = document.createElement("div");
    fieldContainer.classList.add("mb-3");

    const fieldLabel = document.createElement("label");
    fieldLabel.htmlFor = `${id}-${field.name}`;
    fieldLabel.textContent = field.label;
    fieldLabel.classList.add("form-label");
    fieldContainer.appendChild(fieldLabel);

    const fieldInput = document.createElement("input");
    fieldInput.id = `${id}-${field.name}`;
    fieldInput.type = field.type;
    if (field.placeholder) {
      fieldInput.placeholder = field.placeholder;
    }
    if (record?.[field.name]) {
      fieldInput.value = record[field.name];
    }
    fieldInput.classList.add("form-control");
    fieldContainer.appendChild(fieldInput);

    const errorMessage = document.createElement("div");
    errorMessage.classList.add("invalid-feedback");
    fieldContainer.appendChild(errorMessage);

    form.appendChild(fieldContainer);

    fieldInput.addEventListener("input", async () => {
      if (!hasSubmitted) return;
      await validateField(fieldInput, field);
    });
  }

  const submitButton = document.createElement("button");
  submitButton.type = "submit";
  submitButton.textContent = submit.text;
  submitButton.classList.add("btn", "btn-primary");
  form.appendChild(submitButton);

  let hasSubmitted = false;

  const validateField = async (input, field) => {
    if (field.validate) {
      const validationResult = await field.validate(input.value);
      if (validationResult !== true) {
        input.classList.add("is-invalid");
        input.nextElementSibling.textContent = validationResult;
      } else {
        input.classList.remove("is-invalid");
        input.nextElementSibling.textContent = "";
      }
    }
  };

  form.addEventListener("submit", async (event) => {
    event.preventDefault();

    hasSubmitted = true;
    let isValid = true;
    const formData = {};

    for (const field of fields) {
      const input = document.getElementById(`${id}-${field.name}`);
      const value = input.value;
      formData[field.name] = value;

      await validateField(input, field);
      if (input.classList.contains("is-invalid")) {
        isValid = false;
      }
    }

    if (isValid) {
      onSubmit(formData, record);
    }
  });

  form.addEventListener(FORM_EVENT_SET_VALUES, (event) => {
    const values = event.detail;
    for (const [name, value] of Object.entries(values)) {
      const input = document.getElementById(`${id}-${name}`);
      if (input) {
        input.value = value;
      }
    }
  });

  // listen for a custom event to set the record
  form.addEventListener(FORM_EVENT_INIT_RECORD, (event) => {
    record = event.detail;
    hasSubmitted = false;
    if (!record) {
      return;
    }
    for (const field of fields) {
      const input = document.getElementById(`${id}-${field.name}`);
      if (input) {
        input.value = record[field.name];
        // Clear validation messages
        input.classList.remove("is-invalid");
        input.nextElementSibling.textContent = "";
      }
    }
  });

  // listen for a custom event to update the onSubmit variable
  form.addEventListener(FORM_EVENT_UPDATE_SUBMIT, (event) => {
    onSubmit = event.detail;
  });

  return form;
}
