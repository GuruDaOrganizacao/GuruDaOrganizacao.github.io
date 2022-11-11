
const inputBox = document.querySelector(".campoInput input");
const addBtn = document.querySelector(".campoInput button");
const todoList = document.querySelector(".todoList");
const apagarTudoBtn = document.querySelector(".footer button");


inputBox.onkeyup = () => {
  let valorInserido = inputBox.value;
  if (valorInserido.trim() != 0) {
    addBtn.classList.add("active");
  } else {
    addBtn.classList.remove("active");
  }
}

mostrarTarefas();
addBtn.onclick = () => {
  let valorInserido = inputBox.value;
  let getLocalStorageData = localStorage.getItem("New Todo");
  if (getLocalStorageData == null) {
    listArray = [];
  } else {
    listArray = JSON.parse(getLocalStorageData);
  }
  listArray.push(valorInserido);
  localStorage.setItem("New Todo", JSON.stringify(listArray));
  mostrarTarefas();
  addBtn.classList.remove("active");
}

function mostrarTarefas() {
  let getLocalStorageData = localStorage.getItem("New Todo");
  if (getLocalStorageData == null) {
    listArray = [];
  } else {
    listArray = JSON.parse(getLocalStorageData);
  }
  const tarefaPendenteNumb = document.querySelector(".tarefaPendente");
  tarefaPendenteNumb.textContent = listArray.length;
  if (listArray.length > 0) {
    apagarTudoBtn.classList.add("active");
  } else {
    apagarTudoBtn.classList.remove("active");
  }
  let newLiTag = "";
  listArray.forEach((element, index) => {
    newLiTag += `<li>${element}<span class="icon" onclick="deleteTask(${index})">X</span></li>`;
  });
  todoList.innerHTML = newLiTag;
  inputBox.value = "";
}

//função excluir tarefa
function deleteTask(index) {
  let getLocalStorageData = localStorage.getItem("New Todo");
  listArray = JSON.parse(getLocalStorageData);
  listArray.splice(index, 1);
  localStorage.setItem("New Todo", JSON.stringify(listArray));
  mostrarTarefas();
}

// função excluir todas as tarefas
apagarTudoBtn.onclick = () => {
  let getLocalStorageData = localStorage.getItem("New Todo");
  if (getLocalStorageData == null) {
    listArray = [];
  } else {
    listArray = JSON.parse(getLocalStorageData);
    listArray = [];
  }
  localStorage.setItem("New Todo", JSON.stringify(listArray));
  mostrarTarefas(); //chame a função mostrarTarefas
}

const navSlide = () => {
  const burger = document.querySelector('.burger');
  const nav = document.querySelector('.nav-links');
  const navLinks = document.querySelectorAll('.nav-links li');

  burger.addEventListener('click', () => {
    //toggle Nav
    nav.classList.toggle('nav-active');

    //animate links
    navLinks.forEach((link, index) => {
      if (link.style.animation) {
        link.style.animation = '';
      } else {
        link.style.animation = `navLinkFade 0.5s ease forwards ${index / 7 + 0.5}s`;
      }
    });

    //burger animation
    burger.classList.toggle('toggle');
  });
}

navSlide();