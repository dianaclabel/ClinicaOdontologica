// Bootstrap-styled button with specified text and options.
// Options:
// - type: Button style (primary, secondary, etc.), default is primary.
// - onclick: Function to call on button click.
// - classnames: Additional CSS classes to add to the button.

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
