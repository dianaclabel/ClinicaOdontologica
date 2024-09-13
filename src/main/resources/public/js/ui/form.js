export const FORM_EVENT_SET_VALUES = "setValues";
export const FORM_EVENT_UPDATE_SUBMIT = "updateSubmit";
export const FORM_EVENT_INIT_RECORD = "initRecord";
export const FORM_EVENT_RELOAD_ALL_CHOICES = "reloadAllChoices";

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
    const fieldId = generateFieldId(id, field.name);
    const fieldContainer = document.createElement("div");
    fieldContainer.classList.add("mb-3");

    const fieldLabel = document.createElement("label");
    fieldLabel.htmlFor = fieldId;
    fieldLabel.textContent = field.label;
    fieldLabel.classList.add("form-label");
    fieldContainer.appendChild(fieldLabel);

    let fieldElement;
    if (field.tag === "select") {
      fieldElement = document.createElement("select");
      if (field.getChoices) {
        field.getChoices().then((choices) => {
          for (const choice of choices) {
            const option = document.createElement("option");
            option.value = choice.value;
            option.textContent = choice.label;
            fieldElement.appendChild(option);
          }
        });
      }
    } else {
      fieldElement = document.createElement("input");
      fieldElement.type = field.type;
      if (field.placeholder) {
        fieldElement.placeholder = field.placeholder;
      }
    }

    fieldElement.id = fieldId;
    if (record) {
      const value = field.name
        .split(".")
        .reduce((acc, key) => acc?.[key], record);
      if (value) {
        fieldElement.value = value;
      }
    }
    fieldElement.classList.add("form-control");
    fieldContainer.appendChild(fieldElement);

    const errorMessage = document.createElement("div");
    errorMessage.classList.add("invalid-feedback");
    fieldContainer.appendChild(errorMessage);

    form.appendChild(fieldContainer);

    fieldElement.addEventListener("input", async () => {
      if (!hasSubmitted) return;
      await validateField(fieldElement, field);
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
      const fieldId = generateFieldId(id, field.name);
      const input = document.getElementById(fieldId);
      const value = input.value;
      const keys = field.name.split(".");
      let current = formData;
      for (let i = 0; i < keys.length - 1; i++) {
        if (!current[keys[i]]) {
          current[keys[i]] = {};
        }
        current = current[keys[i]];
      }
      current[keys[keys.length - 1]] = value;

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
      const fieldId = generateFieldId(id, name);
      const input = document.getElementById(fieldId);
      if (input) {
        input.value = value;
      }
    }
  });

  // listen for a custom event to set the record
  form.addEventListener(FORM_EVENT_INIT_RECORD, (event) => {
    record = event.detail;
    hasSubmitted = false;
    for (const field of fields) {
      const fieldId = generateFieldId(id, field.name);
      const input = document.getElementById(fieldId);
      if (input) {
        if (record) {
          const value = field.name
            .split(".")
            .reduce((acc, key) => acc?.[key], record);
          if (value) {
            input.value = value;
          }
        } else {
          input.value = "";
        }
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

  // listen for a custom event to reload all choices
  form.addEventListener(FORM_EVENT_RELOAD_ALL_CHOICES, async () => {
    for (const field of fields) {
      if (field.tag === "select" && field.getChoices) {
        const fieldId = generateFieldId(id, field.name);
        const selectElement = document.getElementById(fieldId);
        if (selectElement) {
          // Clear existing options
          selectElement.innerHTML = "";
          // Fetch and append new choices
          const choices = await field.getChoices();
          for (const choice of choices) {
            const option = document.createElement("option");
            option.value = choice.value;
            option.textContent = choice.label;
            selectElement.appendChild(option);
          }
        }
      }
    }
  });

  return form;
}

function generateFieldId(formId, fieldName) {
  return `${formId}-${fieldName.replace(/\./g, "-")}`;
}
