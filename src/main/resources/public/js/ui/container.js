export function Container({ children, classnames = "" }) {
  const container = document.createElement("div");
  container.classList.add("container");
  if (classnames)
    for (const cls of classnames.split(" ")) {
      container.classList.add(cls);
    }
  container.append(...children);

  return container;
}
