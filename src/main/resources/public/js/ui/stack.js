// Stack component using DOM
// Receives an array of DOM objects and returns a stack with the DOM objects inside
// The second argument is an object with the following properties:
// - direction: string, can be "row" or "column", default is "row"
// - gap: string, can be any CSS unit, default is "1rem"
// - classNames: string, additional class names to add to the stack
// By default, it uses Bootstrap classes to add flexbox properties

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
