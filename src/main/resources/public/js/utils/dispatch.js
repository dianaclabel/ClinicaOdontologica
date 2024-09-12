// function to dispatch a custom event in a dom element with a detail

export function dispatch(element, eventName, detail) {
  const event = new CustomEvent(eventName, { detail });
  element.dispatchEvent(event);
}
