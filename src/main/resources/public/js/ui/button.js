

export function Button({ text, type = "primary", onclick, classnames = "" }) {
  const button = document.createElement("button");
  button.textContent = text;
  button.classList.add("btn", `btn-${type}`);
  if (onclick) button.addEventListener("click", onclick);
  if (classnames)
    for (const cls of classnames.split(" ")) {
      button.classList.add(cls);
    }

  return button;
}
