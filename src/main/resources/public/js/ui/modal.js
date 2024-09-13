
export const MODAL_EVENT_SET_TITLE = "setTitle";

export function Modal({ id, title, content, footer }) {
  const modal = document.createElement("div");
  modal.classList.add("modal", "fade");
  modal.setAttribute("id", id);
  modal.setAttribute("tabindex", "-1");
  modal.setAttribute("role", "dialog");

  const modalDialog = document.createElement("div");
  modalDialog.classList.add("modal-dialog");
  modalDialog.setAttribute("role", "document");

  const modalContent = document.createElement("div");
  modalContent.classList.add("modal-content");

  const modalHeader = document.createElement("div");
  modalHeader.classList.add("modal-header");

  const modalTitle = document.createElement("h5");
  modalTitle.classList.add("modal-title");
  modalTitle.textContent = title;

  const closeButton = document.createElement("button");
  closeButton.classList.add("btn-close");
  closeButton.setAttribute("type", "button");
  closeButton.setAttribute("data-bs-dismiss", "modal");
  closeButton.setAttribute("aria-label", "Close");

  const closeIcon = document.createElement("i");
  closeIcon.classList.add("fas", "fa-times");

  closeButton.appendChild(closeIcon);

  closeButton.addEventListener("click", () => {
    $(`#${id}`).modal("hide");
  });

  modalHeader.append(modalTitle, closeButton);
  modalContent.append(modalHeader, content);

  if (footer) {
    modalContent.append(footer);
  }

  modalDialog.append(modalContent);
  modal.append(modalDialog);

  // listen a custom event to set title
  modal.addEventListener(MODAL_EVENT_SET_TITLE, (event) => {
    modalTitle.textContent = event.detail;
  });

  return modal;
}
