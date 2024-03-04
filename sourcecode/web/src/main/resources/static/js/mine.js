const createFormForPost = (position, status) => {
  const form = document.createElement("form");
  form.method = "POST";
  form.action = "mine";

  const value = document.createElement("input");
  value.type = "hidden";
  value.name = "value";
  value.value = position;

  const param = document.createElement("input");
  param.type = "hidden";
  param.name = "name";
  param.value = status;

  form.appendChild(value);
  form.appendChild(param);
  document.body.appendChild(form);

  return form;
};

function openCell(position) {
  const form = createFormForPost(position, "openCell");
  form.submit();
}

function putFlag(position) {
  const form = createFormForPost(position, "putFlag");
  form.submit();
}

function removeFlag(position) {
  const form = createFormForPost(position, "removeFlag");
  form.submit();
}
