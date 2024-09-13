
export function Stack({
  children,
  direction = "row",
  gap = "1rem",
  classNames = "",
} = {}) {
  const stack = document.createElement("div");
  stack.classList.add("d-flex", `flex-${direction}`);
  // add gap
  stack.style.gap = gap;

  if (classNames) {
    for (const className of classNames.split(" ")) {
      stack.classList.add(className);
    }
  }

  stack.append(...children);

  return stack;
}
