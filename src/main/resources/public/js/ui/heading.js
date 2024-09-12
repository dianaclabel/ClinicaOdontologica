// Create a heading using dom and bootstrap classes
// it receives a text and returns a heading with the text inside
// it also receives h1, h2, h3, h4, h5 or h6 as a string to define the heading level
// if no level is provided, it defaults to h1
// it also receives classnames as a string to add to the heading

export function createHeading(text, { level = "h1", classnames = "" } = {}) {
  const heading = document.createElement(level);
  heading.textContent = text;
  if (classnames) heading.classList.add(...classnames.split(" "));

  return heading;
}
