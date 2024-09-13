

export function createHeading(text, { level = "h1", classnames = "" } = {}) {
  const heading = document.createElement(level);
  heading.textContent = text;
  if (classnames) heading.classList.add(...classnames.split(" "));

  return heading;
}
